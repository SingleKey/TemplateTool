package com.mycreate.tool.service;

import com.google.common.base.CaseFormat;
import com.mycreate.tool.model.ColumnClass;
import com.mycreate.tool.model.TableClass;
import com.mycreate.tool.utils.DBUtils;
import com.mycreate.tool.utils.LoggerUtils;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DbServiceImpl implements DbService {

    private Logger logger = LoggerUtils.getLogger();
    /**
     * 用户设置的包名，如com.mytext.demo
     */
    private String packageName;

    //加载FreeMarker配置文件
    private Configuration configuration;
    {
        configuration = new Configuration(Configuration.VERSION_2_3_19);
        configuration.setTemplateLoader(new ClassTemplateLoader(DbServiceImpl.class, "/templates/template"));
        //设置编码格式为utf-8
        configuration.setDefaultEncoding("UTF-8");
    }

    @Override
    public List<TableClass> config(String packageName) throws SQLException {
        this.packageName = packageName.replace(".", "\\");
        Connection connection = DBUtils.getConnection();
        List<TableClass> tableList = new ArrayList();
        DatabaseMetaData metaData = connection.getMetaData();
        //获取表名
        ResultSet tables = metaData.getTables(connection.getCatalog(), null, null, null);
        while (tables.next()) {
            TableClass tableClass = new TableClass(tables.getString("TABLE_NAME"));
            tableList.add(tableClass);
        }
        return tableList;
    }

    @Override
    public String createCode(List<TableClass> tableClassList, Integer type, String path) {
        //系统路径
        int t = type;
        String createPath = null;
        try{
            //获取模板
            Template modelTemplate = configuration.getTemplate("Model.java.ftl");           //model
            Template daoTemplate = configuration.getTemplate("Dao.java.ftl");             //dao
            Template daoImplTemplate = configuration.getTemplate("DaoImpl.java.ftl");         //daoImpl
            Template serviceTemplate = configuration.getTemplate("Service.java.ftl");         //service
            Template serviceImplTemplate = configuration.getTemplate("ServiceImpl.java.ftl");     //serviceImpl
            Template controllerTemplate = configuration.getTemplate("Controller.java.ftl");      //controller

            Connection connection = DBUtils.getConnection(); //获取列信息
            DatabaseMetaData metaData = connection.getMetaData();

            for(TableClass table : tableClassList) {
                //提前设置路径为：com.xxx.xxx.tablename
                table.setPackageName(packageName.replace("\\", ".") + "." + table.getPackageName());
                ResultSet columns = metaData.getColumns(connection.getCatalog(), null, table.getTableName(), null);     //获取当前表的所有字段
                ResultSet primaryKeys = metaData.getPrimaryKeys(connection.getCatalog(), null, table.getTableName());                               //获取当前表的所有主键
                List<ColumnClass> columnClassList = new ArrayList<>();                                                                                       //集合存储该表的所有字段

                while (columns.next()) {
                    ColumnClass column = new ColumnClass();
                    column.setColumnName(columns.getString("COLUMN_NAME"));                                     //获取数据库字段名
                    column.setType(columns.getString("TYPE_NAME"));                                             //获取数据库字段类型
                    column.setRemark(columns.getString("REMARKS"));                                             //获取备注
                    column.setPropertyName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, column.getColumnName()));   //下划线转驼峰

                    //将游标移动到第一个位置
                    primaryKeys.first();
                    //获取主键
                    while (primaryKeys.next()) {
                        String pk = primaryKeys.getString("COLUMN_NAME");
                        if(column.getColumnName().equals(pk)) {
                            column.setPrimary(true);
                        }else{
                            column.setPrimary(false);
                        }
                    }
                    columnClassList.add(column);
                }
                table.setColumns(columnClassList);

                /**
                 * createPath：生成文件所在路径，生成文件绝对路径 + com.xxx.xxx + model
                 * packageName：用户自定义包名，与packageName组合够为类似com.test.demo.user的包名
                 */
                /*
                    以表为包的架构
                 */
                if(t == 2) {
                    createPath = path + "\\" + packageName  + "\\" + table.getModelName().toLowerCase();
                    doCreate(modelTemplate, table, createPath + "\\model\\");
                    doCreate(daoTemplate, table, createPath + "\\dao\\");
                    doCreate(daoImplTemplate, table, createPath + "\\dao\\impl\\");
                    doCreate(serviceTemplate, table, createPath + "\\service\\");
                    doCreate(serviceImplTemplate, table, createPath + "\\service\\impl");
                    doCreate(controllerTemplate, table, createPath + "\\controller\\");

                } else {
                    /*
                        普通的三层架构
                    */
                    createPath = path + "\\" + packageName  + "\\";
                    doCreate(modelTemplate, table, createPath + "\\model\\");
                    doCreate(daoTemplate, table, createPath + "\\dao\\");
                    doCreate(daoImplTemplate, table, createPath + "\\dao\\impl\\");
                    doCreate(serviceTemplate, table, createPath + "\\service\\");
                    doCreate(serviceImplTemplate, table, createPath + "\\service\\impl");
                    doCreate(controllerTemplate, table, createPath + "\\controller\\");
                }
            }
        }catch (Exception e) {
            logger.error("创建代码失败！", e);
            e.printStackTrace();
            return null;
        }
        return path;
    }

    private void doCreate(Template template,TableClass table, String path) {
        File file = new File(path);
        logger.info("Path：" + path);
        if(!file.exists()) {
            file.mkdirs();
        }
        /**
         * 创建文件，获取表对象中的实体名，获取Model template文件名，保留.java，删除Model和.ftl
         */
        String fileName = path + "\\" + table.getModelName() + template.getName().replace(".ftl", "").replace("Model", "");
        FileOutputStream fos = null;
        OutputStreamWriter streamWriter = null;
        try{
            fos = new FileOutputStream(fileName);
            streamWriter = new OutputStreamWriter(fos);
            template.process(table, streamWriter);
        }catch (IOException e) {
            logger.error("IO异常！", e);
        } catch (TemplateException e) {
            logger.error("模板格式不正确！" + e);
        } finally {
            try {
                fos.close();
                streamWriter.close();
            } catch (IOException e) {
                logger.error("关闭IO流出错！", e);
            }
        }
    }
}

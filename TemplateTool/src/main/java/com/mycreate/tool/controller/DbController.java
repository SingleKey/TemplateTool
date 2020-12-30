package com.mycreate.tool.controller;

import com.mycreate.tool.model.CreateForm;
import com.mycreate.tool.model.DbForm;
import com.mycreate.tool.model.Result;
import com.mycreate.tool.model.TableClass;
import com.mycreate.tool.service.DbService;
import com.mycreate.tool.utils.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class DbController {

    @Autowired
    private DbService dbService;

    @GetMapping("/")
    public ModelAndView index(ModelAndView model) {
        model.setViewName("html/index");
        return model;
    }

    @PostMapping("/connection")
    public Result connectionDataSource(@RequestBody DbForm form) {
        Result res = null;
        Connection connection = DBUtils.initDB(form);
        if(connection == null) {
            //失败
            System.out.println("初始化势必，请检查参数！");
            res = Result.error("初始化失败，请检查参数！");
        }else{
            //成功:查询数据库
            System.out.println("数据库连接成功！");
            res = Result.success("数据库连接成功！");
            res.setData("");
        }
        return res;
    }

    @PostMapping("/config")
    public Result config(@RequestBody Map<String, String> map) {
        String packageName = map.get("packageName");
        if(packageName == null || packageName.isEmpty()) {
            System.out.println("请填写包名");
            return Result.error("请填写包名！");
        }
        List<TableClass> tableList = null;
        try {
            tableList = dbService.config(packageName);
        } catch (SQLException e) {
            System.out.println("生成信息失败！");
            e.printStackTrace();
            return Result.error("生成信息失败！");
        }
        System.out.println("配置信息已生成！\n");
        System.out.println("表名\t\t\t\t实体名\t\t\t\t映射文件\t\t\t\t业务逻辑层\t\t\t\t控制层");
        for(TableClass tab : tableList) {
            System.out.println(tab.getTableName() + "\t" +
                    tab.getModelName() + "\t" +
                    tab.getMapperName() + "\t" +
                    tab.getServiceName() + "\t" +
                    tab.getControllerName());
        }
        Result result = new Result(200, "配置信息已生成！", tableList);
        return result;
    }

    /**
     * 创建代码
     * @param form
     * @param request
     * @return
     */
    @PostMapping("/createCode")
    public Result createCode(@RequestBody CreateForm form, HttpServletRequest request) {
        String path = dbService.createCode(form.getTableList(), form.getType(), request.getServletContext().getRealPath("\\"));
        Result res = null;
        if(path != null) {
            res = Result.success("代码生成成功！", path);
        }else {
            res = Result.error("代码生成失败！");
        }
        return res;
    }
}

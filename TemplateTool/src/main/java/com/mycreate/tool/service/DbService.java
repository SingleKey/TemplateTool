package com.mycreate.tool.service;

import com.mycreate.tool.model.TableClass;

import java.sql.SQLException;
import java.util.List;

public interface DbService {

    /***
     * 根据包名获取配置信息
     *
     * @param packageName       包名
     * @return                  返回当前数据库所有表的配置信息
     */
    List<TableClass> config(String packageName) throws SQLException;

    /**
     * 创建根据配置信息创建代码
     * @param tableClassList    配置信息
     * @param type              默认为1,1代表按层分类，2代表按表分类
     * @return                  生成成功返回路径，生成失败返回null
     */
    String createCode(List<TableClass> tableClassList, Integer type, String path);
}

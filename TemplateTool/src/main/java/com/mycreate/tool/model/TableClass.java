package com.mycreate.tool.model;

import com.google.common.base.CaseFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TableClass implements Serializable {

    private static final long serialVersionUID = -71941200007912312L;

    private String tableName;
    private String modelName;
    private String mapperName;
    private String serviceName;
    private String controllerName;
    private String packageName;
    private List<ColumnClass> columns;

    public TableClass() {

    }

    public TableClass(String tableName) {
        this.tableName = tableName;
        this.columns = new ArrayList<ColumnClass>();
        if(tableName != null && !tableName.isEmpty()) {
            this.modelName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName);
            this.mapperName = this.modelName + "Mapper";
            this.serviceName = this.modelName + "Service";
            this.controllerName = this.modelName + "Controller";
            this.packageName = this.modelName.toLowerCase();
        }
    }
    public TableClass(String tableName,
                      String modelName,
                      String mapperName,
                      String serviceName,
                      String controllerName,
                      String packageName,
                      List<ColumnClass> columns) {
        this(tableName);
        this.columns = columns;

    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<ColumnClass> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnClass> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TableClass{");
        sb.append("tableName='").append(tableName).append('\'');
        sb.append(", modelName='").append(modelName).append('\'');
        sb.append(", mapperName='").append(mapperName).append('\'');
        sb.append(", serviceName='").append(serviceName).append('\'');
        sb.append(", controllerName='").append(controllerName).append('\'');
        sb.append(", packageName='").append(packageName).append('\'');
        sb.append(", columns=").append(columns);
        sb.append('}');
        return sb.toString();
    }
}

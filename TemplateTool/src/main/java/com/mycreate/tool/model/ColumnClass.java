package com.mycreate.tool.model;

public class ColumnClass {

    private String propertyName;        //实体属性名
    private String columnName;          //数据库字段名
    private String type;                //数据库字段类型
    private String remark;              //数据库字段的备注
    private Boolean isPrimary;          //是否主键

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ColumnClass{");
        sb.append("propertyName='").append(propertyName).append('\'');
        sb.append(", columnName='").append(columnName).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", remark='").append(remark).append('\'');
        sb.append(", isPrimary=").append(isPrimary);
        sb.append('}');
        return sb.toString();
    }
}

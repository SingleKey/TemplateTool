package com.mycreate.tool.model;

import java.util.List;

public class CreateForm {

    private List<TableClass> tableList;
    private Integer type;

    public List<TableClass> getTableList() {
        return tableList;
    }

    public void setTableList(List<TableClass> tableList) {
        this.tableList = tableList;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

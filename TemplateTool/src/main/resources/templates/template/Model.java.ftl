package ${packageName}.model;

import java.util.Date;
import java.util.List;

public class ${modelName} {

    <#if columns??>
        <#list columns as column>
            <#if column.type='VARCHAR'||column.type='TEXT'||column.type='CHAR'||column.type='TINYBLOB'||column.type='TINYTEXT'||column.type='BLOB'>
                <#--字符，首字母转小写-->
                /**
                * ${column.remark}
                */
                private String ${column.propertyName?uncap_first};
            </#if>
            <#if column.type='INT'||column.type='TINYINT'||column.type='SMALLINT'||column.type='MEDIUMINT'||column.type='INTEGER'>
                <#--整数-->
                /**
                * ${column.remark}
                */
                private Integer ${column.propertyName?uncap_first};
            </#if>
            <#if column.type='BIGINT'>
                <#--大整数-->
                /**
                * ${column.remark}
                */
                private BigInteger ${column.propertyName?uncap_first};
            </#if>
            <#if column.type='DATE'||column.type='TIME'||column.type='YEAR'||column.type='DATATIME'||column.type='TIMESTAMP'>
                <#--时间-->
                /**
                * ${column.remark}
                */
                private Date ${column.propertyName?uncap_first};
            </#if>
            <#if column.type='FLOAT'>
                <#--浮点数-->
                /**
                * ${column.remark}
                */
                private Float ${column.propertyName?uncap_first};
            </#if>
            <#if column.type='DOUBLE'>
                <#--浮点数-->
                /**
                * ${column.remark}
                */
                private Double ${column.propertyName?uncap_first};
            </#if>
        </#list>
    </#if>

    public ${modelName}() {

    }

    <#if columns??>
        <#list columns as column>
            <#if column.type='VARCHAR'||column.type='TEXT'||column.type='CHAR'||column.type='TINYBLOB'||column.type='TINYTEXT'||column.type='BLOB'>
                public String get${column.propertyName} {
                    return this.${column.propertyName?uncap_first};
                }
                public void set${column.propertyName }(String ${column.propertyName?uncap_first}) {
                    this.${column.propertyName?uncap_first} = ${column.propertyName?uncap_first};
                }
            </#if>
            <#if column.type='INT'||column.type='TINYINT'||column.type='SMALLINT'||column.type='MEDIUMINT'||column.type='INTEGER'>
                public Integer get${column.propertyName} {
                    return this.${column.propertyName?uncap_first};
                }
                public void set${column.propertyName}(Integer ${column.propertyName?uncap_first}) {
                    this.${column.propertyName?uncap_first} = ${column.propertyName?uncap_first};
                }
            </#if>
            <#if column.type='BIGINT'>
                public BigInteger get${column.propertyName} {
                    return this.${column.propertyName?uncap_first};
                }
                public void set${column.propertyName}(Integer ${column.propertyName?uncap_first}) {
                    this.${column.propertyName?uncap_first} = ${column.propertyName?uncap_first};
                }
            </#if>
            <#if column.type='DATE'||column.type='TIME'||column.type='YEAR'||column.type='DATATIME'||column.type='TIMESTAMP'>
                public Date get${column.propertyName} {
                    return this.${column.propertyName?uncap_first};
                }
                public void set${column.propertyName}(Date ${column.propertyName?uncap_first}) {
                    this.${column.propertyName?uncap_first} = ${column.propertyName?uncap_first};
                }
            </#if>
            <#if column.type='FLOAT'>
                public Float get${column.propertyName} {
                    return this.${column.propertyName?uncap_first};
                }
                public void set${column.propertyName}(Float ${column.propertyName?uncap_first}) {
                    this.${column.propertyName?uncap_first} = ${column.propertyName?uncap_first};
                }
            </#if>
            <#if column.type='DOUBLE'>
                public Double get${column.propertyName} {
                    return this.${column.propertyName?uncap_first};
                }
                public void set${column.propertyName}(Double ${column.propertyName?uncap_first}) {
                    this.${column.propertyName?uncap_first} = ${column.propertyName?uncap_first};
                }
            </#if>
        </#list>
    </#if>

}

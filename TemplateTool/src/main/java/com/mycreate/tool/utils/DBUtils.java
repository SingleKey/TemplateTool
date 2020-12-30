package com.mycreate.tool.utils;

import com.mycreate.tool.model.DbForm;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 用于连接数据库
 */
public class DBUtils {

    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    public static Connection initDB(DbForm dbForm) {
        if(!isLegalData(dbForm)) {
            return null;
        }
        String fv = "com.mysql.jdbc.Driver";            //5.0+版本MySQL
        String ev = "com.mysql.cj.jdbc.Driver";         //8.0+版本MySQL
        if(connection == null) {
            try{
                if("8".equals(dbForm.getVersion())) {
                    Class.forName(ev);
                }else if("5".equals(dbForm.getVersion())){
                    Class.forName(fv);
                }else{
                    return null;
                }

                connection = DriverManager.getConnection(dbForm.getUrl(), dbForm.getUsername(), dbForm.getPassword());
            }catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return connection;
    }

    public static boolean isLegalData(DbForm dbForm) {
        if(dbForm.getUsername() == null || dbForm.getUsername().isEmpty()
                || dbForm.getPassword() == null || dbForm.getPassword().isEmpty()
                || dbForm.getUrl() == null || dbForm.getUrl().isEmpty()
                || dbForm.getVersion() == null || dbForm.getVersion().isEmpty()) {
            return false;
        }
        return true;
    }

}

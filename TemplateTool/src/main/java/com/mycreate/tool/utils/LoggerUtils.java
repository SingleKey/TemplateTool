package com.mycreate.tool.utils;

import com.mycreate.tool.service.DbServiceImpl;
import org.apache.log4j.Logger;

public class LoggerUtils {

    private static Logger logger = Logger.getLogger(DbServiceImpl.class);

    public static Logger getLogger() {
        return logger;
    }

}

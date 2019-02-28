package com.product.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {


    public static Logger getLog(Object obj) {
        Logger LOGGER = LoggerFactory.getLogger(obj.getClass());
        return LOGGER;
    }
}

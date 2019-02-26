package com.product.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil{


public static Logger getLog(Object obj){
    Logger LOGGER = LoggerFactory.getLogger(obj.getClass());
   // LOGGER.info(message);
    return LOGGER;
}

public static void info(Object obj,String message){
    Logger LOGGER = LoggerFactory.getLogger(obj.getClass());
    LOGGER.info(message);
}
}

package com.llm.demo.controller;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Log4j {
    static Logger logger = Logger.getLogger(Log4j.class.getName());
    public static void main(String[] args){

        PropertyConfigurator.configure("log4j.properties");

        logger.debug("===========debug信息===============");

        logger.info("===========info信息===============");

        logger.error("===========error信息===============");

    }
}
package com.utils;

import java.util.Properties;

public class ConfigReader {

    Properties prop;

    private ConfigReader(){

        this.prop = System.getProperties();
    }

    private static ConfigReader configReader = null;

    public static ConfigReader getInstance(){
        if(configReader == null)
            configReader = new ConfigReader();

        return configReader;
    }

    public String getProp(String propertyName) {

        return prop.getProperty(propertyName);
    }
}

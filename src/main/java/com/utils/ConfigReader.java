package com.utils;

import java.util.Properties;

public class ConfigReader {

    Properties prop;

    public ConfigReader(){
        this.prop = System.getProperties();
    }

    public String getProp(String propertyName) {
        return prop.getProperty(propertyName);
    }
}

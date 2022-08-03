package com.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

public class YamlReader {

    Map<?, ?> data;
    public YamlReader(String path) {
        try{
            InputStream inputStream = new FileInputStream(path);
            Yaml yaml = new Yaml();
            this.data= yaml.load(inputStream);
            inputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getValue(String path){
        Map<?,?> element=(Map<?,?>) data.get(path.split("\\.")[0]);
        return element.get(path.split("\\.")[1]).toString();
    }

}
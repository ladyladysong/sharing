package com.sharing.util;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtil {

    private static Properties properties;

    static {
        String fileName="sharing.properties";
        properties=new Properties();
        try {
            properties.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"UTF-8"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        String result=properties.getProperty(key.trim());
        if(StringUtils.isBlank(result)){
            return null;
        }
        return result.trim();
    }

    public static String getProperty(String key, String defaultValue){
        String result=properties.getProperty(key.trim());
        if(StringUtils.isBlank(result)){
            result = defaultValue;
        }
        return result.trim();
    }
}

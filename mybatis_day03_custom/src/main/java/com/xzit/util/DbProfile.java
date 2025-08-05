package com.xzit.util;

import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Data
public class DbProfile {
    //解析SqlMapConfig.xml
    private String driver;
    private String url;
    private String username;
    private String password;

@Getter
    private Map<String, Mapper> mappers;

    public void setMappers(Map<String, Mapper> mappers) {
        if (this.mappers == null) {
            this.mappers = new HashMap<String, Mapper>();
        }
        this.mappers.putAll(mappers);
    }
}

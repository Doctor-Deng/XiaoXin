package com.mybatis.cfg;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XiaoXin
 * @title: Configuration
 * @projectName day01_03mybatis_design
 * @description: 自定义 mybatis 的配置类
 * @date 2019/10/6 16:46
 */
public class Configuration {

    private String drive;
    private String url;
    private String username;
    private String password;

    private Map<String,Mapper> mappers = new HashMap<String,Mapper>();

    public Map<String, Mapper> getMappers() {
        return mappers;
    }

    public void setMappers(Map<String, Mapper> mappers) {
        this.mappers.putAll(mappers);   //追加
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

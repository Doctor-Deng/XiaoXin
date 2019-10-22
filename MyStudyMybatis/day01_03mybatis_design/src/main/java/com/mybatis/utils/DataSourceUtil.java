package com.mybatis.utils;

import com.mybatis.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author XiaoXin
 * @title: DataSourceUtil
 * @projectName day01_03mybatis_design
 * @description: TODO
 * @date 2019/10/6 20:20
 * 创建数据源的工具类
 */
public class DataSourceUtil {
    public static Connection getConnection(Configuration cfg){
        try {
            Class.forName(cfg.getDrive());
            return DriverManager.getConnection(cfg.getUrl(),
                    cfg.getUsername(),cfg.getPassword());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

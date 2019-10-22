package com.mybatis.sqlsession;

import com.mybatis.cfg.Configuration;
import com.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import com.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * @author XiaoXin
 * @title: SqlSessionFactoryBuilder
 * @projectName day01_03mybatis_design
 * @description: 创建 SqlSessionFactory 对象
 * @date 2019/10/6 15:56
 */
public class SqlSessionFactoryBuilder {
    /**
      *根据参数的字节输入流创建一个 SqlSessionFactory
      * @param config
      * @Return com.mybatis.sqlsession.SqlSessionFactory
      * @author XiaoXin
      * @date 2019/10/6 20:28
      */
    public SqlSessionFactory build(InputStream config){
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(cfg);
    }
}

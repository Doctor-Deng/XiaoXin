package com.mybatis.sqlsession.defaults;

import com.mybatis.cfg.Configuration;
import com.mybatis.sqlsession.SqlSession;
import com.mybatis.sqlsession.SqlSessionFactory;

/**
 * @author XiaoXin
 * @title: DefaultSqlSessionFactory
 * @projectName day01_03mybatis_design
 * @description: SqlSessionFactory 接口的实现类
 * @date 2019/10/6 17:07
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg){
        this.cfg = cfg;
    }
    /**
      * @description: 创建一个新的数据库对象
      * @param ${tags}
      * @return ${return_type}
      * @throws
      * @author XiaoXin
      * @date 2019/10/6 17:09
      */
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}

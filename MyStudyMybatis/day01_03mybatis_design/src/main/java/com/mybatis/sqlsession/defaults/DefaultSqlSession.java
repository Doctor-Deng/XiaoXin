package com.mybatis.sqlsession.defaults;

import com.mybatis.cfg.Configuration;
import com.mybatis.sqlsession.SqlSession;
import com.mybatis.sqlsession.proxy.MapperProxy;
import com.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @author XiaoXin
 * @title: DefaultSqlSession
 * @projectName day01_03mybatis_design
 * @description: SqlSession 的实现类
 * @date 2019/10/6 17:07
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration cfg;
    private Connection connection;

    public DefaultSqlSession(Configuration cfg){
        this.cfg = cfg;
        connection = DataSourceUtil.getConnection(cfg);
    }

    /**
      * @description: 用于创建代理对象
      * @param daoInterfaceClass dao 的接口字节码
      * @param <T>
      * @return ${return_type}
      * @throws
      * @author XiaoXin
      * @date 2019/10/6 17:16
      */
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},new MapperProxy(cfg.getMappers(),connection));
    }

    //关闭资源
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

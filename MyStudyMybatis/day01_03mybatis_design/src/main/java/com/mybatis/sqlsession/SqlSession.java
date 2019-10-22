package com.mybatis.sqlsession;

/**
 * @author XiaoXin
 * @title: SqlSession
 * @projectName day01_03mybatis_design
 * @description: 自定义 Mybatis 中和数据库交互的核心类，可以创建 dao 接口的代理对象
 * @date 2019/10/6 16:31
 */
public interface SqlSession {
    /**
      * @description: TODO
      * @param daoInterfaceClass dao 的接口字节码
      * @param <T>
      * @throws
      * @author XiaoXin
      * @date 2019/10/6 16:32
      */
    <T> T getMapper(Class<T> daoInterfaceClass);

    void close();
}

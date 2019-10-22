package com.mybatis.sqlsession;

/**
 * @author XiaoXin
 * @title: SqlSessionFactory
 * @projectName day01_03mybatis_design
 * @description:
 * @date 2019/10/6 15:59
 */
public interface SqlSessionFactory {
    /**
     * 用于打开一个新的 SqlSession 对象
     * @return
     */
    SqlSession openSession();
}

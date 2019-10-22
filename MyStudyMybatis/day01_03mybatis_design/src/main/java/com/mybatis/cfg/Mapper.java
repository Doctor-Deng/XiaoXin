package com.mybatis.cfg;

/**
 * @author XiaoXin
 * @title: Mapper
 * @projectName day01_03mybatis_design
 * @description: 用于封装执行的 SQL 语句和结果类型的全限定类名
 * @date 2019/10/6 16:50
 */
public class Mapper {

    private String queryString;     // Sql
    private String resultType;      //实体类的全限定类名

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}

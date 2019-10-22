package com.mybatis.sqlsession.proxy;

import com.mybatis.cfg.Mapper;
import com.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * @author XiaoXin
 * @title: MapperProxy
 * @projectName day01_03mybatis_design
 * @description: TODO
 * @date 2019/10/6 18:52
 */
public class MapperProxy implements InvocationHandler {
    // map 是全限定类名 +     方法名
    private Map<String, Mapper> mappers;
    private Connection conn;

    public MapperProxy(Map<String,Mapper> mappers, Connection conn){
        this.mappers = mappers;
        this.conn = conn;
    }

    /**
      * 用于对方法进行增强的，就是调用 SelectList 方法
      * @param proxy
      * @param method
      * @param args
      * @Return java.lang.Object
      * @author XiaoXin
      * @date 2019/10/6 20:03
      */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //获取方法名
        String methodName = method.getName();
        //获取方法所在类的名称
        String className = method.getDeclaringClass().getName();
        //组合 key
        String key = className + "." + methodName;
        //获取 mappers 中的 Mapper 对象
        Mapper mapper = mappers.get(key);
        //判断是否有 mapper
        if (mapper == null){
            throw new IllegalAccessException("传入参数有误");
        }
        //调用工具类执行查询所有
        return new Executor().selectList(mapper,conn);
    }


}

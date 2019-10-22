package com.test;


import com.mybatis.io.Resources;
import com.mybatis.sqlsession.SqlSession;
import com.mybatis.sqlsession.SqlSessionFactory;
import com.mybatis.sqlsession.SqlSessionFactoryBuilder;

import com.dao.IUserDao;
import com.domain.User;
import java.io.InputStream;
import java.util.List;

/**
 * @author 小新
 * mybatis 的入门案例
 */
public class MybatisTest {

    public static void main(String[] args) throws Exception{
        //读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建 SqlSessionFactory 工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //使用工厂生产 SqlSession 对象
        SqlSession session = factory.openSession();
        //使用 SqlSession 创建 Dao 接口的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        //使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users){
            System.out.println(user);
        }
        //释放资源
        session.close();
        in.close();
    }
}

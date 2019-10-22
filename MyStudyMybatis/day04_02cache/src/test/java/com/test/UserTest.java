package com.test;

import com.dao.IUserDao;
import com.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;


/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 */
public class UserTest {

    private SqlSessionFactory factory;
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true);
        //4.获取dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After//用于在测试方法执行之后执行
    public void destroy()throws Exception{
        //提交事务
        // sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试一级缓存
     */
    @Test
    public void testFirstLevelCache(){
        User user1 = userDao.findById(48);
        System.out.println(user1);

//        sqlSession.close();
//
//        //再次获取 SqlSession 对象
//        sqlSession = factory.openSession();
        //相同的效果
        sqlSession.clearCache();

        userDao = sqlSession.getMapper(IUserDao.class);

        User user2 = userDao.findById(48);
        System.out.println(user2);
        System.out.println(user1 == user2);
    }

    /**
     * 测试缓存的同步
     */
    @Test
    public void testClearCache(){
        //根据 id 查询用户
        User user1 = userDao.findById(48);
        System.out.println(user1);

        //更新用户信息
        user1.setUsername("测试更新");
        user1.setAddress("呀拉索");
        userDao.updateUser(user1);

        //查询 id 为48的用户
        User user2 = userDao.findById(48);
        System.out.println(user2);
        System.out.println(user1 == user2);
    }
}


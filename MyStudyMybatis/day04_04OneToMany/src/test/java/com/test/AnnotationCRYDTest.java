package com.test;

import com.dao.IAccountDao;
import com.dao.IUserDao;
import com.domin.Account;
import com.domin.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author XiaoXin
 * @title: AnnotationCRYDTest
 * @projectName day04_03anotation_mybatis
 * @description: TODO
 * @date 2019/10/20 下午 04:40
 */
public class AnnotationCRYDTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destory() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testFindAll(){
        List<User> user = userDao.findAll();
//        for (User user1 : user){
//            System.out.println(user1);
//            System.out.println(user1.getAccounts());
//        }
    }

    @Test
    public void testFindOne(){
        User user = userDao.findById(61);
        System.out.println(user);
    }

    @Test
    public void testFindByName(){
        List<User> users = userDao.findByName("%蔡%");
        for (User user : users)
        System.out.println(user);
    }

}

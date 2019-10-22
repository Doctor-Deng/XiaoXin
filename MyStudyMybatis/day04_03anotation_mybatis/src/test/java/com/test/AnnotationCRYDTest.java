package com.test;

import com.dao.IUserDao;
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
    public void testSave(){
        User user = new User();
        user.setUsername("注解测试");
        user.setAddress("啊啊啊啊");
        user.setSex("女");
        userDao.saveUser(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setUsername("注解更新测试");
        user.setAddress("啊啊啊啊");
        user.setSex("男");
        user.setId(62);
        userDao.updateUser(user);
    }

    @Test
    public void testDel(){
        userDao.delUser(62);
    }

    @Test
    public void testFindOne(){
        User user = userDao.findById(61);
        System.out.println(user);
    }

    @Test
    public void testFindByName(){
//        List<User> users = userDao.findByName("%蔡%");
        List<User> users = userDao.findByName("蔡");
        for (User user : users)
        System.out.println(user);
    }

    @Test
    public void testFindTotal(){
        int totalUser = userDao.findTotalUser();
        System.out.println(totalUser);
    }
}

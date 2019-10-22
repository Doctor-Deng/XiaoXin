package com;

import com.dao.IUserDao;
import com.dao.impl.UserDaoImpl;

import com.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author XiaoXin
 * @title: MybatisTest
 * @projectName day02_01mybatisCRUD
 * @description: 测试 myabtis 的 crub 操作
 * @date 2019/10/8 11:12
 */
public class MybatisTest {

    private InputStream in;
    private IUserDao userDao;

    @Before // 测试方法最先执行
    public void init() throws IOException {
        // 读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获取 SqlSessionFactory  对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 使用工厂对象，创建 dao 对象
        userDao = new UserDaoImpl(factory);
    }

    @After // 用于在测试方法执行之后执行
    public void destory() throws IOException {
        in.close();
    }

    /**
      * 测试查询所有
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 11:21
      */
    @Test
    public void testFindAll() {
        // 执行查询所有方法
        List<User> users = userDao.findAll();
        for (User user : users){
            System.out.println(user);
        }
    }

    /**
      * 测试保存操作
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 11:47
      */
    @Test
    public void testSava() {
        User user = new User();
        user.setUsername("测试方法");
        user.setAddress("呀拉索");
        user.setSex("男");
        user.setBirthday(new Date());

        System.out.println("保存前："+ user);

        // 执行保存方法
        userDao.saveUser(user);

        System.out.println("保存前："+ user);
    }

    /**
      * 执行更新操作
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 14:33
      */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(60);
        user.setUsername("测试dao更新方法");
        user.setAddress("呀拉索");
        user.setSex("女");
        user.setBirthday(new Date());

        // 执行更新方法
        userDao.updateUser(user);
    }

    /**
      * 根据 ID 删除操作
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 14:49
      */
    @Test
    public void testDel(){
        userDao.delUser(55);
    }

    /**
      * 根据 id 查询用户
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 15:08
      */
    @Test
    public void testFindOne(){
        User user = userDao.findById(56);
        System.out.println(user);
    }
    /**
      * 根据 姓名 模糊查找
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 15:19
      */
    @Test
    public void testFindByName(){
        List<User> users= userDao.findByName("徐");
        for (User user1 : users){
            System.out.println(user1);
        }
    }

    /**
     * 测试查询总记录条数
     * @Return void
     * @author XiaoXin
     * @date 2019/10/8 15:21
     */
    @Test
    public void testFindTotal(){
        int count = userDao.findTotal();
        System.out.println(count);
    }
}

package com;

import com.dao.IUserDao;
import com.domain.QueryVo;
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
import java.util.ArrayList;
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
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before // 测试方法最先执行
    public void init() throws IOException {
        // 读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获取 SqlSessionFactory  对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 获取 SqlSession 对象
        sqlSession = factory.openSession(true);     // 参数是 true 的话，就自动提交，不用自己手动 commit，实际开发中不常用（因为方法中如果进行多次与数据库交互，那么这个事务是不可控的）
        // 获取 dao 的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After // 用于在测试方法执行之后执行
    public void destory() throws IOException {
        //提交事务
        //sqlSession.commit();
        // 关闭资源
        sqlSession.close();
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
      * 测试使用 QueryVo 作为查询条件
      * @Return void
      * @author XiaoXin
      * @date 2019/10/10 16:08
      */
    @Test
    public void testFindByQueryVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("%王%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        for (User user1 : users){
            System.out.println(user1);
        }
    }

    /**
      *
      * @Return void
      * @author XiaoXin
      * @date 2019/10/10 16:10
      */
    @Test
    public void testFindByCondition(){
        User uu = new User();
        uu.setUserName("测试方法");
        uu.setUserSex("女");
        // 执行查询所有方法
        List<User> users = userDao.findUserByCondition(uu);
        for (User user : users){
            System.out.println(user);
        }
    }

    /**
      * foreach 标签的使用
      * @Return void
      * @author XiaoXin
      * @date 2019/10/10 16:46
      */
    @Test
    public void testFindInIds(){
        QueryVo vo = new QueryVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(45);
        list.add(48);
        list.add(53);
        vo.setIds(list);
        // 执行查询所有方法
        List<User> users = userDao.fingUserInIds(vo);
        for (User user : users){
            System.out.println(user);
        }
    }

}

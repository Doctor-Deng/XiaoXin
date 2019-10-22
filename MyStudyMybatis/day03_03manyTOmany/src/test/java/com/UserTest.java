package com;

import com.dao.IUserDao;
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
import java.util.List;

/**
 * @author XiaoXin
 * @title: AccountTest
 * @projectName day03_03one2many
 * @description: TODO
 * @date 2019/10/11 23:45
 */
public class UserTest {
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
     * @date 2019/10/14 9:26
     */
    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user : users){
            System.out.println("每个用户信息：");
            System.out.println(user);
            System.out.println(user.getRoles());
        }
    }
}


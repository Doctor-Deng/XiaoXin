package com.test;

import com.dao.IUserDao;
import com.domin.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author XiaoXin
 * @title: MybatisAnnoTest
 * @projectName day04_03anotation_mybatis
 * @description: TODO
 * @date 2019/10/20 下午 03:42
 */
public class MybatisAnnoTest {
    /**
      * 测试注解的 mybatis 的使用
      * @param args
      * @Return void
      * @author XiaoXin
      * @date 2019/10/20 下午 03:42
      */
    public static void main(String[] args) throws Exception{
        //1、获取字节输入流
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2、根据字节输入流构建 SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3、根据 SqlSessionFactory 生成一个 SqlSession 对象
        SqlSession session = factory.openSession();
        //4、使用 SqlSession 获取 Dao 的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        //5、执行 Dao 的方法
        List<User> users = userDao.findAll();
        for (User user : users){
            System.out.println(user);
        }
        //6、释放资源
        session.close();
        in.close();
    }
}

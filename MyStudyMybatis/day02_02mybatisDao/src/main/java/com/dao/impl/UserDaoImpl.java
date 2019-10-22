package com.dao.impl;

import com.dao.IUserDao;
import com.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author XiaoXin
 * @title: UserDaoImpl
 * @projectName day02_02mybatisDao
 * @description: TODO
 * @date 2019/10/9 16:07
 */
public class UserDaoImpl implements IUserDao {
    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }

    public List<User> findAll() {
        //根据 factory 获取 SqlSession 对象
        SqlSession session = factory.openSession();
        //调用 SqlSession 中的方法，实现查询列表
        //参数写的是获取配置信息的 key（也就是 IUserDao.xml 的 namespace 加上方法 id）
        List<User> user = session.selectList("com.dao.IUserDao.findAll");
        //释放资源
        session.close();
        return user;
    }

    public void saveUser(User user) {
        //根据 factory 获取 SqlSession 对象
        SqlSession session = factory.openSession();
        //调用 SqlSession 中的方法，实现查询列表
        //参数写的是获取配置信息的 key（也就是 IUserDao.xml 的 namespace 加上方法 id）
        session.insert("com.dao.IUserDao.saveUser",user);       //第一个参数是用哪里的语句进行执行，第二个参数是用哪个对象的数据
        //提交事务
        session.commit();
        //关闭资源
        session.close();
    }

    public void updateUser(User user) {
        //根据 factory 获取 SqlSession 对象
        SqlSession session = factory.openSession();
        //调用 SqlSession 中的方法，实现查询列表
        //参数写的是获取配置信息的 key（也就是 IUserDao.xml 的 namespace 加上方法 id）
        session.update("com.dao.IUserDao.updateUser",user);       //第一个参数是用哪里的语句进行执行，第二个参数是用哪个对象的数据
        //提交事务
        session.commit();
        //关闭资源
        session.close();
    }

    public void delUser(Integer userId) {
        //根据 factory 获取 SqlSession 对象
        SqlSession session = factory.openSession();
        //调用 SqlSession 中的方法，实现查询列表
        //参数写的是获取配置信息的 key（也就是 IUserDao.xml 的 namespace 加上方法 id）
        session.delete("com.dao.IUserDao.delUser",userId);       //第一个参数是用哪里的语句进行执行，第二个参数是用哪个对象的数据
        //提交事务
        session.commit();
        //关闭资源
        session.close();
    }

    public User findById(Integer userId) {
        //根据 factory 获取 SqlSession 对象
        SqlSession session = factory.openSession();
        //调用 SqlSession 中的方法，实现查询列表
        //参数写的是获取配置信息的 key（也就是 IUserDao.xml 的 namespace 加上方法 id）
        User user = session.selectOne("com.dao.IUserDao.findById",userId);
        //释放资源
        session.close();
        return user;
    }

    public List<User> findByName(String name) {
        //根据 factory 获取 SqlSession 对象
        SqlSession session = factory.openSession();
        //调用 SqlSession 中的方法，实现查询列表
        //参数写的是获取配置信息的 key（也就是 IUserDao.xml 的 namespace 加上方法 id）
        List<User> user = session.selectList("com.dao.IUserDao.findByName",name);
        //释放资源
        session.close();
        return user;
    }

    public int findTotal() {
        //根据 factory 获取 SqlSession 对象
        SqlSession session = factory.openSession();
        //调用 SqlSession 中的方法，实现查询列表
        //参数写的是获取配置信息的 key（也就是 IUserDao.xml 的 namespace 加上方法 id）
        Integer i = session.selectOne("com.dao.IUserDao.findTotal");
        //释放资源
        session.close();
        return i;
    }
}

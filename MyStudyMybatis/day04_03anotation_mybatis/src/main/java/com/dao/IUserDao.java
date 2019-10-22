package com.dao;

import com.domin.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author XiaoXin
 * @title: IUserDao
 * @projectName day04_03anotation_mybatis
 * @description: 在 mybatis 中，CRUD 一共有四个注解
 * @Select()
 * @Insert()
 * @Update()
 * @Delete()
 * @date 2019/10/20 下午 03:29
 */
public interface IUserDao {
    /***
      * 查询所有用户
      * @Return java.util.List<com.domin.User>
      * @author XiaoXin
      * @date 2019/10/20 下午 03:29 
      */
    @Select("select * from user")
    List<User> findAll();

    /**
      * 保存用户
      * @param user
      * @Return void
      * @author XiaoXin
      * @date 2019/10/20 下午 04:37 
      */
    @Insert("insert into user(username,sex,address,birthday)values(#{username},#{sex},#{address},#{birthday})")
    void saveUser(User user);

    /**
      * 更新用户
      * @Return void
      * @author XiaoXin
      * @date 2019/10/20 下午 04:56
      */
    @Update("update user set username=#{username},sex=#{sex},address=#{address},birthday=#{birthday} where id=#{id}")
    void updateUser(User user);

    /**
      * 删除用户
      * @param userid
      * @Return void
      * @author XiaoXin
      * @date 2019/10/20 下午 05:00
      */
    @Delete("delete from user where id=#{id}")
    void delUser(Integer userid);

    /**
      * 根据用户 id 进行查询
      * @param userId
      * @Return com.domin.User
      * @author XiaoXin
      * @date 2019/10/20 下午 05:03
      */
    @Select("select * from user where id=#{id}")
    User findById(Integer userId);

    /**
      * 根据用户昵称进行模糊查询
      * @param username
      * @Return java.util.List<com.domin.User>
      * @author XiaoXin
      * @date 2019/10/20 下午 05:05
      */
//    @Select("select * from user where username like #{username}")
    @Select("select * from user where username like '%${value}%'")
    List<User> findByName(String username);

    /**
      * 查询总用户数
      * @Return int
      * @author XiaoXin
      * @date 2019/10/20 下午 05:13
      */
    @Select("select count(*) from user")
    int findTotalUser();
}

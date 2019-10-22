package com.dao;

import com.domin.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

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
@CacheNamespace(blocking = true)
public interface IUserDao {
    /***
      * 查询所有用户
      * @Return java.util.List<com.domin.User>
      * @author XiaoXin
      * @date 2019/10/20 下午 03:29 
      */
    @Select("select * from user")
    @Results(id = "userMap",value = {
            @Result(id = true,column = "id",property = "userId"),
            @Result(column = "username",property = "userName"),
            @Result(column = "address",property = "userAddress"),
            @Result(column = "sex",property = "userSex"),
            @Result(column = "birthday",property = "userBirthday"),
            @Result(property = "accounts",column = "id",many = @Many(
                    select = "com.dao.IAccountDao.findAccountByUid",
                    fetchType = FetchType.DEFAULT))
    })
    List<User> findAll();


    /**
      * 根据用户 id 进行查询
      * @param userId
      * @Return com.domin.User
      * @author XiaoXin
      * @date 2019/10/20 下午 05:03
      */
    @ResultMap(value = {"userMap"})
    @Select("select * from user where id=#{id}")
    User findById(Integer userId);

    /**
      * 根据用户昵称进行模糊查询
      * @param username
      * @Return java.util.List<com.domin.User>
      * @author XiaoXin
      * @date 2019/10/20 下午 05:05
      */
    @ResultMap(value = {"userMap"})
    @Select("select * from user where username like #{username}")
    List<User> findByName(String username);

}

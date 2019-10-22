package com.dao;

import com.domain.QueryVo;
import com.domain.User;

import java.util.List;

/**
 * @author XiaoXin
 * @title: IUserDao
 * @projectName day02_01mybatisCRUD
 * @description: 用户的持久层接口
 * @date 2019/10/8 10:20
 */
public interface IUserDao {
    /**
      *
    查询所有用户
      * @Return java.util.List<com.domain.User>
      * @author XiaoXin
      * @date 2019/10/8 10:21
      */
    List<User> findAll();

    /**
      * 根据输入的 ID 查询信息
      * @param userId
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 14:51
      */
    User findById(Integer userId);

    /**
      * 根据名称模糊的查询用户的信息
      * @param name
      * @Return java.util.List<com.domain.User>
      * @author XiaoXin
      * @date 2019/10/8 15:06
      */
    List<User> findByName(String name);

    /**
      * 根据 queryVo 中的条件查询用户
      * @param vo
      * @Return int
      * @author XiaoXin
      * @date 2019/10/8 16:13
      */
    List<User> findUserByVo(QueryVo vo);

    /**
      * 根据传入参数条件
      * @param user 查询的条件，有可能有用户名，有可能有性别等等
      * @Return java.util.List<com.domain.User>
      * @author XiaoXin
      * @date 2019/10/10 16:02 
      */
    List<User> findUserByCondition(User user);

    /**
      * 根据 queryvo 中提供的 id 集合，查询用户信息
      * @param vo
      * @Return java.util.List<com.domain.User>
      * @author XiaoXin
      * @date 2019/10/10 16:36
      */
    List<User> fingUserInIds(QueryVo vo);
}
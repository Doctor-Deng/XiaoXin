package com.dao;


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
      * 查询所有用户，同时获取到用户下所有账户的信息
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

}
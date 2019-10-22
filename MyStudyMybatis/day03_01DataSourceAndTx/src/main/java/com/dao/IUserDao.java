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
      * 保存用户
      * @param user
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 11:41
      */
    void saveUser(User user);
    /**
      * 修改用户
      * @param user
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 14:37
      */
    void updateUser(User user);
    /**
      * 根据 ID 删除用户
      * @param userId
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 14:37
      */
    void delUser(Integer userId);
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
      * 查询总用户数
      * @Return int
      * @author XiaoXin
      * @date 2019/10/8 15:17
      */
    int findTotal();

    /**
      * 根据 queryVo 中的条件查询用户
      * @param vo
      * @Return int
      * @author XiaoXin
      * @date 2019/10/8 16:13
      */
    List<User> findUserByVo(QueryVo vo);
}
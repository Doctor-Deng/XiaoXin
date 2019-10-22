package com.dao;


import org.apache.ibatis.annotations.Select;

import com.domain.User;
import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {
    /**
     * 查询所有操作
     * @return
     */
    @Select("select * from user")
    List<User> findAll();
}

package com.dao;

import com.domain.Role;

import java.util.List;

/**
 * @author XiaoXin
 * @title: IRoleDao
 * @projectName day03_03manyTOmany
 * @description: TODO
 * @date 2019/10/14 10:05
 */
public interface IRoleDao {
    /**
      * 查询所有角色
      * @Return java.util.List<com.domain.Role>
      * @author XiaoXin
      * @date 2019/10/14 10:06
      */
    List<Role> findAll();
}

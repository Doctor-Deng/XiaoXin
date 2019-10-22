package com.dao;

import com.domin.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author XiaoXin
 * @title: IAccountDao
 * @projectName day04_04OneToMany
 * @description: TODO
 * @date 2019/10/20 下午 06:39
 */
public interface IAccountDao {
    /**
      * 查询所有账户，而且要获取每个账户下的信息
      * @Return java.util.List<com.domin.Account>
      * @author XiaoXin
      * @date 2019/10/20 下午 06:40
      */
    @Select("select * from account")
    @Results(id = "accountMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "money",property = "money"),
            @Result(property = "user",column = "uid",one = @One(
                    select="com.dao.IUserDao.findById",
                    fetchType = FetchType.EAGER))
    })
    List<Account> findAll();

    /**
      * 根据用户 id 查询账户信息
      * @param userId
      * @Return java.util.List<com.domin.Account>
      * @author XiaoXin
      * @date 2019/10/20 下午 07:02
      */
    @Select("select * from account where uid = #{userId}")
    List<Account> findAccountByUid(Integer userId);
}

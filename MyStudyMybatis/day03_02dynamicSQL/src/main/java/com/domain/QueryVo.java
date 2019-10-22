package com.domain;

import java.util.List;

/**
 * @author XiaoXin
 * @title: QueryVo
 * @projectName day02_01mybatisCRUD
 * @description: TODO
 * @date 2019/10/8 16:14
 */
public class QueryVo {

    private User user;
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

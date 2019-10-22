package com.domin;

/**
 * @author XiaoXin
 * @title: Account
 * @projectName day04_04OneToMany
 * @description: TODO
 * @date 2019/10/20 下午 06:37
 */
public class Account {
    private Integer id;
    private Integer uid;
    private Double money;

    //多对一的映射，一个账户只能是一个用户的
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", uid=" + uid +
                ", money=" + money +
                '}';
    }
}

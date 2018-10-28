package com.market.controller;


import com.market.dao.ShoppingCartDao;
import com.market.dao.UserDao;
import com.market.model.ShoppingCart;
import com.market.model.User;
import com.market.vo.LoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("User")
public class UserController {

    @Autowired
    private UserDao userDao;//有这个属性才能对User进行操作********why？？***********
    @Autowired
    private ShoppingCartDao shoppingCartDao;


    @RequestMapping("testadd")
    @ResponseBody
    public List<User> ad() {

        userDao.insert(new User("1", 1, 1));
        userDao.insert(new User("2", 2, 2));
        userDao.flush();
        return userDao.query(new User(null, null, null));
    }

    /***
     * 1代表无此用户，2代表有此用户密码错误，3代表密码正确游客身份，4代表密码正确商户身份
     * @param name
     * @param pwd
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public LoginResult login(String name, int pwd) {
        LoginResult loginResult = new LoginResult();
        User u1 = new User(name, null);
        List<User> a = userDao.query(u1);
        if (a.size() == 0) {
            loginResult.setEnum1(1);
            return loginResult;
        } else {
            for (User u0 : a) {//集合中的元素都取出来一遍则遍历完成
                if (u0.getPwd() == pwd && u0.getIdentity() == 1) {
                    loginResult.setEnum1(3);
                    loginResult.setId(u0.getId());
                    return loginResult;
                }
                if (u0.getPwd() == pwd && u0.getIdentity() == 2) {
                    loginResult.setEnum1(4);
                    loginResult.setId(u0.getId());
                    return loginResult;
                }

            }
            loginResult.setEnum1(2);
            return loginResult;//把集合中的元素全部遍历一遍后，密码都匹配不上，则只剩下【传入密码】不对场景
        }
    }

    @RequestMapping("register")
    @ResponseBody
    public Integer register(String name, Integer pwd, Integer identity) {
        List<User> userList = userDao.get();//重点看下这行**************
        if (name == null || pwd == null || identity == null) {
            return 1;
        }
        for (User exituser : userList) {//想想为什么这里可以放for
            if (exituser.getName().equals(name)) {
                return 1;
            }
        }
        if (identity == 2) {
            User merchant = new User();
            merchant.setIdentity(2);
            merchant.setName(name);
            merchant.setPwd(pwd);
            userDao.insert(merchant);
            return 0;
        } if (identity == 1) {
            User customer = new User();
            customer.setIdentity(1);
            customer.setName(name);
            customer.setPwd(pwd);
            userDao.insert(customer);
            //绑定一个购物车，通过customerid关联
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setCustomerid(customer.getId());
            shoppingCart.setGoodidlist(new ArrayList<>());
            shoppingCartDao.insert(shoppingCart);

            return 0;
        } else {
            return 1;
        }
    }
}

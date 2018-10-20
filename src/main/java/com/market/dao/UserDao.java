package com.market.dao;

import com.db.file.dao.BaseDao;
import com.market.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDao extends BaseDao<User>{

    /*@Override
    public List<User> query(User user) {
        return super.get().stream().filter(u -> {
            boolean eq = true;
            if (user.getName() != null) {
                eq = eq & user.getName().equals(u.getName());
            }
            return eq;
        }).collect(Collectors.toList());
    }*/
}

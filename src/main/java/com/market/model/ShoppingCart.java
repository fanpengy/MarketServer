package com.market.model;

import com.db.file.dao.BaseDao;
import com.db.file.model.BaseModel;

import java.io.Serializable;
import java.util.List;

public class ShoppingCart extends BaseModel implements Serializable {
    private Long customerid;
    private List<Long> goodidlist;

    public ShoppingCart() {
    }

    public ShoppingCart(Long customerid) {
        this.customerid = customerid;
    }

    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }

    public List<Long> getGoodidlist() {
        return goodidlist;
    }

    public void setGoodidlist(List<Long> goodidlist) {
        this.goodidlist = goodidlist;
    }
}

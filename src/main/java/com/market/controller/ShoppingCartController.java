package com.market.controller;

import com.market.dao.GoodDao;
import com.market.dao.ShoppingCartDao;
import com.market.dao.UserDao;
import com.market.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("shopping")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartDao shoppingCartDao;
    @Autowired
    private GoodDao goodDao;
    @Autowired
    private ShoppingCart shoppingCart;

    @RequestMapping("addgood")
    @ResponseBody
    public int addgood(Long goodid,Long customerid){
        if (goodid ==null || customerid ==null){
            return 1;
        }
        else {
            for (ShoppingCart shoppingCart : shoppingCartDao.get()){
                if (shoppingCart.getCustomerid().equals(customerid) ){
                    shoppingCart.getGoodidlist().add(goodid);
                }
            }
            return 0;
            //另一种方法，用传进来的customerid作为查询条件，去查询对应的购物车
            //shoppingCartDao.query(new shoppingCart(customerid)).get(0).getGoodidlist().add(goodid);
            //query方法只判断新建对象时，customer属性是否一致，对象的其他字段不做判断
        }
    }
}

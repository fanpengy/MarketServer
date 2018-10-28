package com.market.controller;

import com.market.dao.GoodDao;
import com.market.dao.UserDao;
import com.market.model.Good;
import com.market.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "Good",method = { RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS })
public class GoodController {

    @Autowired
    private GoodDao goodDao;
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "addGood",produces = "application/json")
    @ResponseBody
    public Integer addGood(@RequestBody Good good) {
        // TODO: 2018/10/23 新增时检测商户是否存在 ，不存在则不允许新增
        // TODO: 2018/10/23 业务接口要返回明确的接口 即成功或失败
        //用传入的商品的id去匹配商户信息的id，如果为空，则不可以添加，不为空则可添加
        User u = new User(good.getsId());
        List<User> ls = userDao.query(u);
        if (ls.size() == 0) {
            return 1;//1返回失败
        } else if(u.getIdentity() ==1){
            return 1;
        }else{
            goodDao.insert(good);
            goodDao.flush();
            return 0;//0返回成功
        }
    }

    @RequestMapping("queryList")
    @ResponseBody
    public List<Good> queryList(Long id){
        return goodDao.query(new Good(id));
    }

    @RequestMapping("editGood")
    @ResponseBody
    public Integer editGood(@RequestBody Good good){
        // TODO: 2018/10/23 这里要做逻辑判断，所属商户不准修改 可以先用id查询原来数据，再看是否更改了sid
        // TODO: 2018/10/23 业务接口要返回明确的接口 即成功或失败
        Good g1 = goodDao.queryById(good.getId());
        if (g1.getsId() != good.getsId()){
            return 1;
        }
        else{
            goodDao.update(g1,good);
            return 0;
        }
    }
}

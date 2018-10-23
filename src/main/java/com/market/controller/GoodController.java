package com.market.controller;

import com.market.dao.GoodDao;
import com.market.model.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "Good",method = { RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS })
public class GoodController {

    @Autowired
    private GoodDao goodDao;

    @RequestMapping(value = "addGood",produces = "application/json")
    @ResponseBody
    public void addGood(@RequestBody Good good){
        // TODO: 2018/10/23 新增时检测商户是否存在 ，不存在则不允许新增
        // TODO: 2018/10/23 业务接口要返回明确的接口 即成功或失败
        goodDao.insert(good);
        goodDao.flush();
    }

    @RequestMapping("queryList")
    @ResponseBody
    public List<Good> queryList(Long id){
        return goodDao.query(new Good(id));
    }

    @RequestMapping("editGood")
    @ResponseBody
    public void editGood(@RequestBody Good good){
        // TODO: 2018/10/23 这里要做逻辑判断，所属商户不准修改 可以先用id查询原来数据，再看是否更改了sid
        // TODO: 2018/10/23 业务接口要返回明确的接口 即成功或失败
        goodDao.deleteById(good.getsId());
        goodDao.insertWithId(good);
    }
}

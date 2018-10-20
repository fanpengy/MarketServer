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
        goodDao.deleteById(good.getsId());
        goodDao.insertWithId(good);
    }
}

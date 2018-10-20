package com.market.controller;

import com.market.dao.PersonDao;
import com.market.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("first")
public class FirstController {

    @Autowired
    private PersonDao personDao;

    @RequestMapping("one")
    @ResponseBody
    public Person test(String name) {
        personDao.insert(new Person(name));
        personDao.flush();
        return personDao.query(new Person(null)).get(0);
    }
}

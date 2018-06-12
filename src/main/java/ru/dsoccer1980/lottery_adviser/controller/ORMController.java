package ru.dsoccer1980.lottery_adviser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


import ru.dsoccer1980.lottery_adviser.model.Numbers;
import ru.dsoccer1980.lottery_adviser.service.ORMService;


import java.util.List;


@Controller
public class ORMController {

    @Autowired
    private ORMService ormService;

    @RequestMapping(value = "/ormFindAllNumbers", method= RequestMethod.GET)
    public ModelAndView ormFindAllUsers() {
        System.out.println("ORMController ormFindAllNumbers is called");
        List<Numbers> numbers = ormService.queryFindAllNumbers();
        return new ModelAndView("/orm", "resultObject", numbers);
    }

}



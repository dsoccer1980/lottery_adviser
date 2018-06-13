package ru.dsoccer1980.lottery_adviser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import ru.dsoccer1980.lottery_adviser.model.Numbers;
import ru.dsoccer1980.lottery_adviser.service.ORMService;
import javax.servlet.http.HttpServletRequest;



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

    @RequestMapping(value = "/addNumbers", method= RequestMethod.POST)
    public  String ormAddNumbers(HttpServletRequest request) throws UnsupportedEncodingException {
        System.out.println("ORMController ormAddNumbers is called");
        List<Integer> list = new ArrayList<>();
        list.add(Integer.parseInt(request.getParameter("draw_number")));
        list.add(Integer.parseInt(request.getParameter("number1")));
        list.add(Integer.parseInt(request.getParameter("number2")));
        list.add(Integer.parseInt(request.getParameter("number3")));
        list.add(Integer.parseInt(request.getParameter("number4")));
        list.add(Integer.parseInt(request.getParameter("number5")));
        list.add(Integer.parseInt(request.getParameter("number6")));
        ormService.ormAddNumbers(list);

        return "redirect:/ormFindAllNumbers";
    }

}



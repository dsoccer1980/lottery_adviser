package ru.dsoccer1980.lottery_adviser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.dsoccer1980.lottery_adviser.model.Numbers;
import ru.dsoccer1980.lottery_adviser.service.ORMService;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ORMController {

    @Autowired
    private ORMService ormService;

    @RequestMapping(value = "/ormFindAllNumbers", method = RequestMethod.GET)
    public ModelAndView findAllUsers() {
        System.out.println("ORMController findAllNumbers is called");
        Map<Integer, List<Numbers>> numbers = ormService.queryFindAllNumbers();
        return new ModelAndView("/orm", "resultObject", numbers);
    }

    @RequestMapping(value = "/addNumbers", method = RequestMethod.POST)
    public String addNumbers(HttpServletRequest request) throws UnsupportedEncodingException {
        System.out.println("ORMController addNumbers is called");
        List<Integer> list = new ArrayList<>();
        list.add(Integer.parseInt(request.getParameter("draw_number")));
        list.add(Integer.parseInt(request.getParameter("number1")));
        list.add(Integer.parseInt(request.getParameter("number2")));
        list.add(Integer.parseInt(request.getParameter("number3")));
        list.add(Integer.parseInt(request.getParameter("number4")));
        list.add(Integer.parseInt(request.getParameter("number5")));
        list.add(Integer.parseInt(request.getParameter("number6")));
        ormService.addNumbers(list);

        return "redirect:/ormFindAllNumbers";
    }

    @RequestMapping(value = "/deleteDraw/id/{id}", method = RequestMethod.GET)
    public String drawDelete(@PathVariable(value = "id") int drawNumber, HttpServletRequest request) {
        System.out.println("ORMController drawDelete is called");
        ormService.drawDelete(drawNumber);

        return "redirect:/ormFindAllNumbers";
    }

    @RequestMapping(value = "/populateDb", method = RequestMethod.GET)
    public String populateDb(HttpServletRequest request) {
        System.out.println("ORMController populateDb is called");
        ormService.populateDb();

        return "redirect:/ormFindAllNumbers";
    }

    @RequestMapping(value = "/numbersAverageAppearance", method = RequestMethod.GET)
    public ModelAndView numbersAverageAppearance(HttpServletRequest request) {
        System.out.println("ORMController numbersAverageAppearance is called");
        Map<Integer, Integer> result = ormService.numbersAverageAppearance();

        return new ModelAndView("/averageAppearance", "resultObject", result);
    }

}



package com.fang.alpha.controller;

import com.fang.alpha.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/")
public class PageController {
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping(value = "/main/")
    public String test(){
        return "开始";
    }

    @GetMapping(value = "/login/")
    public ModelAndView login(Model model){
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @GetMapping(value = "/register/")
    public ModelAndView register(Model model){
        ModelAndView modelAndView = new ModelAndView("register");
        return modelAndView;
    }

    @GetMapping(value = "/zone/")
    public ModelAndView zone(Model model){
        ModelAndView modelAndView = new ModelAndView("zone");
        return modelAndView;
    }
    @GetMapping(value = "/")
    public ModelAndView index(Model model){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @GetMapping(value = "/logout/")
    public void logout(HttpServletRequest request,HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        session.setAttribute("api_key",null);
        response.sendRedirect("/login/");
    }
    @GetMapping(value = "/test/")
    public ModelAndView test(Model model){
        return new ModelAndView("test");
    }

    @GetMapping(value = "/video/")
    public ModelAndView video(Model model){return new ModelAndView("video");}
}

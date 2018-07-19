package com.fang.alpha.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.fang.alpha.bean.Response;
import com.fang.alpha.exception.FileException;
import com.fang.alpha.exception.LoginException;
import com.fang.alpha.exception.SigninException;
import com.sun.media.sound.JARSoundbankReader;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@ControllerAdvice
public class ErrorController implements ApplicationContextAware{
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException{
        this.context = context;
    }

    @ExceptionHandler
    @ResponseJSONP
    public Response loginExceptionHandler(Exception exception, HttpServletRequest req, HttpServletResponse rsp) throws Exception{
        Response response = new Response(500,"unknown err");
        int code;
        if (exception instanceof LoginException){
            LoginException loginException = (LoginException)exception;
            switch (loginException.getErrorCode()){
                case LoginException.NotLogin:
                    rsp.sendRedirect("/login/");
                    System.out.println("未登录");
                    break;
            }
            response.setCode(loginException.getErrorCode());
            response.setMsg(loginException.getMessage());
            response.setData("");
        }else if (exception instanceof SigninException) {
            SigninException signinException = ((SigninException)exception);
            response.setCode(signinException.getErrorCode());
            response.setMsg(signinException.getMessage());
            response.setData("");
        }else if (exception instanceof FileException){
            FileException fileException = (FileException)exception;
            response.setCode(fileException.getErrorCode());
            response.setMsg(fileException.getMessage());
            response.setData("");
        }
        System.out.println(JSON.toJSONString(response));
        return response;
    }

    private void notLogin(HttpServletResponse response) throws Exception{
        response.sendRedirect("/login/");
    }
}
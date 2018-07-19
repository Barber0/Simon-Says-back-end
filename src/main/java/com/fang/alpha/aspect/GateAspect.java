package com.fang.alpha.aspect;

import com.fang.alpha.dao.UserRole;
import com.fang.alpha.exception.LoginException;
import com.fang.alpha.repository.UserRepository;
import com.fang.alpha.repository.UserRoleRepository;
import com.fang.alpha.utils.Encrypt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class GateAspect {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Value("${project.keyName}")
    private String keyName;

    @Pointcut("execution(public * com.fang.alpha.controller.PageController.zone(..))")
    public void action(){}

    @Before("action()")
    public void doBefore(JoinPoint joinpoint) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String key = (String) request.getSession().getAttribute(keyName);

        if (key==null) throw new LoginException(LoginException.NotLogin,"尚未登录");
        String[] keys = key.split("---");
        UserRole user = userRoleRepository.findByUsername(keys[0]);
        if (user==null)throw new LoginException(LoginException.NoSuchUser,"用户不存在");

        System.out.println("chk: "+request.getSession().getId()+"  "+user.getRole());
        System.out.println(keys[1]);
        System.out.println(Encrypt.generateKey(user.getUsername(),user.getRole(),request.getSession().getId()));
        if (!key.equals(Encrypt.generateKey(user.getUsername(),user.getRole(),request.getSession().getId())))throw new LoginException(LoginException.TokenWrong,"登录状态失效");
    }
}

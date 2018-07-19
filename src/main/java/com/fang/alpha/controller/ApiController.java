package com.fang.alpha.controller;

import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.fang.alpha.bean.UserBody;
import com.fang.alpha.bean.Response;
import com.fang.alpha.dao.Role;
import com.fang.alpha.dao.User;
import com.fang.alpha.dao.UserRole;
import com.fang.alpha.exception.LoginException;
import com.fang.alpha.exception.SigninException;
import com.fang.alpha.repository.RoleRepository;
import com.fang.alpha.repository.UserRepository;
import com.fang.alpha.repository.UserRoleRepository;
import com.fang.alpha.utils.Encrypt;
import com.fang.alpha.utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/api/")
public class ApiController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @PostMapping(value = "/login/")
    @ResponseJSONP
    public Response login(
            @RequestBody UserBody userBody,
            HttpServletRequest request){
        UserRole user = userRoleRepository.findByUsername(userBody.getUname());
        if (user==null) throw new LoginException(LoginException.NoSuchUser,"用户不存在");
        if (!user.getRole().equals(userBody.getRole())) throw new LoginException(LoginException.WrongRole,"dddd");

        String pass_checked = Encrypt.getSHA256StrJava(Encrypt.getSHA256StrJava(userBody.getPass())+user.getSalt());

        HttpSession session = request.getSession();
        String key = Encrypt.generateKey(user.getUsername(),user.getRole(),session.getId());

        session.setAttribute("api_key",key);

        if (pass_checked.equals(user.getPassword())){
            return new Response(200, key,"登录成功");
        }else {
            throw new LoginException(LoginException.UnameOrPassWrong,"用户名或密码错误");
        }
    }

    @GetMapping(value = "/logout/")
    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("api_key",null);
    }

    @PostMapping(value = "/register/")
    @ResponseJSONP
    public Response register(
            @RequestBody UserBody userBody,
            HttpServletRequest request){

        if (userBody.getUname().trim().equals("") ||
                userBody.getPass().trim().equals("") ||
                userBody.getCheck_pass().trim().equals("") ||
                userBody.getEmail().trim().equals("")) throw new SigninException(SigninException.ArgsExpected,"各项信息不能为空");
        if (!userBody.getPass().equals(userBody.getCheck_pass())) throw new SigninException(SigninException.CheckPassWrong,"验证密码不相同");

        if (!RandomString.checkEmail(userBody.getEmail())) throw new SigninException("邮箱验证失败");

        User user = userRepository.findByUsername(userBody.getUname());
        if (user!=null) throw new SigninException(SigninException.UserExisted,"用户已存在");
            else user = new User();

        if (userRepository.findByEmail(userBody.getEmail())!=null) throw new SigninException("邮箱已被使用");

        Role role = roleRepository.findByName("user");

        user.setUsername(userBody.getUname());
        user.setNickname(user.getUsername());
        String password = userBody.getPass();
        String salt = RandomString.getString(6);

        password = Encrypt.getSHA256StrJava(Encrypt.getSHA256StrJava(password)+salt);

        user.setPassword(password);
        user.setSalt(salt);
        user.setRole(role.getId());

        user.setEmail(userBody.getEmail());
        user.setHeader("/static/default_header.jpg");
        user.setIntro("我在这里寻找最皮的弹幕");

        HttpSession session = request.getSession();
        String key = Encrypt.generateKey(user.getUsername(),role.getName(),session.getId());
        session.setAttribute("api_key",key);


        if (userRepository.save(user)==null){
            throw new SigninException("注册失败");
        }else {
            return new Response(200,key,"注册成功");
        }
    }
}

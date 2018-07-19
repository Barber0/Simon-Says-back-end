package com.fang.alpha.controller;

import com.alibaba.fastjson.JSON;
import com.fang.alpha.bean.ChPassBody;
import com.fang.alpha.bean.Response;
import com.fang.alpha.dao.User;
import com.fang.alpha.exception.LoginException;
import com.fang.alpha.repository.UserRepository;
import com.fang.alpha.repository.UserRoleRepository;
import com.fang.alpha.repository.VideoRepository;
import com.fang.alpha.utils.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

@RestController
@RequestMapping("/api/user/")
@CrossOrigin
public class UserController {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Value("${project.keyName}")
    String keyName;

    @Value("${project.storePath}")
    String storePath;

    @GetMapping(value = "/")
    public Response fetchUserInfo(HttpServletRequest request){
        HttpSession session = request.getSession();
        String uname = Encrypt.getUnameFromKey((String) session.getAttribute(keyName));
        User user = userRepository.fetchUserByUsername(uname);
        if (user==null) return new Response(LoginException.NoSuchUser,"no such user");
        return new Response(200,user,"suc");
    }

    @GetMapping(value = "/{id}")
    public Response fetchUserInfo(@PathVariable("id") String id){
        User user = userRepository.fetchUserById(Integer.parseInt(id));
        if (user==null) throw new  LoginException(LoginException.NoSuchUser,"用户不存在");
        return new Response(200,user,"suc");
    }

    @PutMapping(value = "/")
    public Response updateUser(@RequestBody User user){

        int row = userRepository.updateUserById(user.getId(),user.getNickname(),user.getEmail(),user.getIntro(),user.getHeader());

        if (row>0) return new Response(200,user,"suc");
        else return new Response(666,row,"失败");
    }

    @PostMapping(value = "/ch/")
    public Response chPass(@RequestBody ChPassBody chPassBody,HttpServletRequest request){
        HttpSession session = request.getSession();
        String uname = Encrypt.getUnameFromKey((String) session.getAttribute(keyName));
        User user = userRepository.findByUsername(uname);
        if (!chPassBody.getNew_pass().equals(chPassBody.getCheck_pass())) throw new LoginException(LoginException.FailedToChPass,"验证密码不正确");
        if (!Encrypt.getSHA256StrJava(Encrypt.getSHA256StrJava(chPassBody.getOrigin())+user.getSalt()).equals(user.getPassword())) throw new LoginException(LoginException.FailedToChPass,"原密码错误");
        user.setPassword(Encrypt.getSHA256StrJava(Encrypt.getSHA256StrJava(chPassBody.getNew_pass())+user.getSalt()));
        if (userRepository.save(user)==null) throw new LoginException(LoginException.FailedToChPass,"修改错误");
        return new Response(200,"修改成功");
    }
}

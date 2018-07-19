package com.fang.alpha.controller;

import com.fang.alpha.bean.Response;
import com.fang.alpha.dao.Danmaku;
import com.fang.alpha.dao.DmUser;
import com.fang.alpha.repository.DanmakuRepository;
import com.fang.alpha.repository.DmUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/danmaku/")
public class DanmakuController {
    @Autowired
    private DanmakuRepository danmakuRepository;

    @Autowired
    private DmUserRepository dmUserRepository;

    @GetMapping(value = "/v3")
    public Response getDanmaku(@RequestParam("id") int id){
        List<DmUser> list = dmUserRepository.list(id);
        return new Response(0,list,"获取弹幕成功");
    }

    @PostMapping(value = "/v3")
    public Response postDanmaku(@RequestBody Danmaku danmaku){
        if (danmakuRepository.save(danmaku)==null)
            return new Response(-666,"添加失败");
        else return new Response(200,"弹幕发送成功");
    }
}

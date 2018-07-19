package com.fang.alpha.controller;

import com.fang.alpha.bean.Response;
import com.fang.alpha.dao.Sort;
import com.fang.alpha.repository.SortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/common/")
@CrossOrigin
public class CommonController {

    @Autowired
    private SortRepository sortRepository;

    @GetMapping("/sort/")
    public Response getSort(){
        List<Sort> sorts = sortRepository.findAll();
        return new Response(200,sorts,"suc");
    }
}

package com.jiessie.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jiessie.facade.domain.DubboUser;
import com.jiessie.facade.service.DubooService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/dubbo")
public class DubboController {

    @Reference(version = "1.0.0", timeout = 20000, retries = 0)
    private DubooService dubooService;

    @GetMapping(value = "/getName")
    public String getName(@RequestParam String name){

        return dubooService.getName(null);
    }
    @GetMapping(value = "/getuser")
    public List<DubboUser> getUser(){
        return dubooService.findAllUser();
    }
}

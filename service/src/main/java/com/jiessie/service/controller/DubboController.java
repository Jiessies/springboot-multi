package com.jiessie.service.controller;

import com.jiessie.facade.domain.DubboUser;
import com.jiessie.facade.service.DubooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/dubbo")
public class DubboController {

    @Autowired
    private DubooService dubooService;

    @GetMapping(value = "/getName")
    public String getName(@RequestParam String name) {

        return dubooService.getName(null);
    }

    @GetMapping(value = "/getuser")
    public List<DubboUser> getUser() {
        return dubooService.findAllUser();
    }
}

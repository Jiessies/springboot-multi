package com.jiessie.service.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jiessie.facade.domain.DubboUser;
import com.jiessie.facade.service.DubooService;
import com.jiessie.service.mapper.DubooMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Service(interfaceClass = DubooService.class, version = "1.0.0", timeout = 20000, retries = 0)
public class DubooServiceImpl implements DubooService {

    @Autowired
    private DubooMapper dubooMapper;

    @Override
    public String getName(String name) {
        return "zhangjie";
    }

    @Override
    public List<DubboUser> findAllUser() {
        return dubooMapper.findAllUser();
    }
}

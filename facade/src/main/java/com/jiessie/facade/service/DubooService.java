package com.jiessie.facade.service;


import com.jiessie.facade.domain.DubboUser;

import java.util.List;

public interface DubooService {
    public String getName(String name);
    public List<DubboUser> findAllUser();
}

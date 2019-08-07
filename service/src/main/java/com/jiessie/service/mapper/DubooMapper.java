package com.jiessie.service.mapper;


import com.jiessie.facade.domain.DubboUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DubooMapper {

    @Select({"select * from user"})
    public List<DubboUser> findAllUser();

    void saveDeptBatch(List<DubboUser> deptList);
}

package com.jiessie.service;

import com.alibaba.fastjson.JSON;
import com.jiessie.facade.domain.DubboUser;
import com.jiessie.service.mapper.DubooMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ActiveProfiles("unit")
//@ContextConfiguration(classes = {ServiceApplication.class}, loader = SpringBootContextLoader.class)
public class ServiceApplicationTests {


    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void contextLoads() {
    }

    @Test
    public void mybatisBatch() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            DubooMapper dubooMapper = session.getMapper(DubooMapper.class);
            long start = System.currentTimeMillis();
            /*for (int i = 0; i <10000 ; i++) {
                SysDept dept=new SysDept(UUID.randomUUID().toString().substring(1,6), 1, new Date(),  new Date(), 1);
                deptMapper.saveSysDept(dept);
            }*/
            List<DubboUser> dubboUserList = dubooMapper.findAllUser();
            long end = System.currentTimeMillis();
            System.out.println(JSON.toJSONString(dubboUserList) + "耗时:" + (end - start));
            //ExecutorType.BATCH 批量耗时耗时:2134
            //单条操作耗时 耗时:8584
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.commit();
            session.close();
        }
    }


    @Test
    public void saveDeptBatchTwo() {
        //设置ExecutorType.BATCH原理：把SQL语句发个数据库，数据库预编译好，数据库等待需要运行的参数，接收到参数后一次运行，ExecutorType.BATCH只打印一次SQL语句，多次设置参数步骤，
//        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            DubooMapper dubooMapper = session.getMapper(DubooMapper.class);
            long start = System.currentTimeMillis();
            List<DubboUser> deptList = new ArrayList<>();
            for (int i = 0; i < 1; i++) {
                DubboUser dubboUser = new DubboUser();
                dubboUser.setPassword("1111");
                dubboUser.setPhone("13144445555");
                dubboUser.setUserName("huangmingjie");
                deptList.add(dubboUser);
                if (i != 0 && i % 500 == 0) {
                    dubooMapper.saveDeptBatch(deptList);
                    deptList.clear();
                }
            }
            dubooMapper.saveDeptBatch(deptList);
            long end = System.currentTimeMillis();
            System.out.println("耗时:" + (end - start));
            //BATCH批量耗时 耗时:822
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.commit();
            session.close();
        }
    }

    @Test
    public void saveDeptBatchOne() {
        //设置ExecutorType.BATCH原理：把SQL语句发个数据库，数据库预编译好，数据库等待需要运行的参数，接收到参数后一次运行，ExecutorType.BATCH只打印一次SQL语句，多次设置参数步骤，
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try {
            DubooMapper dubooMapper = session.getMapper(DubooMapper.class);
            long start = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                DubboUser dubboUser = new DubboUser();
                dubboUser.setPassword("1111");
                dubboUser.setPhone("13144445555");
                dubboUser.setUserName("huangmingjie");
                dubooMapper.saveDept(dubboUser);
                if (i != 0 && i % 1000 == 0) {
                    session.commit();
                    session.clearCache();
                }
            }
            session.commit();
            session.clearCache();
            long end = System.currentTimeMillis();
            System.out.println("耗时:" + (end - start));
            //BATCH批量耗时 耗时:822
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}

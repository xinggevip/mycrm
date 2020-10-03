package com.xinggevip;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xinggevip.dao.UserMapper;
import com.xinggevip.domain.*;
import com.xinggevip.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Transactional
class MycrmApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private EmpService empService;

    @Autowired
    private AptService aptService;

    @Autowired
    private ChargeService chargeService;

    @Autowired
    private PaytypeService paytypeService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private SourceService sourceService;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Test
    void contextLoads() {
        System.out.println("hello");
        User user = userService.findById(1L);
        System.out.println(user);

        Emp emp = empService.findById(1L);
        System.out.println(emp);

        Apt apt = aptService.findById(1L);
        System.out.println(apt);

        Charge charge = chargeService.findById(1L);
        System.out.println(charge);

        IPage<Paytype> ipageofpaytype = paytypeService.findListByPage(1, 10);
        List<Paytype> paytypeList = ipageofpaytype.getRecords();
        for (Paytype paytype : paytypeList) {
            System.out.println(paytype);
        }

        Room room = roomService.findById(1L);
        System.out.println(room);

        Source source = sourceService.findById(1L);
        System.out.println(source);

        Date date = new Date();
        System.out.println(date);


    }

    @Test
    void test2() {
        List<User> userList = userService.lambdaQuery()
                .like(User::getUsername, null)
                .like(User::getPhonenumber, "159")
                .list();
        userList.forEach(System.out::println);
    }

    @Test
    void test3() {

        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery()
                .like(User::getUsername, "")
                .like(User::getPhonenumber, "159");

        Page<User> page = new Page<User>(1, 3);

        IPage<User> userIPage = userMapper.selectPage(page, wrapper);
        List<User> userList = userIPage.getRecords();
        for (User user : userList) {
            System.out.println(user);
        }

    }

    @Test
    void test4() {
        String orderBy = "id  desc";//按照排序字段 倒序 排序
        com.github.pagehelper.Page<Map> pageIn = PageHelper.startPage(1, 2, orderBy);
        List<Map> maps = userMapper.selectUserListByKeyWord("159");
        for (Map map : maps) {
            System.out.println(map);
        }

        PageInfo<Map> pageInfo = new PageInfo<>(maps, 3);



    }

}

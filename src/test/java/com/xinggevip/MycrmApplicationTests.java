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
import com.xinggevip.dao.ChargeMapper;
import com.xinggevip.dao.PaytypeMapper;
import com.xinggevip.dao.UserMapper;
import com.xinggevip.domain.*;
import com.xinggevip.service.*;
import com.xinggevip.vo.CountInfo;
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
    private PaytypeMapper paytypeMapper;

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

    @Autowired
    private ChargeMapper chargeMapper;

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

    @Test
    void test5() {
        List<Paytype> paytypeList = paytypeMapper.selectList(null);
        for (Paytype paytype : paytypeList) {
            System.out.println(paytype);
        }
    }

    @Test
    void test6() {
        com.xinggevip.vo.Page page = new com.xinggevip.vo.Page();
        page.setKeyword("女士");
        page.setPageNum(1);
        page.setPageSize(10);
        page.setStatus(0);

        PageInfo<Map> mapPageInfo = aptService.selectAptListByKeyword(page);
        List<Map> list = mapPageInfo.getList();
        for (Map map : list) {
            System.out.println(map);
        }
    }

    @Test
    void test7() {
        com.xinggevip.vo.Page page = new com.xinggevip.vo.Page();
        page.setKeyword("159");
        page.setPageNum(1);
        page.setPageSize(10);
        page.setPaytypeid(1);
        page.setSourceid(2);
//        page.setStarttime(new Date(1601481600000L));
//        page.setEndtime(new Date(1601740800000L));
//        page.setRoomid(1);

        // 不指定
        page.setFlag(3);
        page.setRoomid(2);

        PageInfo pageInfo = chargeService.selectChargeListByKeyword(page);
        List<Map> list = pageInfo.getList();
        for (Map map : list) {
            System.out.println(map);
        }


    }

    @Test
    void test8() {
        com.xinggevip.vo.Page page = new com.xinggevip.vo.Page();
        page.setKeyword("");

        CountInfo countInfo = chargeService.getCountInfo(page);
        System.out.println(countInfo);

    }


    @Test
    void test9() {
        Apt apt = aptService.getById(1);
        Date starttime = apt.getStarttime();
        Date endtime = apt.getEndtime();

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endtime.getTime() - starttime.getTime();
        System.out.println(endtime);
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        System.out.println(day + "天" + hour + "小时" + min + "分钟" + sec + "秒");

        Long subRes = day * nd + hour * nh + min * nm + sec * ns;
        // 把apt的开始时间更新为现在的时间
        // 把结束时间更新为更新现在的时间加上相差的时间
        Date date = new Date(new Date().getTime() + subRes);
        System.out.println(date);


    }



}

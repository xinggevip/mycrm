package com.xinggevip.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinggevip.dao.ChargeMapper;
import com.xinggevip.dao.UserMapper;
import com.xinggevip.domain.Charge;
import com.xinggevip.domain.User;
import com.xinggevip.service.ChargeService;
import com.xinggevip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xinggevip
 * @since 2020-10-02
 */
@Service
public class ChargeServiceImpl extends ServiceImpl<ChargeMapper, Charge> implements ChargeService {

    @Autowired
    private UserService userService;

    @Override
    public IPage<Charge> findListByPage(Integer page, Integer pageCount){
        IPage<Charge> wherePage = new Page<>(page, pageCount);
        Charge where = new Charge();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Charge charge){
        Date date = new Date();
        charge.setCreatedate(date);


        int res = userService.updateMoney(charge.getUserid(), charge.getMoneynum());
        if (res == 0) {
            return 0;
        }

        return baseMapper.insert(charge);
    }

    @Override
    public int notSubMoney(Charge charge) {
        Date date = new Date();
        charge.setCreatedate(date);

        return baseMapper.insert(charge);
    }


    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Charge charge){
        return baseMapper.updateById(charge);
    }

    @Override
    public Charge findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public PageInfo selectChargeListByKeyword(com.xinggevip.vo.Page page) {
        String keyword = page.getKeyword();
        if (keyword == null) keyword = "";

        Integer pageNum = page.getPageNum();
        Integer pageSize = page.getPageSize();
        Date starttime = page.getStarttime();
        Date endtime = page.getEndtime();
        Integer roomid = page.getRoomid();

        String orderBy = "t_charge.id  desc";

        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Map> maps = baseMapper.selectChargeListByKeyword(keyword, starttime, endtime, roomid);

        return new PageInfo<>(maps, 5);
    }
}

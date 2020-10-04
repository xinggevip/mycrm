package com.xinggevip.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}

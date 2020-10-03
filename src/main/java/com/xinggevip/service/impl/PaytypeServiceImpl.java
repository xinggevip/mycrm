package com.xinggevip.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinggevip.dao.PaytypeMapper;
import com.xinggevip.domain.Paytype;
import com.xinggevip.service.PaytypeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xinggevip
 * @since 2020-10-02
 */
@Service
public class PaytypeServiceImpl extends ServiceImpl<PaytypeMapper, Paytype> implements PaytypeService {

    @Override
    public IPage<Paytype> findListByPage(Integer page, Integer pageCount){
        IPage<Paytype> wherePage = new Page<>(page, pageCount);
        Paytype where = new Paytype();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Paytype paytype){
        return baseMapper.insert(paytype);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Paytype paytype){
        return baseMapper.updateById(paytype);
    }

    @Override
    public Paytype findById(Long id){
        return  baseMapper.selectById(id);
    }
}

package com.xinggevip.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinggevip.dao.AptMapper;
import com.xinggevip.domain.Apt;
import com.xinggevip.service.AptService;
import org.springframework.stereotype.Service;

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
public class AptServiceImpl extends ServiceImpl<AptMapper, Apt> implements AptService {

    @Override
    public IPage<Apt> findListByPage(Integer page, Integer pageCount){
        IPage<Apt> wherePage = new Page<>(page, pageCount);
        Apt where = new Apt();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Apt apt){
        Date date = new Date();
        apt.setCreatetime(date);
        apt.setStatus(0);
        return baseMapper.insert(apt);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Apt apt){
        return baseMapper.updateById(apt);
    }

    @Override
    public Apt findById(Long id){
        return  baseMapper.selectById(id);
    }
}

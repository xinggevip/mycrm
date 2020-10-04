package com.xinggevip.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinggevip.dao.SourceMapper;
import com.xinggevip.domain.Source;
import com.xinggevip.service.SourceService;
import org.springframework.stereotype.Service;

import javax.crypto.spec.PSource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xinggevip
 * @since 2020-10-02
 */
@Service
public class SourceServiceImpl extends ServiceImpl<SourceMapper, Source> implements SourceService {

    @Override
    public IPage<Source> findListByPage(Integer page, Integer pageCount){
        IPage<Source> wherePage = new Page<>(page, pageCount);
        Source where = new Source();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Source source){
        return baseMapper.insert(source);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Source source){
        return baseMapper.updateById(source);
    }

    @Override
    public Source findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public List<Source> findAll() {
        return baseMapper.selectList(null);
    }
}

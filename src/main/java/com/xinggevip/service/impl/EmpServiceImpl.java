package com.xinggevip.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.xinggevip.dao.EmpMapper;
import com.xinggevip.domain.Emp;
import com.xinggevip.service.EmpService;
import org.springframework.stereotype.Service;

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
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {

    @Override
    public IPage<Emp> findListByPage(Integer page, Integer pageCount){
        IPage<Emp> wherePage = new Page<>(page, pageCount);
        Emp where = new Emp();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Emp emp){
        return baseMapper.insert(emp);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Emp emp){
        return baseMapper.updateById(emp);
    }

    @Override
    public Emp findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public List<Emp> selectAllEmpList() {

        return baseMapper.selectList(null);
    }


}

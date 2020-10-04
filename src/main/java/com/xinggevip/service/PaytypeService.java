package com.xinggevip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinggevip.domain.Paytype;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xinggevip
 * @since 2020-10-02
 */
public interface PaytypeService extends IService<Paytype> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Paytype>
     */
    IPage<Paytype> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param paytype
     * @return int
     */
    int add(Paytype paytype);

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改
     *
     * @param paytype
     * @return int
     */
    int updateData(Paytype paytype);

    /**
     * id查询数据
     *
     * @param id id
     * @return Paytype
     */
    Paytype findById(Long id);

    List<Paytype> findAll();
}

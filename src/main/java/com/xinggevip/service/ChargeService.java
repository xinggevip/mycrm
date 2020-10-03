package com.xinggevip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinggevip.domain.Charge;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xinggevip
 * @since 2020-10-02
 */
public interface ChargeService extends IService<Charge> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Charge>
     */
    IPage<Charge> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param charge
     * @return int
     */
    int add(Charge charge);

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
     * @param charge
     * @return int
     */
    int updateData(Charge charge);

    /**
     * id查询数据
     *
     * @param id id
     * @return Charge
     */
    Charge findById(Long id);
}

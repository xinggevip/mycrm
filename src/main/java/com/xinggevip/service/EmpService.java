package com.xinggevip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinggevip.domain.Emp;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xinggevip
 * @since 2020-10-02
 */
public interface EmpService extends IService<Emp> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Emp>
     */
    IPage<Emp> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param emp
     * @return int
     */
    int add(Emp emp);

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
     * @param emp
     * @return int
     */
    int updateData(Emp emp);

    /**
     * id查询数据
     *
     * @param id id
     * @return Emp
     */
    Emp findById(Long id);

}

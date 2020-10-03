package com.xinggevip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinggevip.domain.Apt;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xinggevip
 * @since 2020-10-02
 */
public interface AptService extends IService<Apt> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Apt>
     */
    IPage<Apt> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param apt
     * @return int
     */
    int add(Apt apt);

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
     * @param apt
     * @return int
     */
    int updateData(Apt apt);

    /**
     * id查询数据
     *
     * @param id id
     * @return Apt
     */
    Apt findById(Long id);
}

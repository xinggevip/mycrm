package com.xinggevip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinggevip.domain.Source;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xinggevip
 * @since 2020-10-02
 */
public interface SourceService extends IService<Source> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Source>
     */
    IPage<Source> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param source
     * @return int
     */
    int add(Source source);

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
     * @param source
     * @return int
     */
    int updateData(Source source);

    /**
     * id查询数据
     *
     * @param id id
     * @return Source
     */
    Source findById(Long id);
}

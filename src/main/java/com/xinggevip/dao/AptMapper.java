package com.xinggevip.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinggevip.domain.Apt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xinggevip
 * @since 2020-10-02
 */
public interface AptMapper extends BaseMapper<Apt> {
    List<Map> selectAptListByKeyword(@Param("keyword") String keyword, @Param("status") Integer status);
}

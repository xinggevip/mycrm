package com.xinggevip.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinggevip.domain.Charge;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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
public interface ChargeMapper extends BaseMapper<Charge> {
    List<Map> selectChargeListByKeyword(@Param("keyword") String keyword, @Param("starttime") Date starttime, @Param("endtime") Date endtime, @Param("roomid") Integer roomid);
}

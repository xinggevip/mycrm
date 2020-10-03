package com.xinggevip.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinggevip.domain.User;
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
public interface UserMapper extends BaseMapper<User> {
    public List<Map> selectUserListByKeyWord(@Param("keyword") String keyword);

}

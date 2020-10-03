package com.xinggevip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinggevip.domain.Room;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xinggevip
 * @since 2020-10-02
 */
public interface RoomService extends IService<Room> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Room>
     */
    IPage<Room> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param room
     * @return int
     */
    int add(Room room);

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
     * @param room
     * @return int
     */
    int updateData(Room room);

    /**
     * id查询数据
     *
     * @param id id
     * @return Room
     */
    Room findById(Long id);
}

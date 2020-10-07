package com.xinggevip.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinggevip.dao.RoomMapper;
import com.xinggevip.domain.Room;
import com.xinggevip.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {

    @Override
    public IPage<Room> findListByPage(Integer page, Integer pageCount){
        IPage<Room> wherePage = new Page<>(page, pageCount);
        Room where = new Room();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Room room){
        return baseMapper.insert(room);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Room room){
        return baseMapper.updateById(room);
    }

    @Override
    public Room findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public List<Room> findAll() {
        return baseMapper.selectList(null);
    }
}

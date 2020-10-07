package com.xinggevip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinggevip.dao.UserMapper;
import com.xinggevip.domain.User;
import com.xinggevip.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public IPage<User> findListByPage(Integer page, Integer pageCount){
        IPage<User> wherePage = new Page<>(page, pageCount);
        User where = new User();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(User user){
        Date date = new Date();
        user.setSavedate(date);
        user.setMoney(BigDecimal.valueOf(0));
        return baseMapper.insert(user);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(User user){
        return baseMapper.updateById(user);
    }

    @Override
    public User findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public IPage<User> findListByPageAndNameAndPhoneNum(Integer page, Integer pageCount, String username, String phonenumber) {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery()
                .like(User::getUsername, username)
                .like(User::getPhonenumber, phonenumber);

        Page<User> pageobj = new Page<User>(page, pageCount);


        return baseMapper.selectPage(pageobj, wrapper);
    }

    @Override
    public PageInfo<Map> selectUserListByKeyWord(Integer page, Integer pageCount, String keyword) {

        if (keyword == null) keyword = "";

        String orderBy = "id  desc";//按照排序字段 倒序 排序
        PageHelper.startPage(page, pageCount, orderBy);

        List<Map> maps = baseMapper.selectUserListByKeyWord(keyword);

        return new PageInfo<>(maps, 3);
    }

    @Override
    public int updateMoney(Integer userId, BigDecimal moneynum) {
        User user = baseMapper.selectById(userId);
        if ((user.getMoney().add(moneynum)).compareTo(BigDecimal.ZERO) == -1) {
            return 0;
        }
        user.setMoney(user.getMoney().add(moneynum));
        return baseMapper.updateById(user);
    }
}

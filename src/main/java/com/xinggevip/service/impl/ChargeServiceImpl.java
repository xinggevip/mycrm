package com.xinggevip.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinggevip.dao.AptMapper;
import com.xinggevip.dao.ChargeMapper;
import com.xinggevip.dao.UserMapper;
import com.xinggevip.domain.Charge;
import com.xinggevip.domain.User;
import com.xinggevip.exception.ServerException;
import com.xinggevip.service.AptService;
import com.xinggevip.service.ChargeService;
import com.xinggevip.service.UserService;
import com.xinggevip.vo.CountInfo;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ChargeServiceImpl extends ServiceImpl<ChargeMapper, Charge> implements ChargeService {

    @Autowired
    private UserService userService;

    @Autowired
    private AptService aptService;

    @Autowired
    private AptMapper aptMapper;

    @Override
    public IPage<Charge> findListByPage(Integer page, Integer pageCount){
        IPage<Charge> wherePage = new Page<>(page, pageCount);
        Charge where = new Charge();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Charge charge){
        Date date = new Date();
        charge.setCreatedate(date);


        int res = userService.updateMoney(charge.getUserid(), charge.getMoneynum());
        if (res == 0) {
            return 0;
        }

        return baseMapper.insert(charge);
    }

    @Override
    public int notSubMoney(Charge charge) {
        Date date = new Date();
        charge.setCreatedate(date);

        return baseMapper.insert(charge);
    }


    @Override
    public int delete(Long id){
        Charge charge = baseMapper.selectById(id);
        Integer aptid = charge.getAptid();
        int res1 = 1;
        int res2 = 0;

        res2 = baseMapper.deleteById(id);

        if (aptid != null) {
            res1 = aptMapper.deleteById(aptid);
        }
        if (res1 != 1 || res2 != 1) {
            throw new ServerException();
        }

        return 1;
    }

    @Override
    public int updateData(Charge charge){
        return baseMapper.updateById(charge);
    }

    @Override
    public Charge findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public PageInfo selectChargeListByKeyword(com.xinggevip.vo.Page page) {
        String keyword = page.getKeyword();
        if (keyword == null) keyword = "";

        Integer pageNum = page.getPageNum();
        Integer pageSize = page.getPageSize();
        Date starttime = page.getStarttime();
        Date endtime = page.getEndtime();
        Integer roomid = page.getRoomid();
        Integer flag = page.getFlag();
        Integer sourceid = page.getSourceid();
        Integer paytypeid = page.getPaytypeid();

        String orderBy = "t_charge.id  desc";

        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Map> maps = baseMapper.selectChargeListByKeyword(keyword, starttime, endtime, roomid, flag, sourceid, paytypeid);

        return new PageInfo<>(maps, 5);
    }

    @Override
    public CountInfo getCountInfo(com.xinggevip.vo.Page page) {
        String keyword = page.getKeyword();
        if (keyword == null) keyword = "";

        Date starttime = page.getStarttime();
        Date endtime = page.getEndtime();
        Integer roomid = page.getRoomid();
        Integer flag = page.getFlag();
        Integer sourceid = page.getSourceid();
        Integer paytypeid = page.getPaytypeid();

        List<Map> maps = baseMapper.selectChargeListByKeyword(keyword, starttime, endtime, roomid, flag, sourceid, paytypeid);

        CountInfo countInfo = new CountInfo();
        countInfo.setRoomMoneyNum(0);
        countInfo.setRoomMoney(BigDecimal.valueOf(0));
        countInfo.setOtherMoneyNum(0);
        countInfo.setOtherMoney(BigDecimal.valueOf(0));
        countInfo.setChongMoneyNum(0);
        countInfo.setChongMoney(BigDecimal.valueOf(0));

        for (Map map : maps) {
            BigDecimal moneynum = (BigDecimal)map.get("moneynum");
            Integer aptid = (Integer)map.get("aptid");

            int i = moneynum.compareTo(BigDecimal.ZERO);
            if(i == -1){
                //num小于0  例如：num=-10.00
                if (aptid != null) {
                    countInfo.setRoomMoneyNum(countInfo.getRoomMoneyNum() + 1);
                    countInfo.setRoomMoney(countInfo.getRoomMoney().add(moneynum.abs()));
                }else{
                    countInfo.setOtherMoneyNum(countInfo.getOtherMoneyNum() + 1);
                    countInfo.setOtherMoney(countInfo.getOtherMoney().add(moneynum.abs()));
                }
            }
            if(i == 0){
                //num等于0，  num=0.00
            }
            if(i == 1){
                //num大于0  例如：num=10.00
                countInfo.setChongMoneyNum(countInfo.getChongMoneyNum() + 1);
                countInfo.setChongMoney(countInfo.getChongMoney().add(moneynum.abs()));
            }

        }

        countInfo.setAllMoneyNum(
                countInfo.getChongMoneyNum() +
                countInfo.getRoomMoneyNum() +
                countInfo.getOtherMoneyNum()
        );

        countInfo.setAllMoney(
                countInfo.getChongMoney().
                add(countInfo.getRoomMoney()).
                add(countInfo.getOtherMoney())
        );

        return countInfo;
    }
}

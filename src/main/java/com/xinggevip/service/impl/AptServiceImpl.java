package com.xinggevip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinggevip.dao.AptMapper;
import com.xinggevip.dao.ChargeMapper;
import com.xinggevip.domain.Apt;
import com.xinggevip.domain.Charge;
import com.xinggevip.exception.EmpLoginException;
import com.xinggevip.exception.ServerException;
import com.xinggevip.service.AptService;
import com.xinggevip.service.ChargeService;
import net.bytebuddy.implementation.bytecode.Throw;
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
public class AptServiceImpl extends ServiceImpl<AptMapper, Apt> implements AptService {

    @Autowired
    private ChargeService chargeService;

    @Autowired
    private ChargeMapper chargeMapper;

    @Override
    public IPage<Apt> findListByPage(Integer page, Integer pageCount){
        IPage<Apt> wherePage = new Page<>(page, pageCount);
        Apt where = new Apt();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Apt apt){
        Date date = new Date();
        apt.setCreatetime(date);
        apt.setStatus(0);
        return baseMapper.insert(apt);
    }

    @Override
    public int delete(Long id){

        // 根据apt查找账单，如果不为null则用server删除
        QueryWrapper<Charge> wrapper = new QueryWrapper<>();
        wrapper.eq("aptid", id);
        Charge charge = chargeMapper.selectOne(wrapper);

        if (charge != null) {
            return chargeService.delete(Long.valueOf(charge.getId()));
        }

        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Apt apt){
        System.out.println(apt);
        Integer status = apt.getStatus();

        int res1 = 0;
        int res2 = 0;

        if (status.equals(0)) {
            // 更新预约信息
            return baseMapper.updateById(apt);
        } else if (status.equals(1)) {
            // 结算
            Charge charge = new Charge();
            charge.setPaytypeid(apt.getPaytypeid());
            charge.setMoneynum(apt.getPrice());
            charge.setAptid(apt.getId());
            charge.setUserid(apt.getUserid());
            charge.setEmpid(apt.getEmpid());

            int i = apt.getPrice().compareTo(BigDecimal.ZERO);
            if(i == -1){
                //num小于0  例如：num=-10.00
            }
            if(i == 0){
                //num等于0，  num=0.00
            }
            if(i == 1){
                //num大于0  例如：num=10.00
                charge.setMoneynum(apt.getPrice().negate());
            }

            // 余额支付扣除用户余额
            if (apt.getPaytypeid().equals(5)) {
                charge.setComment("余额支付，系统自动从余额扣除");
                res2 = chargeService.add(charge);
            } else {
                charge.setComment("非余额支付，仅记录");
                res2 = chargeService.notSubMoney(charge);
            }


        } else if (status.equals(2)) {
            System.out.println("来到了放弃");
            // 放弃
            return baseMapper.updateById(apt);
        }else {
            return 0;
        }

        // 更新时间
        Date starttime = apt.getStarttime();
        Date endtime = apt.getEndtime();
        Date nowTime = new Date();

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endtime.getTime() - starttime.getTime();
        System.out.println(endtime);
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
//        System.out.println(day + "天" + hour + "小时" + min + "分钟" + sec + "秒");

        Long subRes = day * nd + hour * nh + min * nm + sec * ns;

        apt.setStarttime(nowTime);
        apt.setEndtime(new Date(nowTime.getTime() + subRes));

        res1 = baseMapper.updateById(apt);
        if (res1 == 1 && res2 == 1) {
            return 1;
        }else{
            throw new ServerException();
        }
    }

    @Override
    public Apt findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public PageInfo<Map> selectAptListByKeyword(com.xinggevip.vo.Page page) {
        String keyword = page.getKeyword();
        Integer status = page.getStatus();
        if (keyword == null) keyword = "";
        if (status == null) status = 0;

        Integer pageNum = page.getPageNum();
        Integer pageSize = page.getPageSize();

        String orderBy = "t_apt.starttime  asc";//按照排序字段 倒序 排序

        if (status.equals(0)) {
            orderBy = "t_apt.starttime  asc";
        }
        if (status.equals(1) || status.equals(2)) {
            orderBy = "t_apt.starttime  desc";
        }


        PageHelper.startPage(pageNum, pageSize, orderBy);

        List<Map> maps = baseMapper.selectAptListByKeyword(keyword, status);

        return new PageInfo<>(maps, 5);
    }

    @Override
    public Map selectAptById(Integer aptid) {
        return baseMapper.selectAptById(aptid);
    }
}

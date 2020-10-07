package com.xinggevip.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.xinggevip.domain.Charge;
import com.xinggevip.enunm.ResultCodeEnum;
import com.xinggevip.service.ChargeService;
import com.xinggevip.utils.HttpResult;
import com.xinggevip.vo.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xinggevip
 * @since 2020-10-02
 */
@Api(tags = {""})
@RestController
@RequestMapping("/charge")
@CrossOrigin
public class ChargeController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ChargeService chargeService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public HttpResult add(@RequestBody Charge charge){
        int res = chargeService.add(charge);
        if (res != 1) {
            return HttpResult.failure(ResultCodeEnum.MONEY_NOT_ENOUGH_ERROR);
        }
        return HttpResult.success(ResultCodeEnum.HANDLE_SUCCESS);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public HttpResult delete(@PathVariable("id") Long id){
        int res = chargeService.delete(id);
        if (res != 1) {
            return HttpResult.failure(ResultCodeEnum.DELETE_ERROR);
        }
        return HttpResult.success(ResultCodeEnum.DELETE_SUCCESS);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Charge charge){
        return chargeService.updateData(charge);
    }

    @ApiOperation(value = "根据关键字查询分页数据")
    @PostMapping("/getlist")
    public HttpResult findListByPage(@RequestBody Page page){
        return HttpResult.success(chargeService.selectChargeListByKeyword(page));
    }

    @ApiOperation(value = "获取统计数据")
    @PostMapping("/getcount")
    public HttpResult getCount(@RequestBody Page page){
        return HttpResult.success(chargeService.getCountInfo(page));
    }


    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Charge findById(@PathVariable Long id){
        return chargeService.findById(id);
    }

}

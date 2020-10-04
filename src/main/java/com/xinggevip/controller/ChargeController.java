package com.xinggevip.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinggevip.domain.Charge;
import com.xinggevip.enunm.ResultCodeEnum;
import com.xinggevip.service.ChargeService;
import com.xinggevip.utils.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    public int delete(@PathVariable("id") Long id){
        return chargeService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Charge charge){
        return chargeService.updateData(charge);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Charge> findListByPage(@RequestParam Integer page,
                                        @RequestParam Integer pageCount){
        return chargeService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Charge findById(@PathVariable Long id){
        return chargeService.findById(id);
    }

}

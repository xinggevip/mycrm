package com.xinggevip.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinggevip.domain.Paytype;
import com.xinggevip.enunm.ResultCodeEnum;
import com.xinggevip.service.PaytypeService;
import com.xinggevip.utils.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
@RequestMapping("/paytype")
@CrossOrigin
public class PaytypeController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private PaytypeService paytypeService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public HttpResult add(@RequestBody Paytype paytype){
        int res = paytypeService.add(paytype);
        if (res != 1) {
            HttpResult<Object> httpResult = HttpResult.failure(ResultCodeEnum.ADD_ERROR);
            return httpResult;
        }
        return HttpResult.success(ResultCodeEnum.ADD_SUCCESS);
    }


    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public HttpResult delete(@PathVariable("id") Long id){
        int res = paytypeService.delete(id);
        if (res != 1) {
            HttpResult<Object> httpResult = HttpResult.failure(ResultCodeEnum.DELETE_ERROR);
            return httpResult;
        }
        return HttpResult.success(ResultCodeEnum.DELETE_SUCCESS);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public HttpResult<Object> update(@RequestBody Paytype paytype){
        int res = paytypeService.updateData(paytype);
        if (res != 1) {
            HttpResult<Object> httpResult = HttpResult.failure(ResultCodeEnum.UPDATE_ERROR);
            return httpResult;
        }
        return HttpResult.success(ResultCodeEnum.UPDATE_SUCCESS);
    }

    @ApiOperation(value = "查询所有数据")
    @GetMapping()
    public HttpResult findAll(){
        List<Paytype> paytypeList = paytypeService.findAll();
        return HttpResult.success(paytypeList);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Paytype findById(@PathVariable Long id){
        return paytypeService.findById(id);
    }

}

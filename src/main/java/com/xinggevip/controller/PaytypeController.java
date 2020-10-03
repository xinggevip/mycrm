package com.xinggevip.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinggevip.domain.Paytype;
import com.xinggevip.service.PaytypeService;
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
@RequestMapping("/paytype")
@CrossOrigin
public class PaytypeController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private PaytypeService paytypeService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody Paytype paytype){
        return paytypeService.add(paytype);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return paytypeService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Paytype paytype){
        return paytypeService.updateData(paytype);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Paytype> findListByPage(@RequestParam Integer page,
                                         @RequestParam Integer pageCount){
        return paytypeService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Paytype findById(@PathVariable Long id){
        return paytypeService.findById(id);
    }

}

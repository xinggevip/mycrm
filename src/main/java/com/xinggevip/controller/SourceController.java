package com.xinggevip.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinggevip.domain.Source;
import com.xinggevip.enunm.ResultCodeEnum;
import com.xinggevip.service.SourceService;
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
@RequestMapping("/source")
@CrossOrigin
public class SourceController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private SourceService sourceService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public HttpResult add(@RequestBody Source source){
        int res = sourceService.add(source);
        if (res != 1) {
            return HttpResult.failure(ResultCodeEnum.ADD_ERROR);
        }
        return HttpResult.success(ResultCodeEnum.ADD_SUCCESS);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public HttpResult delete(@PathVariable("id") Long id){
        int res = sourceService.delete(id);
        if (res != 1) {
            return HttpResult.failure(ResultCodeEnum.DELETE_ERROR);
        }
        return HttpResult.success(ResultCodeEnum.DELETE_SUCCESS);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public HttpResult update(@RequestBody Source source){
        int res = sourceService.updateData(source);
        if (res != 1) {
            return HttpResult.failure(ResultCodeEnum.UPDATE_ERROR);
        }
        return HttpResult.success(ResultCodeEnum.UPDATE_SUCCESS);
    }

    @ApiOperation(value = "查询所有数据")
    @GetMapping()
    public HttpResult findListByPage(){
        return HttpResult.success(sourceService.findAll());
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Source findById(@PathVariable Long id){
        return sourceService.findById(id);
    }

}

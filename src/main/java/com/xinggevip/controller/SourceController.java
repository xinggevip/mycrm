package com.xinggevip.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinggevip.domain.Source;
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
    public int add(@RequestBody Source source){
        return sourceService.add(source);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return sourceService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Source source){
        return sourceService.updateData(source);
    }

    @ApiOperation(value = "查询所有数据")
    @GetMapping()
    public HttpResult findListByPage(){
        List<Source> sourceList = sourceService.findAll();
        return HttpResult.success(sourceList);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Source findById(@PathVariable Long id){
        return sourceService.findById(id);
    }

}

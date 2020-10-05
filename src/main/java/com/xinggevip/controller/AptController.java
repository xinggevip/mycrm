package com.xinggevip.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinggevip.domain.Apt;
import com.xinggevip.enunm.ResultCodeEnum;
import com.xinggevip.service.AptService;
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
@RequestMapping("/apt")
@CrossOrigin
public class AptController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private AptService aptService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public HttpResult add(@RequestBody Apt apt){
        int res = aptService.add(apt);
        if (res != 1) {
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR);
        }
        return HttpResult.success(ResultCodeEnum.HANDLE_SUCCESS);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return aptService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public HttpResult update(@RequestBody Apt apt){
        int res = aptService.updateData(apt);
        if (res != 1) {
            HttpResult<Object> httpResult = HttpResult.failure(ResultCodeEnum.HANDLE_ERROR);
            return httpResult;
        }
        return HttpResult.success(ResultCodeEnum.HANDLE_SUCCESS);
    }

    @ApiOperation(value = "根据关键字查询分页数据")
    @PostMapping("/getlist")
    public HttpResult findListByPage(@RequestBody Page page){
        return HttpResult.success(aptService.selectAptListByKeyword(page));
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Apt findById(@PathVariable Long id){
        return aptService.findById(id);
    }

}

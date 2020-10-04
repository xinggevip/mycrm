package com.xinggevip.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.xinggevip.domain.User;
import com.xinggevip.enunm.ResultCodeEnum;
import com.xinggevip.service.UserService;
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
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private UserService userService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public HttpResult add(@RequestBody User user){
        int res = userService.add(user);
        if (res != 1) {
            HttpResult<Object> httpResult = HttpResult.failure(ResultCodeEnum.ADD_ERROR);
            return httpResult;
        }
        return HttpResult.success(ResultCodeEnum.ADD_SUCCESS,Boolean.TRUE);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return userService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public HttpResult update(@RequestBody User user){
        int res = userService.updateData(user);
        if (res != 1) {
            HttpResult<Object> httpResult = HttpResult.failure(ResultCodeEnum.UPDATE_ERROR);
            return httpResult;
        }
        return HttpResult.success(ResultCodeEnum.UPDATE_SUCCESS,Boolean.TRUE);
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("/getlist")
    public HttpResult findListByPage(@RequestBody Page page){

        PageInfo<Map> pageInfo = userService.selectUserListByKeyWord(
                page.getPageNum(),
                page.getPageSize(),
                page.getKeyword()
        );

        return HttpResult.success(pageInfo);


    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public User findById(@PathVariable Long id){
        return userService.findById(id);
    }



}

package com.xinggevip.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.xinggevip.domain.Emp;
import com.xinggevip.exception.EmpLoginException;
import com.xinggevip.service.EmpService;
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
@RequestMapping("/emp")
@CrossOrigin
public class EmpController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private EmpService empService;

    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phonenumber", value = "手机号"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @PostMapping("/login")
    public HttpResult<Emp> login(@RequestParam String phonenumber, @RequestParam String password) {
        List<Emp> empList = empService.lambdaQuery().eq(Emp::getPhonenumber, phonenumber).eq(Emp::getPassword, password).list();
        int size = empList.size();
        if (size != 1) {
            // 抛出登录失败异常
            throw new EmpLoginException();
        }
        Emp emp = empList.get(0);
        HttpResult<Emp> empHttpResult = new HttpResult<>(emp);
        return empHttpResult;
    }

    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody Emp emp){
        return empService.add(emp);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return empService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Emp emp){
        return empService.updateData(emp);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Emp> findListByPage(@RequestParam Integer page,
                                     @RequestParam Integer pageCount){
        return empService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Emp findById(@PathVariable Long id){
        return empService.findById(id);
    }

}

package com.totoro.mall.manager.controller;

import com.totoro.mall.model.dto.system.LoginDto;
import com.totoro.mall.model.vo.common.Result;
import com.totoro.mall.model.vo.common.ResultCodeEnum;
import com.totoro.mall.model.vo.system.LoginVo;
import com.totoro.mall.manager.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "用户接口")//swagger的提示，为了测试接口时有中文提示
@RestController//交给spring管理返回json数据
@RequestMapping(value = "/admin/system/index")
public class IndexController {
    @Autowired
    private SysUserService sysUserService;

    @Operation(summary = "登录接口")
    @PostMapping(value = "/login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto){
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }

}

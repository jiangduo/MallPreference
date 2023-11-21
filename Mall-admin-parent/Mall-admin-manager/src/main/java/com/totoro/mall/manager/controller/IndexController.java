package com.totoro.mall.manager.controller;

import com.totoro.mall.manager.service.ValidateCodeService;
import com.totoro.mall.model.dto.system.LoginDto;
import com.totoro.mall.model.entity.system.SysUser;
import com.totoro.mall.model.vo.common.Result;
import com.totoro.mall.model.vo.common.ResultCodeEnum;
import com.totoro.mall.model.vo.system.LoginVo;
import com.totoro.mall.manager.service.SysUserService;
import com.totoro.mall.model.vo.system.ValidateCodeVo;
import com.totoro.mall.utils.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户接口")//swagger的提示，为了测试接口时有中文提示
@RestController//交给spring管理返回json数据
@RequestMapping(value = " ")
//@CrossOrigin(allowCredentials = "true" , originPatterns = "*" , allowedHeaders = "*")
//上述接口解决跨域问题，但是如果每一个都需要手动过于繁复
public class IndexController {
    @Autowired
    private SysUserService sysUserService;


    @GetMapping(value = "/logout")
    public Result logout(@RequestHeader(value = "token") String token){
        sysUserService.logout(token);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     *
     * 获取当前登录用户信息
     * @param
     * @return
     */
    // @GetMapping(value = "/getUserInfo")
    // public Result getUserInfo(@RequestHeader(name = "token")String token){
    //     //1、从请求头获取token
    //     //      a、增加参数，getUserInfo(HttpServletRequest request){
    //     //          String token = request.getHeader("token")
    //     //      b、上述
    //     //2、根据token查询redis获取用户信息
    //     // SysUser sysUser = sysUserService.getUserInfo(token);
    //     //3、用户信息返回
    //     // return Result.build(sysUser,ResultCodeEnum.SUCCESS);
    //
    //     //对上述做优化，因为后来我们使用了threadLocal，登录之后将用户信息放到了threadLocal中，因此这里可以直接获取
    //
    // }
    @GetMapping(value =  "/getUserInfo")
    public Result getUserInfo(){
        return Result.build(AuthContextUtil.get(),ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "登录接口")
    @PostMapping(value = "/login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto){
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }

    @Autowired
    private ValidateCodeService validateCodeService;

    @GetMapping(value = "/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode(){
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo, ResultCodeEnum.SUCCESS);
    }

}

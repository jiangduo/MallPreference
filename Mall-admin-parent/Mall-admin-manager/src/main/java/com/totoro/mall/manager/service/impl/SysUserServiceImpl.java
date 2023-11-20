package com.totoro.mall.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.totoro.mall.common.exception.GlobalException;
import com.totoro.mall.manager.mapper.SysUserMapper;
import com.totoro.mall.manager.service.SysUserService;
import com.totoro.mall.model.dto.system.LoginDto;
import com.totoro.mall.model.entity.system.SysUser;
import com.totoro.mall.model.vo.common.ResultCodeEnum;
import com.totoro.mall.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public LoginVo login(LoginDto loginDto) {


        /**
         * 2、驗證嗎校驗
         */

        //1、获取用户输入的验证码和codeKey（）（用户存放redis中，使用uuid生成的）
        String captcha = loginDto.getCaptcha();
        String codeKey = loginDto.getCodeKey();
        //2、根据获取redis里面的key，查询redis里面对应的数据（真是的验证码数据）
        String redisCode = redisTemplate.opsForValue().get("user:login:validatecode:"+codeKey);
        //3、比较输入的验证码是否一致，不一致抛出异常
        if (StrUtil.isEmpty(redisCode)||
        !StrUtil.equalsIgnoreCase(redisCode, captcha)){
            throw new GlobalException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
        //5、如果一致，删除redis里面的验证码
        redisTemplate.delete("user:login:validatecode:"+codeKey);



        /**
         * 1、傳統登錄
         */
        // 1、获取提交到用户名，loginDto获取到
        // 2、根据用户名查询数据库表，sys_user表
        SysUser sysUser = sysUserMapper.selectByUserName(loginDto.getUserName());
        // 3、查询不到用户信息，用户不存在，返回错误信息
        if (sysUser == null) {
            throw new GlobalException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 4、查询到用户信息，用户存在
        // 5、获取输入的密码，比较输入的密码好数据库密码是否一致
        // 51、把输入的密码进行加密，在比较数据库密码，md5
        String inputPassword = DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes());
        if (!inputPassword.equals(sysUser.getPassword())) {
            throw new GlobalException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 6、如果密码一直，登陆成功，如果密码不一致登录失败
        // 7、登陆成功，生成唯一表示token
        // token有多重方式，这里采用uuid生成唯一的
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue()
                .set("user:login:" + token,
                        JSON.toJSONString(sysUser),
                        7,
                        TimeUnit.DAYS);
        // 8、把登录成功的用户信息放到redis里面，用户信息转成json字符串
        // key:token value：用户信息
        // 9、返回login对象

        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setRefresh_token("");
        return loginVo;
    }

    @Override
    public SysUser getUserInfo(String token) {
        String userJson = redisTemplate.opsForValue().get("user:login:" + token);
        //将字符串转为对象
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return sysUser;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login:" + token);
    }
}

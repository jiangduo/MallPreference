package com.totoro.mall.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.totoro.mall.manager.mapper.SysUserMapper;
import com.totoro.mall.manager.service.SysUserService;
import com.totoro.mall.model.dto.system.LoginDto;
import com.totoro.mall.model.entity.system.SysUser;
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

        // 1、获取提交到用户名，loginDto获取到
        // 2、根据用户名查询数据库表，sys_user表
        SysUser sysUser = sysUserMapper.selectByUserName(loginDto.getUserName());
        // 3、查询不到用户信息，用户不存在，返回错误信息
        if (sysUser == null) {
            throw new RuntimeException("用户名或者密码错误");
        }
        // 4、查询到用户信息，用户存在
        // 5、获取输入的密码，比较输入的密码好数据库密码是否一致
        // 51、把输入的密码进行加密，在比较数据库密码，md5
        String inputPassword = DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes());
        if (!inputPassword.equals(sysUser.getPassword())) {
            throw new RuntimeException("用户名或者密码错误");
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
}

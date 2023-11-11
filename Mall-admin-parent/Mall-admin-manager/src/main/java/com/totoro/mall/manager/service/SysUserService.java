package com.totoro.mall.manager.service;

import com.totoro.mall.model.dto.system.LoginDto;
import com.totoro.mall.model.vo.system.LoginVo;

public interface SysUserService {
    public abstract LoginVo login(LoginDto loginDto) ;
}

package com.totoro.mall.manager.mapper;

import com.totoro.mall.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper//能找到他动态创建的对象
public interface SysUserMapper {
    public abstract SysUser selectByUserName(String userName);
}

package com.totoro.mall.manager.mapper;

import com.totoro.mall.model.dto.system.SysRoleDto;
import com.totoro.mall.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Totoro
 * @create 23 16:20
 * @Description:
 */
@Mapper
public interface SysRoleMapper {
    public abstract List<SysRole> findByPage(SysRoleDto sysRoleDto);

    public abstract void saveSysRole(SysRole sysRole);

    void deleteById(Long roleId);

    void updateSysRole(SysRole sysRole);
}

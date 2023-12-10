package com.totoro.mall.manager.service;

import com.github.pagehelper.PageInfo;
import com.totoro.mall.model.dto.system.SysRoleDto;
import com.totoro.mall.model.entity.system.SysRole;

/**
 * @author Totoro
 * @create 23 16:19
 * @Description:
 */
public interface SysRoleService {
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize);

    void saveSysRole(SysRole sysRole);

    void deleteById(Long roleId);

    void updateSysRole(SysRole sysRole);
}

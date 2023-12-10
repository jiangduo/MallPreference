package com.totoro.mall.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.totoro.mall.manager.mapper.SysRoleMapper;
import com.totoro.mall.manager.service.SysRoleService;
import com.totoro.mall.model.dto.system.SysRoleDto;
import com.totoro.mall.model.entity.system.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Totoro
 * @create 23 16:20
 * @Description:
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Override
    public PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize) {
        //使用到了pageHelper插件，因此需要先设置分页参数
        PageHelper.startPage(pageNum, pageSize);

        //这里的查询是查询所有数据，然后根据pageInfo封装设置的参数返回数据
        List<SysRole> sysRoleList = sysRoleMapper.findByPage(sysRoleDto);
        PageInfo<SysRole> pageInfo  = new PageInfo(sysRoleList);
        return pageInfo;
    }

    @Override
    public void saveSysRole(SysRole sysRole) {
        sysRoleMapper.saveSysRole(sysRole);
    }

    @Override
    public void deleteById(Long roleId) {
        sysRoleMapper.deleteById(roleId);
    }

    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.updateSysRole(sysRole);
    }
}

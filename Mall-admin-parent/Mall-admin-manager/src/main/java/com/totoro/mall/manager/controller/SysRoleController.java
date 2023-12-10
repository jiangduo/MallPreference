package com.totoro.mall.manager.controller;

import com.github.pagehelper.PageInfo;
import com.totoro.mall.manager.service.SysRoleService;
import com.totoro.mall.model.dto.system.SysRoleDto;
import com.totoro.mall.model.entity.system.SysRole;
import com.totoro.mall.model.vo.common.Result;
import com.totoro.mall.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Totoro
 * @create 23 16:18
 * @Description: 角色管理
 */ 

@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    //角色查询
    @PostMapping("/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<SysRole>> findByPage(@RequestBody SysRoleDto sysRoleDto,//dto只封装了角色名称
                                                @PathVariable(value = "pageNum") Integer pageNum,//通过路径得到该值
                                                @PathVariable(value = "pageSize") Integer pageSize) {
        //pageHelper插件实现的分页
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(sysRoleDto, pageNum, pageSize);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/saveSysRole")
    public Result saveSysRole(@RequestBody SysRole sysRole){
        sysRoleService.saveSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @DeleteMapping("/deleteById/{roleId}")
    public Result deleteById(@PathVariable(value = "roleId") Long roleId){
        sysRoleService.deleteById(roleId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    @PutMapping("/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole){
        sysRoleService.updateSysRole(sysRole);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}

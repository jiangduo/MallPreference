import request from '@/utils/request'

// 分页查询角色数据
export const GetSysRoleListByPage = (current , limit , queryDto) => {
    return request({
        // url: '/admin/system/sysRole/findByPage/' + pageNum + "/" + pageSize,
        url: `/admin/system/sysRole/findByPage/${current}/${limit}`,//接口
        method: 'post',//提交方式
        //后端接口如果是@RequestBody，需要用data
        //如果没有注解,前端是params：名称
        data: queryDto,
    })
}

// 添加角色请求方法
export const SaveSysRole = (sysRole) => {
    return request({
        url: '/admin/system/sysRole/saveSysRole',
        method: 'post',
        data: sysRole ,
    })
}

// 保存修改
export const UpdateSysRole = (data) => {
    return request({
        url: '/admin/system/sysRole/updateSysRole',
        method: 'put',
        data
    })
}

// 删除角色
export const DeleteSysRoleById = (roleId) => {
    return request({
        url: '/admin/system/sysRole/deleteById/' + roleId,
        method: 'delete'
    })
}


// 查询所有的角色数据
export const GetAllRoleList = (userId) => {
    return request({
        url: '/admin/system/sysRole/findAllRoles/' + userId,
        method: 'get'
    })
}
// 查询指定角色所对应的菜单id
export const GetSysRoleMenuIds = (roleId) => {
    return request({
        url: "/admin/system/sysRoleMenu/findSysRoleMenuByRoleId/"+ roleId,
        method: 'get'
    })
}

// 根据角色分配菜单请求方法
export const DoAssignMenuIdToSysRole = (assignMenuDto) => {
    return request({
        url: "/admin/system/sysRoleMenu/doAssign",
        method: 'post',
        data: assignMenuDto
    })
}
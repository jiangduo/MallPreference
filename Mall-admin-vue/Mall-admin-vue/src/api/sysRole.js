import request from '@/utils/request' //引入文件，这个文件中创建了ajax请求对象，里面有请求和响应拦截器

//抽取公共路径
const base_api = '/admin/system/sysRole'

// 定义接口：分页查询角色数据
//export 表示可以被引用的方法    方法=（//findByPage接口中对应的参数,）
export const GetSysRoleListByPage = (pageNum, pageSize, queryDto) => {
  return request({
    url: base_api + '/findByPage/' + pageNum + '/' + pageSize, //路径：接口+方法
    //第二种方式：末班字符串：`${base_api}/findByPage/${pageNum}/${pageSize}`,然后通过表达式把，值取到
    method: 'post',
    //其它参数，如果后端接口为@RequestBody，则前端对应写法为 data：名称 。 标明以json形式传输
    //如果后端没有注解，则前端以params：参数名称 、 表示以普通方式传递、
    //params: queryDto
    data: queryDto,
  })
}

// 添加角色请求方法
export const SaveSysRole = data => {
  return request({
    url: '/admin/system/sysRole/saveSysRole',
    method: 'post',
    data,
  })
}
// 保存修改
export const UpdateSysRole = data => {
  return request({
    url: '/admin/system/sysRole/updateSysRole',
    method: 'put',
    data,
  })
}
// 删除角色
export const DeleteSysRoleById = roleId => {
  return request({
    url: '/admin/system/sysRole/deleteById/' + roleId,
    method: 'delete',
  })
}

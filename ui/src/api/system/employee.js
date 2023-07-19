import request from '@/utils/request'

// 查询职工列表列表
export function listEmployee(query) {
  return request({
    url: '/system/employee/list',
    method: 'get',
    params: query
  })
}

// 查询职工列表详细
export function getEmployee(id) {
  return request({
    url: '/system/employee/' + id,
    method: 'get'
  })
}

// 新增职工列表
export function addEmployee(data) {
  return request({
    url: '/system/employee',
    method: 'post',
    data: data
  })
}

// 修改职工列表
export function updateEmployee(data) {
  return request({
    url: '/system/employee',
    method: 'put',
    data: data
  })
}

// 删除职工列表
export function delEmployee(id) {
  return request({
    url: '/system/employee/' + id,
    method: 'delete'
  })
}

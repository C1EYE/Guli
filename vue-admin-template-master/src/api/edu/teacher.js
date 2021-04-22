import request from '@/utils/request'
const api_name = '/teacher'

export default {
  getPageList(page, limit, searchObj) {
    return request({
      url: `${api_name}/${page}/${limit}`,
      method: 'post',
      data: searchObj,
    })
  },
  removeDataById(id){
    return request({
      url: `${api_name}/${id}`,
      method: 'delete',
    })
  },
  save(teacher){
    return request({
      url: `${api_name}/save`,
      method: 'post',
      data: teacher
    })
  },
  getById(id){
    return request({
      url: `${api_name}/${id}`,
      method: 'get',
    })
  },
  updateById(id,teacher){
    return request({
      url: `${api_name}/${id}`,
      method: 'put',
      data: teacher
    })
  }
}

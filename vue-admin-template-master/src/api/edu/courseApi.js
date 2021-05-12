import request from "@/utils/request";

const api_name = "/edu-course";

export default{
    saveVo(courseInfo){
        return request({
            url: `${api_name}/saveVo`,
            method: "post",
            data: courseInfo
        })
    },
    getVoById(id){
        return request({
            url: `${api_name}/${id}`,
            method: "get"
        })
    },
    updateVo(courseInfo){
        return request({
            url: `${api_name}/update`,
            method: "put",
            data: courseInfo
        })
    },
    getPageList(page, limit, searchObj) {
        return request({
          url: `${api_name}/${page}/${limit}`,
          method: 'post',
          data: searchObj
        })
    },
    removeById(id) {
        return request({
            url: `${api_name}/${id}`,
            method: 'delete'
        })
    },
    getCoursePublishInfoById(id) {
        return request({
          url: `${api_name}/vo/${id}`,
          method: 'get'
        })
      },
      updateStatusById(id){
        return request({
          url:`${api_name}/status/${id}`,
          method:'put'
        })
      }
    


}
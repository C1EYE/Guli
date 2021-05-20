import request from '@/utils/request'

const api_name  = "/FrontTeacher" 
export default {
    //讲师列表的方法
    getTeacherListPage(page,limit) {
        return request({
            url: `${api_name}/getTeacherList/`+page+`/`+limit,
            method: 'get'
        })
    },
    //根据讲师id查询详情
    getTeacherInfoId(id) {
        return request({
            url: `${api_name}/getTeacherById/${id}`,
            method: 'get'
        })
    }
}
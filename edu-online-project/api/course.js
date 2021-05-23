import request from "@/utils/request";

const api_name = "/FrontCourse";
export default {
  //讲师列表的方法
  getCourseListPage(page, limit) {
    return request({
      url: `${api_name}/list/${page}/${limit}`,
      method: "get"
    });
  },
  getCourseListPageQueryWrapper(queryWrapper){
    return request({
      url: `${api_name}/listWrapper`,
      method: "post",
      data: queryWrapper
    })
  },
  getById(courseId) {
    return request({
        url: `${api_name}/getById/${courseId}`,
        method: 'get'
    })
},
  getSubjectList(){
    return request({
      url: `subject/tree`,
      method: 'get'
    })
  },
};

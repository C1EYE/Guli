import request from "@/utils/request";

const api_name = "/sta";

export default {
  createStatistics(day) {
    return request({
      url: `${api_name}/createData/${day}`,
      method: "post"
    });
  },
  showDataEcharts(searchObj) {
    return request({
      url: `${api_name}/getCountData/${searchObj.begin}/${searchObj.end}/${searchObj.type}`,
      method: "get",
    });
  }
};

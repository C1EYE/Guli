import request from "@/utils/request";

const api_name = "/vod";

export default {
  getAuth(vid) {
    return request({
      url: `${api_name}/getAuth/${vid}`,
      method: "GET"
    });
  }
};

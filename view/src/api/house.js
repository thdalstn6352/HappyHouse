import { apiInstance } from "./index.js";

const api = apiInstance();
// const house = houseInstance();

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  console.log(params);
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  api.get(`map/apt`, { params: params }).then(success).catch(fail);
}

function aroundList(params, success, fail) {
  api.get(`map/around`, { params: params }).then(success).catch(fail);
}

function searchList(params, success, fail) {
  console.log(params);
  api.get(`map/search`, { params: params }).then(success).catch(fail);
}
export { sidoList, gugunList, houseList, aroundList, searchList };

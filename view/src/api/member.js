import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function join(user, success, fail) {
  console.log(JSON.stringify(user));
  await api.post(`/user/join`, JSON.stringify(user)).then(success).catch(fail);
  // await api.post(`/user/join`, user).then(success).catch(fail);
}

async function modify(user, success, fail) {
  await api.put(`/user`, JSON.stringify(user)).then(success).catch(fail);
}

async function remove(userid, success, fail) {
  await api.delete(`/user/${userid}`).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/auth/${userid}`).then(success).catch(fail);
}

async function checkId(userid, success, fail) {
  await api.get(`/user/${userid}`).then(success).catch(fail);
}
// function logout(success, fail)

export { login, join, modify, remove, findById, checkId };

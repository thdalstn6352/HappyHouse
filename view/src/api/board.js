import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(param, success, fail) {
  api.get(`/board/`, { params: param }).then(success).catch(fail);
}

async function writeArticle(article, success, fail) {
  await api.post(`/board`, article).then(success).catch(fail);
}

async function getArticle(articleno, success, fail) {
  await api.get(`/board/${articleno}`).then(success).catch(fail);
}

async function modifyArticle(article, success, fail) {
  await api.put(`/board`, article).then(success).catch(fail);
}

async function deleteArticle(articleno, success, fail) {
  await api.delete(`/board/${articleno}`).then(success).catch(fail);
}

export { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle };

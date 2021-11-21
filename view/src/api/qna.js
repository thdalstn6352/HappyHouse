import { apiInstance } from "./index.js";

const api = apiInstance();

function getQnas(param, success, fail) {
  api.get(`/qna`, { params: param }).then(success).catch(fail);
}

function getQna(qnano, success, fail) {
  api.get(`/qna/${qnano}`).then(success).catch(fail);
}

async function writeQna(qna, success, fail) {
  await api.post(`/qna`, qna).then(success).catch(fail);
}

async function modifyQna(qna, success, fail) {
  await api.put(`/qna`, qna).then(success).catch(fail);
}

async function deleteQna(qnano, success, fail) {
  await api.delete(`/qna/${qnano}`).then(success).catch(fail);
}

function getComments(qnano, success, fail) {
  api.get(`/answer/${qnano}`).then(success).catch(fail);
}

function writeComment(comment, success, fail) {
  api.post(`/answer`, comment).then(success).catch(fail);
}

function updateComment(comment, success, fail) {
  api.put(`/answer`, comment).then(success).catch(fail);
}

function deleteComment(answerno, success, fail) {
  api.delete(`/answer/${answerno}`).then(success).catch(fail);
}

export {
  getQnas,
  getQna,
  writeQna,
  modifyQna,
  deleteQna,
  getComments,
  writeComment,
  updateComment,
  deleteComment,
};

import { apiInstance } from "./index.js";

const api = apiInstance();

function getQnas(success, fail) {
  api.get(`/qna`).then(success).catch(fail);
}

function getQna(qnano, success, fail) {
  api.get(`/qna/${qnano}`).then(success).catch(fail);
}

function writeQna(qna, success, fail) {
  api.post(`/qna`, qna).then(success).catch(fail);
}

function modifyQna(qna, success, fail) {
  api.put(`/qna`, qna).then(success).catch(fail);
}

function deleteQna(qnano, success, fail) {
  api.put(`/qna/${qnano}`).then(success).catch(fail);
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

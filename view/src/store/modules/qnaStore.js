/* eslint-disable prettier/prettier */
import {
  getQnas,
  getQna,
  writeQna,
  modifyQna,
  deleteQna,
  getComments,
  writeComment,
  updateComment,
  deleteComment,
} from "@/api/qna.js";

const qnaStore = {
  namespaced: true,
  state: {
    qnas: [],
    qna: {},
    comments: [],
    comment: {},
    totalRows: Number,
    currentPage: Number,
  },
  getters: {
    qnas(state) {
      return state.qnas;
    },
    qna(state) {
      return state.qna;
    },
    comments(state) {
      return state.comments;
    },
    totalCount(state) {
      return state.totalRows;
    },
    getCurrentPage(state) {
      return state.currentPage;
    },
  },
  mutations: {
    setQnas(state, payload) {
      state.qnas = payload;
    },
    setQna(state, payload) {
      state.qna = payload;
    },
    setComments(state, payload) {
      state.comments = payload;
    },
    setComment(state, payload) {
      state.comment = payload;
    },
    setTotalRows(state, payload) {
      state.totalRows = payload;
    },
    setCurrentPage(state, payload) {
      state.currentPage = payload;
    },
  },
  actions: {
    getQnas: ({ commit }, param) => {
      getQnas(
        param,
        ({ data }) => {
          console.log(data);
          commit("setTotalRows", data.pageNav.totalCount);
          commit("setQnas", data.list);
        },
        error => {
          console.log(error);
        }
      );
    },
    getQna: ({ commit }, qnano) => {
      getQna(
        qnano,
        ({ data }) => {
          commit("setQna", data);
        },
        error => {
          console.log(error);
        }
      );
    },

    writeQna: ({ commit }, qna) => {
      writeQna(
        qna,
        ({ data }) => {
          commit("setQna", data);
        },
        error => {
          console.log(error);
        }
      );
    },

    modifyQna: ({ commit }, qna) => {
      modifyQna(
        qna,
        ({ data }) => {
          commit("setQna", data);
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
        },
        error => {
          console.log(error);
        }
      );
    },
    deleteQna: ({ commit }, qnano) => {
      deleteQna(
        qnano,
        ({ data }) => {
          commit("setQna", data);
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "삭제가 완료되었습니다.";
          }
          alert(msg);
        },
        error => {
          console.log(error);
        }
      );
    },

    getComments: ({ commit }, qnano) => {
      getComments(
        qnano,
        ({ data }) => {
          commit("setComments", data);
        },
        error => {
          console.log(error);
        }
      );
    },

    writeComment: ({ commit }, comment) => {
      console.log(comment);
      writeComment(
        comment,
        ({ data }) => {
          commit("setComment", data);
          getComments(
            comment.qnano,
            ({ data }) => {
              commit("setComments", data);
            },
            error => {
              console.log(error);
            }
          );
        },
        error => {
          console.log(error);
        }
      );
    },

    updateComment: ({ commit }, comment) => {
      updateComment(
        comment,
        ({ data }) => {
          commit("setComment", data);
          getComments(
            comment.qnano,
            ({ data }) => {
              commit("setComments", data);
            },
            error => {
              console.log(error);
            }
          );
        },
        error => {
          console.log(error);
        }
      );
    },
    deleteComment: ({ commit }, comment) => {
      deleteComment(
        comment.answerno,
        ({ data }) => {
          commit("setComment", data);
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "삭제가 완료되었습니다.";
          }
          alert(msg);
          getComments(
            comment.qnano,
            ({ data }) => {
              commit("setComments", data);
            },
            error => {
              console.log(error);
            }
          );
        },
        error => {
          console.log(error);
        }
      );
    },
  },
};

export default qnaStore;

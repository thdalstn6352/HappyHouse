/* eslint-disable prettier/prettier */
import {
  listArticle,
  writeArticle,
  getArticle,
  modifyArticle,
  deleteArticle,
} from "@/api/board.js";

const boardStore = {
  namespaced: true,
  state: {
    articles: [],
    article: {},
    isSuccess: false,
    totalRows: Number,
    currentPage: Number,
  },
  getters: {
    articles(state) {
      // 도서목록
      return state.articles;
    },
    article(state) {
      // 도서정보
      return state.article;
    },
    isSuccess(state) {
      return state.isSuccess;
    },
    totalCount(state) {
      return state.totalRows;
    },
    getCurrentPage(state) {
      return state.currentPage;
    },
  },
  mutations: {
    setArticles(state, payload) {
      // state의 books에 서버에서 얻어온 도서목록 세팅.
      state.articles = payload;
    },
    setArticle(state, payload) {
      // state의 book에 서버에서 얻어온 도서정보 세팅.
      state.article = payload;
      state.isSuccess = true;
    },
    setSuccess(state, payload) {
      state.isSuccess = payload;
    },
    setTotalRows(state, payload) {
      state.totalRows = payload;
    },
    setCurrentPage(state, payload) {
      state.currentPage = payload;
    },
  },
  actions: {
    // 서버에서 게시글을 얻고 mutation의 setArticles를 호출한다.
    getArticles: ({ commit }, param) => {
      listArticle(
        param,
        ({ data }) => {
          commit("setTotalRows", data.pageNav.totalCount);
          commit("setArticles", data.list);
          // commit("setCurrentPage", param.pg);
        },
        error => {
          console.log(error);
        }
      );
    },
    getArticle: ({ commit }, articleno) => {
      console.log(articleno);
      getArticle(
        articleno,
        ({ data }) => {
          commit("setArticle", data);
        },
        error => {
          console.log(error);
        }
      );
    },
    async writeArticle({ commit }, article) {
      await writeArticle(
        article,
        ({ data }) => {
          console.log(data);
          commit("setArticle", article);
        },
        error => {
          console.log(error);
        }
      );
    },
    async modifyArticle({ commit }, article) {
      await modifyArticle(
        article,
        ({ data }) => {
          commit("setArticle", article);
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
    async deleteArticle({ commit }, articleno) {
      await deleteArticle(
        articleno,
        ({ data }) => {
          commit("setArticle", {});
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
  },
};

export default boardStore;

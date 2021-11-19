/* eslint-disable prettier/prettier */
import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

import memberStore from "@/store/modules/memberStore.js";
import boardStore from "@/store/modules/boardStore.js";
import houseStore from "@/store/modules/houseStore.js";
import qnaStore from "@/store/modules/qnaStore.js";

export default new Vuex.Store({
  // state: {
  //   sidos: [{ value: null, text: "선택하세요" }],
  //   guguns: [{ value: null, text: "선택하세요" }],
  //   houses: [],
  //   house: null,
  //   articles: [],
  //   article: {},
  //   qnas: [],
  //   qna: {},
  //   comments: [],
  // },
  // getters: {
  //   articles(state) {
  //     // 도서목록
  //     return state.articles;
  //   },
  //   article(state) {
  //     // 도서정보
  //     return state.article;
  //   },
  //   qnas(state) {
  //     return state.qnas;
  //   },
  //   qna(state) {
  //     return state.qna;
  //   },
  //   comments(state) {
  //     // 도서평 목록
  //     return state.comments;
  //   },
  // },
  // mutations: {
  //   setArticles(state, payload) {
  //     // state의 books에 서버에서 얻어온 도서목록 세팅.
  //     state.articles = payload;
  //   },
  //   setArticle(state, payload) {
  //     // state의 book에 서버에서 얻어온 도서정보 세팅.
  //     state.article = payload;
  //   },
  //   setQnas(state, payload) {
  //     // state의 books에 서버에서 얻어온 도서목록 세팅.
  //     state.qnas = payload;
  //   },
  //   setQna(state, payload) {
  //     // state의 book에 서버에서 얻어온 도서정보 세팅.
  //     state.qna = payload;
  //   },
  //   setComments(state, payload) {
  //     // state의 comments에 서버에서 얻어온 도서평 목록 세팅.
  //     state.comments = payload;
  //   },
  //   SET_SIDO_LIST(state, sidos) {
  //     sidos.forEach(sido => {
  //       state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
  //     });
  //   },
  //   SET_GUGUN_LIST(state, guguns) {
  //     guguns.forEach(gugun => {
  //       state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
  //     });
  //   },
  //   CLEAR_SIDO_LIST(state) {
  //     state.sidos = [{ value: null, text: "선택하세요" }];
  //   },
  //   CLEAR_GUGUN_LIST(state) {
  //     state.guguns = [{ value: null, text: "선택하세요" }];
  //   },
  //   SET_HOUSE_LIST(state, houses) {
  //     state.houses = houses;
  //   },
  //   SET_DETAIL_HOUSE(state, house) {
  //     state.house = house;
  //   },
  // },
  // actions: {
  //   // 서버에서 게시글을 얻고 mutation의 setArticles를 호출한다.
  //   getArticles(context) {
  //     http
  //       .get("/board")
  //       .then(({ data }) => {
  //         context.commit("setArticles", data);
  //       })
  //       .catch(() => {
  //         alert("에러발생!");
  //       });
  //   },
  //   // 서버에서 isbn에 해당하는 도서정보 얻고 mutation의 setBook을 호출한다.
  //   getArticle(context, payload) {
  //     http.get(payload).then(({ data }) => {
  //       context.commit("setArticle", data);
  //     });
  //   },
  //   getQnas(context) {
  //     http.get("/qna").then(({ data }) => {
  //       context.commit("setQnas", data);
  //     });
  //   },
  //   getQna(context, payload) {
  //     http.get(payload).then(({ data }) => {
  //       context.commit("setQna", data);
  //     });
  //   },
  //   // 서버에서 isbn에 해당하는 도서평을 얻고 mutation의 setComments를 호출한다.
  //   getComments(context, payload) {
  //     http.get(payload).then(({ data }) => {
  //       context.commit("setComments", data);
  //     });
  //   },
  //   getSido({ commit }) {
  //     http
  //       .get(`/map/sido`)
  //       .then(response => {
  //         // console.log(data)
  //         commit("SET_SIDO_LIST", response.data);
  //       })
  //       .catch(error => {
  //         console.log(error);
  //       });
  //   },
  //   getGugun({ commit }, sidoCode) {
  //     const params = { sido: sidoCode };
  //     http
  //       .get(`/map/gugun`, { params })
  //       .then(response => {
  //         // console.log(commit, response);
  //         commit("SET_GUGUN_LIST", response.data);
  //       })
  //       .catch(error => {
  //         console.log(error);
  //       });
  //   },
  //   getHouseList({ commit }, gugunCode) {
  //     const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
  //     // const SERVICE_KEY =
  //     //   "9Xo0vlglWcOBGUDxH8PPbuKnlBwbWU6aO7%2Bk3FV4baF9GXok1yxIEF%2BIwr2%2B%2F%2F4oVLT8bekKU%2Bk9ztkJO0wsBw%3D%3D";
  //     const SERVICE_URL =
  //       "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
  //     const params = {
  //       LAWD_CD: gugunCode,
  //       DEAL_YMD: "202110",
  //       serviceKey: decodeURIComponent(SERVICE_KEY),
  //     };
  //     http
  //       .get(SERVICE_URL, { params })
  //       .then(response => {
  //         console.log(commit, response);
  //         commit("SET_HOUSE_LIST", response.data.response.body.items.item);
  //       })
  //       .catch(error => {
  //         console.log(error);
  //       });
  //   },
  //   detailHouse({ commit }, house) {
  //     // house 일련번호를 이용하여 api 호출
  //     commit("SET_DETAIL_HOUSE", house);
  //   },
  // },
  modules: {
    memberStore,
    boardStore,
    houseStore,
    qnaStore,
  },
  plugins: [
    createPersistedState({
      storage: sessionStorage,
    }),
  ],
});

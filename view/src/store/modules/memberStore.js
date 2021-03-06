/* eslint-disable prettier/prettier */
import jwt_decode from "jwt-decode";
import { login, join, checkId, modify, remove } from "@/api/member.js";
import { findById } from "../../api/member";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    isJoin: false,
    isJoinError: false,
    check: null,
    isId: false,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
      // state.userInfo.userpwd = null;
      // state.userInfo.userpwdcheck = null;
      console.log(state.userInfo);
    },
    SET_IS_JOIN: (state, isJoin) => {
      state.isJoin = isJoin;
    },
    SET_IS_JOIN_ERROR: (state, isJoinError) => {
      state.isJoinError = isJoinError;
    },
    SET_IS_ID: (state, isId) => {
      state.isId = isId;
    },
    SET_CHECK_ID: (state, id) => {
      state.check = id;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          console.log(response);
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {}
      );
    },
    async joinConfirm({ commit }, user) {
      await join(
        user,
        (response) => {
          console.log(response);
          if (response.data === "success") {
            commit("SET_IS_JOIN", true);
            commit("SET_IS_JOIN_ERROR", false);
          } else {
            commit("SET_IS_JOIN", false);
            commit("SET_IS_JOIN_ERROR", true);
          }
        },
        () => {}
      );
    },

    async modifyInfo({ commit }, user) {
      await modify(
        user,
        (response) => {
          console.log(response);
          if (response.data === "success") {
            user.userpwd = null;
            user.userpwdcheck = null;
            console.log(user);
            commit("SET_USER_INFO", user);
          } else {
            console.log("?????? ?????? ??????");
          }
        },
        () => {}
      );
    },

    async deleteInfo({ commit }, userid) {
      await remove(
        userid,
        (response) => {
          console.log(response);
          if (response.data === "success") {
            commit("SET_USER_INFO", null);
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {}
      );
    },

    async checkId({ commit }, userid) {
      console.log("2");
      await checkId(
        userid,
        (response) => {
          console.log("hihi");
          if (response.data != "") {
            commit("SET_IS_ID", true);
            commit("SET_CHECK_ID", response);
          } else {
            commit("SET_IS_ID", false);
          }
        },
        () => {}
      );
    },

    getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      findById(
        decode_token.userid,
        (response) => {
          if (response.data.message === "success") {
            console.log(response);
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("?????? ?????? ??????!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default memberStore;

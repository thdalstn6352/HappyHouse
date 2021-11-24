/* eslint-disable prettier/prettier */
import Vue from "vue";
import Vuex from "vuex";
// import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

import memberStore from "@/store/modules/memberStore.js";
import boardStore from "@/store/modules/boardStore.js";
import houseStore from "@/store/modules/houseStore.js";
import qnaStore from "@/store/modules/qnaStore.js";

export default new Vuex.Store({
  modules: {
    memberStore,
    boardStore,
    houseStore,
    qnaStore,
  },
  // plugins: [
  //   createPersistedState({
  //     storage: sessionStorage,
  //   }),
  // ],
});

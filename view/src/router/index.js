import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/Home.vue";
import Favorite from "@/views/HouseFavorite.vue";

import Member from "@/views/Member.vue";
import MemberLogin from "@/components/user/MemberLogin.vue";
import MemberJoin from "@/components/user/MemberJoin.vue";
import MemberMyPage from "@/components/user/MemberMyPage.vue";
import MemberModify from "@/components/user/MemberModify.vue";

import Board from "@/views/Board.vue";
import BoardList from "@/components/board/BoardList.vue";
import BoardWrite from "@/components/board/BoardWrite.vue";
import BoardView from "@/components/board/BoardView.vue";
import BoardUpdate from "@/components/board/BoardUpdate.vue";

import House from "@/views/House.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";
import HouseView from "@/components/house/HouseView.vue";
import RecentView from "@/components/house/HouseRecentView.vue";
import WatchList from "@/components/house/WatchList.vue";

import store from "@/store/index.js";

import Qna from "@/views/QnA.vue";
import QnaList from "@/components/qna/QnaList.vue";
import QnaWrite from "@/components/qna/QnaWrite.vue";
import QnaView from "@/components/qna/QnaView.vue";
import QnaUpdate from "@/components/qna/QnaUpdate.vue";
Vue.use(VueRouter);

const onlyAuthUser = async (to, from, next) => {
  console.log("hihi");
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  // const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    // await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "SignIn" });
    router.push({ name: "SignIn" });
  } else {
    console.log("로그인 했다.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/favorite",
    name: "Favorite",
    component: Favorite,
    redirect: "/favorite/recentview",
    children: [
      {
        path: "recentview",
        name: "RecentView",
        component: RecentView,
      },
      {
        path: "watchlist",
        name: "WatchList",
        component: WatchList,
      },
    ],
  },
  {
    path: "/user",
    name: "Member",
    component: Member,
    children: [
      {
        path: "singin",
        name: "SignIn",
        component: MemberLogin,
      },
      {
        path: "singup",
        name: "SignUp",
        component: MemberJoin,
      },
      {
        path: "mypage",
        name: "MyPage",
        beforeEnter: onlyAuthUser,
        component: MemberMyPage,
      },
      {
        path: "modify",
        name: "Modify",
        beforeEnter: onlyAuthUser,
        component: MemberModify,
      },
    ],
  },
  {
    path: "/board",
    name: "Board",
    component: Board,
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "BoardList",
        component: BoardList,
      },
      {
        path: "write",
        name: "BoardWrite",
        beforeEnter: onlyAuthUser,
        component: BoardWrite,
      },
      {
        path: "detail/:articleno",
        name: "BoardView",
        component: BoardView,
      },
      {
        path: "update/:articleno",
        name: "BoardUpdate",
        beforeEnter: onlyAuthUser,
        component: BoardUpdate,
      },
    ],
  },
  {
    path: "/house",
    name: "House",
    component: House,
    redirect: "/house/view",
    children: [
      {
        path: "view",
        name: "HouseView",
        component: HouseView,
      },
      {
        path: "detail",
        name: "HouseDetail",
        component: HouseDetail,
      },
    ],
  },
  {
    path: "*",
    redirect: "/",
  },
  {
    path: "/qna",
    name: "QnA",
    component: Qna,
    redirect: "/qna/list",
    children: [
      {
        path: "list",
        name: "QnaList",
        component: QnaList,
      },
      {
        path: "write",
        name: "QnaWrite",
        beforeEnter: onlyAuthUser,
        component: QnaWrite,
      },
      {
        path: "detail/:qnano",
        name: "QnaView",
        beforeEnter: onlyAuthUser,
        component: QnaView,
      },
      {
        path: "update/:qnano",
        name: "QnaUpdate",
        beforeEnter: onlyAuthUser,
        component: QnaUpdate,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

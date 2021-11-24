<template>
  <div>
    <b-navbar toggleable="lg" type="dark">
      <b-navbar-brand href="#">
        <router-link to="/">
          <img
            src="@/assets/ssafy_logo.png"
            class="d-inline-block align-middle"
            width="90px"
            alt="Kitten"
          />
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="ml-auto ml-auto">
          <b-nav-item href="#"
            ><router-link :to="{ name: 'Board' }" class="link"
              ><b-icon icon="journal" font-scale="1"></b-icon>
              공지사항</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'Favorite' }" class="link"
              ><b-icon icon="bookmark-star" font-scale="1"></b-icon>
              관심목록</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'House' }" class="link"
              ><b-icon icon="house" font-scale="1"></b-icon>
              아파트정보</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'QnA' }" class="link"
              ><b-icon icon="question-circle" font-scale="1"></b-icon>
              QnA</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto" v-if="userInfo">
          <b-nav-item-dropdown right>
            <template #button-content>
              <b-icon icon="people" font-scale="2"></b-icon>
              <span>{{ userInfo.username }}님</span>
            </template>
            <b-dropdown-item href="#"
              ><router-link
                :to="{ name: 'MyPage' }"
                class="link align-self-center"
                >내정보보기</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item href="#" @click.prevent="onClickLogout"
              >로그아웃</b-dropdown-item
            >
          </b-nav-item-dropdown>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-else>
          <b-nav-item-dropdown right>
            <template #button-content>
              <b-icon icon="people" font-scale="2"></b-icon>
            </template>
            <b-dropdown-item href="#"
              ><router-link :to="{ name: 'SignUp' }" class="link"
                ><b-icon icon="person-circle"></b-icon> 회원가입</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item href="#"
              ><router-link :to="{ name: 'SignIn' }" class="link"
                ><b-icon icon="key"></b-icon> 로그인</router-link
              ></b-dropdown-item
            >
          </b-nav-item-dropdown>
        </b-navbar-nav>
        <!-- <span>{{ userInfo.username }}님</span> -->
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  name: "NaviBar",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "Home" });
    },
  },
};
</script>

<style scoped>
.navbar {
  background-image: linear-gradient(
    134deg,
    rgb(19, 183, 207) -5%,
    rgb(54, 91, 180) 56%,
    rgb(54, 91, 180) 56%
  );
}
span {
  color: black;
}
a .navbar-dark .navbar-nav .nav-link {
  color: black;
}

.nav-item {
  color: black;
}
/* .navbar-dark .navbar-nav .nav-link:hover {
  color: black;
} */
a {
  color: rgb(34, 34, 34);
}

svg {
  color: black;
}

.navbar-toggler {
  background-color: #eeeeee;
}
</style>

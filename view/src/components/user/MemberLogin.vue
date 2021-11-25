<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col> <img class="image" src="@/assets/happyhouse.png" /> </b-col>
    </b-row>
    <b-row>
      <b-col cols="2"></b-col>
      <b-col cols="8">
        <b-card
          class="text-center mt-3 login-form"
          style="max-width: 40rem"
          align="left"
        >
          <b-form class="text-left">
            <!-- <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            > -->
            <b-form-group>
              <b-form-input
                id="userid"
                v-model="user.userid"
                required
                placeholder="아이디"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group>
              <b-form-input
                type="password"
                id="userpwd"
                v-model="user.userpwd"
                required
                placeholder="비밀번호"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <!-- <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            > -->
            <div
              id="idresult"
              v-if="isLoginError"
              class="mt-1 error"
              v-text="text"
            ></div>
            <b-button type="button" class="mt-5" @click="confirm"
              >로그인</b-button
            >
            <b-col class="text-right mt-1">
              <span class="signup"
                >아직 회원이 아니신가요?
                <router-link :to="{ name: 'SignUp' }">회원가입</router-link>
              </span>
            </b-col>
            <!-- <b-button
              type="button"
              variant="success"
              class="m-1"
              @click="movePage"
              >회원가입</b-button
            > -->
          </b-form>
        </b-card>
      </b-col>
      <b-col cols="2"></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
      },
      text: "아이디 또는 비밀번호를 확인하세요.",
    };
  },
  created() {
    this.SET_IS_LOGIN_ERROR(false);
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    ...mapMutations(memberStore, ["SET_IS_LOGIN_ERROR"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "Home" });
      }
    },
    movePage() {
      this.$router.push({ name: "SignUp" });
    },
  },
};
</script>

<style scoped>
.image {
  width: 350px;
  height: 200px;
}
.login-form {
  margin-left: auto;
  margin-right: auto;
  height: 300px;
}
.form-group {
  margin-bottom: 0;
  /* height: 60px; */
}
.form-control {
  height: 60px;
}

.btn {
  width: 100%;
  height: 60px;
}

.btn.btn-secondary {
  border: none;
  background-color: rgb(18, 184, 134);
}
.btn.btn-secondary:hover {
  background-color: rgb(58, 238, 127);
}

.error {
  color: red;
}
.signup {
  text-align: right;
}
</style>

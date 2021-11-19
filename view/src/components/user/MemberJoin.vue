<template>
  <div class="container text-center mt-3">
    <div class="col-lg-8 mx-auto">
      <h2 class="p-3 mb-3 shadow bg-light">
        <mark class="orange">회원가입</mark>
      </h2>
      <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
        <form id="memberform" class="text-left mb-3" method="post" action="">
          <input type="hidden" id="email" name="email" />
          <div class="form-group">
            <label for="username">이름</label>
            <input
              type="text"
              class="form-control"
              id="username"
              name="userName"
              v-model="user.username"
              required
            />
          </div>
          <div class="form-group">
            <label for="userid">아이디</label>
            <input
              type="text"
              class="form-control"
              id="userid"
              required
              name="userId"
              v-model="user.userid"
              @keyup="check"
            />
            <div
              id="idresult"
              class="mt-1"
              v-text="text"
              v-bind:class="{
                'text-dark': text_dark,
                'text-danger': text_danger,
                'text-primary': text_primary,
              }"
            ></div>
          </div>
          <div class="form-group">
            <label for="userpwd">비밀번호</label>
            <input
              type="password"
              class="form-control"
              id="userpwd"
              name="userPwd"
              v-model="user.userpwd"
              required
            />
          </div>
          <div class="form-group">
            <label for="pwdcheck">비밀번호재입력</label>
            <input
              type="password"
              class="form-control"
              id="pwdcheck"
              name="pwdcheck"
              v-model="user.userpwdcheck"
              required
            />
          </div>
          <div
            id="idresult"
            class="mt-1"
            v-text="textPwd"
            v-bind:class="{
              'textPwd-dark': textPwd_dark,
              'textPwd-danger': textPwd_danger,
              'textPwd-primary': textPwd_primary,
            }"
          ></div>
          <div class="form-group">
            <label for="emailid">이메일</label><br />
            <div id="email" class="custom-control-inline">
              <input
                type="text"
                class="form-control"
                id="emailid"
                name="emailid"
                size="25"
                v-model="emailid"
                required
              />
              @
              <select
                class="form-control"
                id="emaildomain"
                name="emaildomain"
                v-model="emaildomain"
              >
                <option value="" selected disabled>선택하세요</option>
                <option value="ssafy.com">싸피</option>
                <option value="naver.com">네이버</option>
                <option value="kakao.com">카카오</option>
                <option value="google.com">구글</option>
              </select>
            </div>
          </div>

          <div class="form-group text-center">
            <button
              type="button"
              id="registerBtn"
              class="btn btn-outline-primary"
              @click="join"
            >
              회원가입
            </button>
            <button type="reset" class="btn btn-outline-danger">초기화</button>
          </div>
        </form>
      </b-card>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
const memberStore = "memberStore";
export default {
  name: "MemberJoin",
  data() {
    return {
      user: {
        userid: "",
        username: "",
        userpwd: "",
        email: "",
        userpwdcheck: "",
      },
      emailid: "",
      emaildomain: "",
      isValid: false,
      text: "",
      textPwd: "",
      text_dark: false,
      text_primary: false,
      text_danger: false,
      textPwd_dark: false,
      textPwd_primary: false,
      textPwd_danger: false,
    };
  },
  computed: {
    ...mapState(memberStore, ["isJoin", "isJoinError", "isId"]),
  },
  methods: {
    ...mapActions(memberStore, ["joinConfirm", "checkId"]),

    async join() {
      this.checkPwd();
      this.checkEmail();
      if (this.isValid) {
        await this.joinConfirm(this.user);
        if (this.isJoin) {
          alert("회원가입 완료");
          this.$router.push({ name: "SignIn" });
        }
      }
    },
    async check() {
      // let isId = false;
      if (this.user.userid.length < 6 || this.user.userid.length > 16) {
        this.text = "아이디는 6자이상 16자이하입니다.";
        this.isValid = false;
        this.text_dark = true;
        this.text_primary = false;
        this.text_danger = false;
      } else {
        await this.checkId(this.user.userid);
        if (!this.isId) {
          this.text = `${this.user.userid}는 사용가능합니다`;
          this.text_dark = false;
          this.text_danger = false;
          this.text_primary = true;
          console.log("true");
        } else {
          this.text = `${this.user.userid}는 사용할 수 없습니다.`;
          this.text_dark = false;
          this.text_danger = true;
          this.text_primary = false;
          console.log("false");
        }
      }
    },

    checkPwd() {
      if (this.user.userpwd !== this.user.userpwdcheck) {
        this.textPwd = "비밀번호가 일치하지 않습니다.";
        this.textPwd_dark = false;
        this.textPwd_danger = true;
        this.textPwd_primary = false;
        this.isValid = false;
      } else if (this.user.userpwd === "") {
        this.textPwd = "비밀번호를 입력해주세요.";
        this.textPwd_dark = true;
        this.textPwd_danger = false;
        this.textPwd_primary = false;
        this.isValid = false;
      } else {
        this.textPwd = "비밀번호가 일치합니다.";
        this.textPwd_dark = false;
        this.textPwd_danger = false;
        this.textPwd_primary = true;
        this.isValid = true;
      }
    },

    checkEmail() {
      if (this.emaildomain === "") {
        this.isValid = false;
        alert("이메일 도메인을 선택해주세요");
      } else {
        this.user.email = `${this.emailid}@${this.emaildomain}`;
        console.log(this.user.email);
      }
    },
  },
};
</script>

<style>
.text-danger {
  color: red;
}
.text-primary {
  color: blue;
}
.text-dark {
  color: grey;
}

.textPwd-danger {
  color: red;
}
.textPwd-primary {
  color: blue;
}
.textPwd-dark {
  color: grey;
}
</style>

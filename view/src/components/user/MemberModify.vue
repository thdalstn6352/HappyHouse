<template>
  <div class="container text-center mt-3">
    <div class="col-lg-8 mx-auto">
      <h2 class="p-3 mb-3 shadow bg-light">
        <mark class="orange">정보 수정</mark>
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
              disabled
            />
          </div>
          <div class="form-group">
            <label for="userid">아이디</label>
            <input
              type="text"
              class="form-control"
              id="userid"
              name="userId"
              v-model="user.userid"
              disabled
            />
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
            id="pwdresult"
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
              @click="modify"
            >
              정보 수정
            </button>
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
      textPwd: "",
      textPwd_dark: false,
      textPwd_primary: false,
      textPwd_danger: false,
    };
  },
  created() {
    console.log(this.userInfo);
    this.user = this.userInfo;
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["modifyInfo"]),

    async modify() {
      this.checkPwd();
      this.checkEmail();
      if (this.isValid) {
        await this.modifyInfo(this.user);
        this.$router.push({ name: "MyPage" });
        // if (this.isJoin) {
        //   alert("회원가입 완료");
        //   this.$router.push({ name: "SignIn" });
        // }
      }
    },
    checkPwd() {
      if (this.user.userpwd !== this.user.userpwdcheck) {
        this.textPwd = "비밀번호가 일치하지 않습니다.";
        this.textPwd_dark = false;
        this.textPwd_danger = true;
        this.textPwd_primary = false;
        this.isValid = false;
      } else if (this.user.userpwd === null) {
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

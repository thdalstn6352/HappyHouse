<template>
  <b-container class="mt-4">
    <b-row>
      <b-col cols="3"></b-col>
      <b-col cols="6">
        <b-row>
          <b-col class="title text-left mb-5 pb-3 pl-0">내정보 수정하기 </b-col>
        </b-row>
        <b-row>
          <b-col cols="2" class="image">
            <img class="profile-image" src="@/assets/cat.jpeg" />
          </b-col>
          <b-col cols="10" class="text-left id">
            <dl>
              <dt>아이디</dt>
              <dd>{{ userInfo.userid }}</dd>
            </dl>
          </b-col>
        </b-row>
        <b-row class="mt-4 name">
          <dl>
            <dt>이름</dt>
            <dd>
              {{ userInfo.username }}
            </dd>
          </dl>
        </b-row>
        <b-row class="mt-4 date">
          <dl>
            <dt>생년월일</dt>
            <dd>1996년 09월 27일</dd>
          </dl>
        </b-row>
        <b-row class="mt-4 email">
          <label for="email">이메일</label>
          <input
            type="email"
            class="form-control"
            id="email"
            name="email"
            v-model="user.email"
            required
          />
        </b-row>
        <b-row class="mt-4 password">
          <label for="userpwd">비밀번호</label>
          <input
            type="password"
            class="form-control"
            id="userpwd"
            name="userPwd"
            v-model="user.userpwd"
            required
          />
        </b-row>
        <b-row class="mt-4 password-confirm">
          <label for="pwdcheck">비밀번호재입력</label>
          <input
            type="password"
            class="form-control"
            id="pwdcheck"
            name="pwdcheck"
            v-model="user.userpwdcheck"
            required
          />
        </b-row>
        <b-row>
          <div
            id="pwdresult"
            class="mt-1 p-1"
            v-text="textPwd"
            v-bind:class="{
              'textPwd-dark': textPwd_dark,
              'textPwd-danger': textPwd_danger,
              'textPwd-primary': textPwd_primary,
            }"
          ></div>
        </b-row>
      </b-col>
      <b-col cols="3"></b-col>
    </b-row>
    <b-row>
      <b-col cols="3"></b-col>
      <b-col cols="6" class="text-right p-0 mt-3 mb-3">
        <button
          type="button"
          id="registerBtn"
          class="btn btn-outline-primary"
          @click="modify"
        >
          정보 수정
        </button>
      </b-col>
      <b-col cols="3"></b-col>
    </b-row>
  </b-container>
  <!-- <div class="container text-center mt-3">
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
  </div> -->
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
    this.user.userpwd = "";
    this.user.userpwdcheck = "";
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["modifyInfo"]),

    async modify() {
      this.checkPwd();
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
      } else if (this.user.userpwd === "") {
        this.textPwd = "비밀번호를 입력해주세요.";
        this.textPwd_dark = false;
        this.textPwd_danger = true;
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
  },
};
</script>

<style scoped>
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

.image {
  padding: 0;
}
.profile-image {
  width: 70px;
  height: 70px;
  border-radius: 40%;
}

.title {
  font-size: 20px;
  border-bottom: 1px solid black;
}

.name,
.id,
.date,
.email,
.join-date,
.password,
.password-confirm {
  padding: 10px;
  border-bottom: 1px solid rgb(236, 232, 232);
}

.name dt,
.id dt,
.date dt,
.email dt,
.join-date dt {
  text-align: left;
  font-size: 12px;
  font-weight: 400;
  margin-bottom: 10px;
  color: rgb(85, 83, 83);
}

.name dl,
.name dd,
.id dl,
.id dd,
.date dl,
.date dd,
.email dl,
.email dd,
.join-date dl,
.join-date dd {
  margin-bottom: 0;
}

.btn.btn-secondary {
  border: none;
  background-color: rgb(18, 184, 134);
}
.btn.btn-secondary:hover {
  background-color: rgb(58, 238, 127);
}

.form-control {
  border: none;
}

input {
  padding: 0;
}
</style>

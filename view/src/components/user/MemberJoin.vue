<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <div class="container text-center mt-3">
        <div class="col-lg-8 mx-auto">
          <h2 class="p-3 mb-3 shadow bg-light">
            <mark class="orange">회원가입</mark>
          </h2>
          <form id="memberform" class="text-left mb-3" method="post" action="">
            <input type="hidden" id="email" name="email" />
            <div class="form-group">
              <label for="username">이름</label>
              <input
                type="text"
                class="form-control"
                id="username"
                name="userName"
              />
            </div>
            <div class="form-group">
              <label for="userid">아이디</label>
              <input
                type="text"
                class="form-control"
                id="userid"
                name="userId"
              />
              <div id="idresult" class="mt-1"></div>
            </div>
            <div class="form-group">
              <label for="userpwd">비밀번호</label>
              <input
                type="password"
                class="form-control"
                id="userpwd"
                name="userPwd"
              />
            </div>
            <div class="form-group">
              <label for="pwdcheck">비밀번호재입력</label>
              <input
                type="password"
                class="form-control"
                id="pwdcheck"
                name="pwdcheck"
              />
            </div>
            <div class="form-group">
              <label for="emailid">이메일</label><br />
              <div id="email" class="custom-control-inline">
                <input
                  type="text"
                  class="form-control"
                  id="emailid"
                  name="emailid"
                  size="25"
                />
                @
                <select
                  class="form-control"
                  id="emaildomain"
                  name="emaildomain"
                >
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
              >
                회원가입
              </button>
              <button type="reset" class="btn btn-outline-danger">
                초기화
              </button>
            </div>
          </form>
        </div>
      </div>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "MemberJoin",
};
</script>

<style></style>

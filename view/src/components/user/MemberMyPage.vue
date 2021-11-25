<template>
  <b-container class="mt-4">
    <b-row>
      <b-col cols="3"></b-col>
      <b-col cols="6">
        <b-row>
          <b-col class="title text-left mb-5 pb-3 pl-0">내정보 관리 </b-col>
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
            <dd>{{ userInfo.username }}</dd>
          </dl>
        </b-row>
        <b-row class="mt-4 date">
          <dl>
            <dt>생년월일</dt>
            <dd>1996년 09월 27일</dd>
          </dl>
        </b-row>
        <b-row class="mt-4 email">
          <dl>
            <dt>이메일</dt>
            <dd>{{ userInfo.email }}</dd>
          </dl>
        </b-row>
        <b-row class="mt-4 join-date">
          <dl>
            <dt>가입일</dt>
            <dd>{{ userInfo.joindate }}</dd>
          </dl>
        </b-row>
      </b-col>
      <b-col cols="3"></b-col>
    </b-row>
    <b-row>
      <b-col cols="3"></b-col>
      <b-col cols="6" class="text-right p-0 mt-3">
        <b-button class="h-100 btn" @click="moveModifyMyInfo">수정</b-button>
        <b-button variant="danger" class="ml-2" @click="moveDeleteMyInfo"
          >회원탈퇴</b-button
        >
      </b-col>
      <b-col cols="3"></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  methods: {
    ...mapActions(memberStore, ["deleteInfo"]),
    moveModifyMyInfo() {
      this.$router.push({
        name: "Modify",
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    moveDeleteMyInfo() {
      console.log("삭제삭제");
      this.deleteInfo(this.userInfo.userid);
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style scoped>
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
.join-date {
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
</style>

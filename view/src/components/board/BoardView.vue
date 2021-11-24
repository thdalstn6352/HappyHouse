<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle">목록</b-button>
      </b-col>
      <b-col
        v-if="userInfo !== null && article.userid === userInfo.userid"
        class="text-right"
      >
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="doDeleteArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <!-- <b-card
          :header-html="`<h3>${article.articleno}.
          ${article.subject} [${article.hit}]</h3><div><h6>${article.userid}</div><div>${article.regtime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left content">
            <div v-html="message"></div>
          </b-card-body>
        </b-card> -->
        <div class="header">
          <div id="title">
            <h1>{{ article.subject }}</h1>
          </div>

          <div class="userinfo">
            <span class="userid">{{ article.userid }}</span>
            <span> · </span>
            <span class="date">{{ this.changeDateFormat }}</span>
          </div>
          <!-- <div class="hit">
            <b-icon icon="eye" font-scale="1"></b-icon>
            <span>{{ article.hit }}</span>
          </div> -->
        </div>
        <div class="content">
          <div v-html="message"></div>
        </div>
        <div class="profile">
          <!-- <img src="" /> -->
          <div class="username">
            {{ article.username }}
          </div>
          <div class="userintro">
            {{ article.userintro }}
          </div>
        </div>
      </b-col>
    </b-row>
    <b-row mb-5>
      <b-col cols="6">
        <b-card class="text-left">
          <font-awesome-icon icon="arrow-circle-left" />
          <div>이전 공지사항</div>
          <div></div>
        </b-card>
      </b-col>
      <b-col cols="6">
        <b-card class="text-right">
          <font-awesome-icon icon="arrow-circle-right" />
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import moment from "moment";
// import http from "@/util/http-common";
import { mapActions, mapState, mapGetters } from "vuex";

const boardStore = "boardStore";
const memberStore = "memberStore";

export default {
  data() {
    return {
      articleno: "",
    };
  },
  computed: {
    ...mapGetters(boardStore, ["article"]),
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
    changeDateFormat() {
      return moment(new Date(this.article.regtime)).format("YYYY년 MM월 DD일");
    },
  },
  created() {
    this.articleno = this.$route.params.articleno;
    this.getArticle(this.articleno);
    // this.$store.dispatch("getArticle", `/board/${this.articleno}`);
  },
  methods: {
    ...mapActions(boardStore, ["getArticle", "deleteArticle"]),
    listArticle() {
      this.$router.push({ name: "BoardList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "BoardUpdate",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    async doDeleteArticle() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        await this.deleteArticle(this.articleno);
      }
      this.listArticle();
    },
  },
};
</script>

<style>
.content {
  height: 300px;
}
#title {
  margin-bottom: 30px;
}
#title h1 {
  font-weight: 600;
}
.userid {
  font-weight: 600;
}
.date {
  color: grey;
}
.profile,
.header {
  border-bottom: 1px solid rgb(231, 231, 231);
  padding-bottom: 15px;
  margin-bottom: 30px;
}
.userinfo,
.hit {
  display: inline-block;
}
.hit {
  float: right;
}
.content {
  font-size: 30px;
  margin-bottom: 50px;
}
</style>

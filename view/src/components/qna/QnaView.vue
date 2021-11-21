<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>QnA 보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle">목록</b-button>
      </b-col>
      <b-col v-if="qna.userid === userInfo.userid" class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <qna-view-detail :qna="qna" />
    <comment-write :qnano="this.qnano" />

    <comment-write
      v-if="isModifyShow && this.modifyComment != null"
      :modifyComment="this.modifyComment"
      @modify-comment-cancel="onModifyCommentCancel"
    />

    <comment
      v-for="(comment, index) in comments"
      :key="index"
      :comment="comment"
      @modify-comment="onModifyComment"
    />
  </b-container>
</template>

<script>
import QnaViewDetail from "@/components/qna/child/QnaViewDetail";
import CommentWrite from "@/components/qna/child/comment/CommentWrite";
import Comment from "@/components/qna/child/comment/Comment";
import { mapActions, mapGetters, mapState } from "vuex";

const qnaStore = "qnaStore";
const memberStore = "memberStore";

export default {
  data() {
    return {
      qnano: Number,
      isModifyShow: false,
      modifyComment: Object,
    };
  },
  components: {
    QnaViewDetail,
    CommentWrite,
    Comment,
  },
  computed: {
    ...mapGetters(qnaStore, ["qna", "comments"]),
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
    // changeDateFormat() {
    //   return moment(new Date(this.article.regtime)).format(
    //     "YYYY.MM.DD hh:mm:ss"
    //   );
    // },
  },
  created() {
    this.qnano = parseInt(this.$route.params.qnano);
    this.getQna(this.qnano);
    this.getComments(this.qnano);
  },
  methods: {
    ...mapActions(qnaStore, ["getQna", "getComments"]),
    listArticle() {
      this.$router.push({ name: "QnaList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "QnaUpdate",
        params: { qnano: this.qnano },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "QnaDelete",
          params: { qnano: this.qnano },
        });
      }
    },
    onModifyComment(comment) {
      this.modifyComment = comment;
      this.isModifyShow = true;
      console.log(this.isModifyShow);
    },
    onModifyCommentCancel(isShow) {
      this.isModifyShow = isShow;
    },
  },
};
</script>

<style></style>

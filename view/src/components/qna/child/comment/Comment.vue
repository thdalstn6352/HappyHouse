<template>
  <b-container class="bv-example-row bg-light mb-2 p-2">
    <b-row class="p-23">
      <b-col class="text-left text-bold"
        ><b>{{ comment.userid }}</b></b-col
      >
      <b-col class="text-right">{{ comment.regtime }}</b-col>
    </b-row>
    <b-row class="p-3">
      <b-col class="text-left" v-html="enterToBr(comment.content)"></b-col>
    </b-row>
    <b-row v-if="comment.userid === userInfo.userid">
      <b-col class="text-right">
        <b-link @click="modifyCommentView">수정</b-link> |
        <b-link @click="delComment">삭제</b-link>
      </b-col>
    </b-row>
    <!-- 로그인 기능 구현 후 로그인한 자신의 글에만 보이게 한다. -->
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";
// import http from "@/util/http-common";
const qnaStore = "qnaStore";
const memberStore = "memberStore";
export default {
  name: "comment",
  props: {
    // 상위 component에서 전달한 도서평을 받는다.
    comment: Object,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(qnaStore, ["deleteComment"]),
    modifyCommentView() {
      // 상위 component의 modify-comment인 event listener에 전달.
      this.$emit("modify-comment", {
        answerno: this.comment.answerno,
        content: this.comment.content,
        qnano: this.comment.qnano,
      });
    },
    delComment() {
      console.log(this.comment);
      this.deleteComment(this.comment);
      // if (confirm("정말로 삭제?")) {
      //   // 서버로 삭제할 도서평번호를 전달.
      //   http.delete(`/comment/${this.comment.comment_no}`).then(({ data }) => {
      //     let msg = "삭제 처리시 문제가 발생했습니다.";
      //     if (data === "success") {
      //       msg = "삭제가 완료되었습니다.";
      //     }
      //     alert(msg);
      //     // 도서평(댓글) 얻기.
      //     this.$store.dispatch("getComments", `/comment/${this.comment.isbn}`);
      //   });
      // }
    },
    enterToBr(str) {
      if (str) return str.replace(/(?:\r\n|\r|\n)/g, "<br />");
    },
  },
};
</script>

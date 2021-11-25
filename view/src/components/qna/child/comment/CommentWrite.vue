<template>
  <b-row class="mb-3 mt-2">
    <b-col cols="12">
      <b-form-textarea
        id="comment"
        placeholder="댓글을 작성하세요"
        v-model="content"
        rows="4"
        class="mb-3"
        no-resize
      ></b-form-textarea>
    </b-col>
    <b-col cols="12" class="text-right">
      <b-button class="h-100 btn" @click="registComment">댓글 작성</b-button>
    </b-col>
  </b-row>
</template>

<script>
import { mapActions, mapState } from "vuex";
const qnaStore = "qnaStore";
const memberStore = "memberStore";

export default {
  name: "commentwrite",
  data() {
    return {
      // 차후 작성자 이름은 로그인 구현후 로그인한 사용자로 바꾼다.
      userid: "",
      content: "",
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  props: {
    qnano: { type: Number },
    modifyComment: { type: Object },
  },
  methods: {
    ...mapActions(qnaStore, ["getComments", "writeComment", "updateComment"]),
    registComment() {
      this.writeComment({
        userid: this.userInfo.userid,
        content: this.content,
        qnano: this.qnano,
      });
      this.content = "";
    },
    modComment() {
      this.updateComment({
        answerno: this.modifyComment.answerno,
        content: this.modifyComment.content,
        qnano: this.modifyComment.qnano,
      });
      this.updateCommentCancel();
    },
    updateCommentCancel() {
      this.$emit("modify-comment-cancel", false);
    },
  },
};
</script>
<style scoped>
.btn-secondary {
  border: none;
  background-color: rgb(18, 184, 134);
}
.btn-secondary:hover {
  background-color: rgb(58, 238, 127);
}
</style>

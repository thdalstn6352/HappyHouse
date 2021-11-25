<template>
  <b-container class="bv-example-row mt-4 container">
    <div class="profile">
      <b-row>
        <b-col cols="6" class="text-left">
          <b-row>
            <b-col cols="2" class="pl-0 pr-0">
              <img src="@/assets/cat.jpeg" class="image" />
            </b-col>
            <b-col cols="9" class="text-left pl-0 pt-3">
              <div class="username">
                {{ comment.userid }}
              </div>
              <div>{{ comment.regtime }}</div>
            </b-col>
          </b-row>
        </b-col>
        <b-col
          v-if="comment.userid === userInfo.userid"
          cols="6"
          class="text-right mt-4"
          :style="commentBoxDisplay"
        >
          <b-link class="link" @click="modifyComment()">수정</b-link>
          <b-link class="link ml-2" @click="delComment">삭제</b-link>
        </b-col>
      </b-row>
      <b-col cols="12" class="mt-4 text-left pl-0">
        <b-row class="mb-3 mt-2" :style="modifyBoxDisplay">
          <b-col cols="12">
            <b-form-textarea
              id="comment"
              placeholder="댓글을 작성하세요"
              v-model="content"
              rows="4"
            ></b-form-textarea>
          </b-col>
          <b-col cols="12" class="text-right mt-3">
            <b-button
              variant="outline-info"
              class="mr-2 h-100"
              @click="modComment"
              >댓글 수정</b-button
            >
            <b-button
              variant="outline-danger"
              class="h-100"
              @click="updateCommentCancel"
              >취소</b-button
            >
          </b-col>
        </b-row>
      </b-col>
      <b-col cols="12" class="mt-4 text-left pl-0">
        <div :style="commentBoxDisplay">
          <b-col class="text-left" v-html="enterToBr(comment.content)"></b-col>
        </div>
      </b-col>
    </div>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";
// import http from "@/util/http-common";
const qnaStore = "qnaStore";
const memberStore = "memberStore";
export default {
  name: "comment",
  data() {
    return {
      modifyBoxDisplay: {
        display: "none",
      },
      commentBoxDisplay: {
        display: "block",
      },
      modifyButtonDisplay: {
        flag: true,
      },
      content: "",
    };
  },
  props: {
    // 상위 component에서 전달한 도서평을 받는다.
    comment: Object,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(qnaStore, ["isModify"]),
  },
  methods: {
    ...mapActions(qnaStore, ["deleteComment", "updateComment"]),
    modifyComment() {
      if (this.modifyButtonDisplay.flag) {
        this.content = this.comment.content;
        this.modifyBoxDisplay.display = "block";
        this.commentBoxDisplay.display = "none";
        this.modifyButtonDisplay.flag = !this.modifyButtonDisplay.flag;
      } else {
        this.modifyBoxDisplay.display = "none";
        this.commentBoxDisplay.display = "block";
        this.modifyButtonDisplay.flag = !this.modifyButtonDisplay.flag;
      }
    },
    delComment() {
      this.deleteComment(this.comment);
    },
    enterToBr(str) {
      if (str) return str.replace(/(?:\r\n|\r|\n)/g, "<br />");
    },
    modComment() {
      this.updateComment({
        answerno: this.comment.answerno,
        content: this.content,
        qnano: this.comment.qnano,
      });
      this.updateCommentCancel();
    },
    updateCommentCancel() {
      this.modifyComment();
      // this.$emit("modify-comment-cancel", false);
    },
  },
};
</script>
<style scoped>
.container {
  border-bottom: 1px solid rgb(231, 231, 231);
  padding-bottom: 30px;
  margin-bottom: 40px;
}
.link {
  font-size: 15px;
  color: rgb(134, 142, 150);
}
.image {
  width: 70px;
  height: 70px;
  border-radius: 50%;
}
</style>

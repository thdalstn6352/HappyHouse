<template>
  <b-container class="bv-example-row mt-3">
    <qna-view-detail :qna="qna" />
    <div class="comments">
      <b-row>
        <b-col class="text-left">
          <span class="comment-count">{{ comments.length }}개의 댓글</span>
        </b-col>
      </b-row>
      <comment-write :qnano="this.qnano" />
      <!-- <comment-write
        v-if="isModifyShow && this.modifyComment != null"
        :modifyComment="this.modifyComment"
        @modify-comment-cancel="onModifyCommentCancel"
      /> -->
      <comment
        v-for="(comment, index) in comments"
        :key="index"
        :comment="comment"
        @modify-comment="onModifyComment"
      />
    </div>
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

<style scoped>
.comments {
  padding: 15px;
}

.comments .comment-count {
  color: rgb(52, 58, 64);
  font-weight: 600;
}
</style>

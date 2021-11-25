<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group id="subject-group" label="제목:" label-for="subject">
          <b-form-input
            id="subject"
            v-model="qnaForm.subject"
            type="text"
            required
            placeholder="제목을 입력해주세요."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="qnaForm.content"
            placeholder="당신의 글을 적어보세요..."
            rows="20"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>
        <b-col class="text-right pr-0">
          <b-button
            type="submit"
            variant="outline-info"
            class="m-1"
            v-if="this.type === 'register'"
            >글작성</b-button
          >
          <b-button type="submit" variant="outline-info" class="m-1" v-else
            >글수정</b-button
          >
          <b-button type="reset" variant="outline-danger" class="m-1"
            >초기화</b-button
          >
        </b-col>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { mapActions, mapGetters, mapState } from "vuex";
// import http from "@/util/http-common";
const qnaStore = "qnaStore";
const memberStore = "memberStore";

export default {
  name: "QnaWriteForm",
  data() {
    return {
      qnaForm: {
        articleno: 0,
        userid: "",
        subject: "",
        content: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapGetters(qnaStore, ["qna"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    if (this.type === "modify") {
      this.getQna(`${this.$route.params.qnano}`);
      this.qnaForm = this.qna;
      this.isUserid = true;
    }
  },
  methods: {
    ...mapActions(qnaStore, ["getQna", "writeQna", "modifyQna"]),
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      err &&
        !this.qnaForm.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.qnaForm.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type === "register" ? this.registQna() : this.updateQna();
    },
    onReset(event) {
      event.preventDefault();
      this.article.articleno = 0;
      this.article.subject = "";
      this.article.content = "";
      this.$router.push({ name: "QnaList" });
    },
    async registQna() {
      this.qnaForm.userid = this.userInfo.userid;
      await this.writeQna(this.qnaForm);
      this.moveList();
    },
    async updateQna() {
      await this.modifyQna(this.qnaForm);
      this.moveView();
    },
    moveList() {
      this.$router.push({ name: "QnaList" });
    },
    moveView() {
      this.$router.push({ name: "QnaView" });
    },
  },
};
</script>

<style></style>

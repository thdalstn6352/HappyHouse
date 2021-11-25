<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="subject-group"
          label="제목:"
          label-for="subject"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="subject"
            ref="subject"
            v-model="articleForm.subject"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            ref="content"
            v-model="articleForm.content"
            placeholder="내용 입력..."
            rows="20"
            max-rows="25"
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
// import http from "@/util/http-common";
import { mapActions, mapGetters, mapMutations, mapState } from "vuex";
const boardStore = "boardStore";
const memberStore = "memberStore";

export default {
  name: "BoardWriteForm",
  data() {
    return {
      articleForm: {
        articleno: 0,
        userid: "",
        subject: "",
        content: "",
      },
      isUserid: false,
      // articleno: "",
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapGetters(boardStore, ["article", "isSuccess"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    if (this.type === "modify") {
      this.getArticle(`${this.$route.params.articleno}`);
      this.articleForm = this.article;
      this.isUserid = true;
    }
  },
  methods: {
    ...mapActions(boardStore, ["getArticle", "writeArticle", "modifyArticle"]),
    ...mapMutations(boardStore, ["setSuccess"]),
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";

      err &&
        !this.articleForm.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.articleForm.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.updateArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.articleForm.articleno = 0;
      this.articleForm.subject = "";
      this.articleForm.content = "";
      this.$router.push({ name: "BoardList" });
    },
    async registArticle() {
      this.articleForm.userid = this.userInfo.userid;
      await this.writeArticle(this.articleForm);
      this.moveList();
    },
    async updateArticle() {
      console.log("수정");
      await this.modifyArticle(this.articleForm);
      this.moveView();
    },
    moveList() {
      this.$router.push({ name: "BoardList" });
    },
    moveView() {
      this.$router.push({ name: "BoardView" });
    },
  },
};
</script>

<style></style>

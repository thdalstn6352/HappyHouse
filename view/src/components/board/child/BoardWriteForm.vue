<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="userid-group"
          label="작성자:"
          label-for="userid"
          description="작성자를 입력하세요."
        >
          <b-form-input
            id="userid"
            :disabled="isUserid"
            v-model="articleForm.userid"
            type="text"
            required
            placeholder="작성자 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="subject-group"
          label="제목:"
          label-for="subject"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="subject"
            v-model="articleForm.subject"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="articleForm.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'register'"
          >글작성</b-button
        >
        <b-button type="submit" variant="primary" class="m-1" v-else
          >글수정</b-button
        >
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
// import http from "@/util/http-common";
import { mapActions, mapGetters, mapMutations } from "vuex";
const boardStore = "boardStore";

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
  },
  created() {
    // this.setSuccess(false);
    console.log(this.isSuccess);
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
      !this.articleForm.userid &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
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
    registArticle() {
      console.log("1" + this.isSuccess);
      this.writeArticle(this.articleForm);
      console.log("2" + this.isSuccess);
      if (this.isSuccess) this.moveList();
      // this.moveList();
    },
    updateArticle() {
      this.modifyArticle(this.articleForm);
    },
    moveList() {
      this.$router.push({ name: "BoardList" });
    },
  },
};
</script>

<style></style>

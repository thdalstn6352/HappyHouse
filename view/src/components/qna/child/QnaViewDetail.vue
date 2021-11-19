<template>
  <b-row class="mb-1">
    <b-col>
      <b-card
        :header-html="`<h3>${qna.qnano}.
          ${qna.subject}</h3><div><h6>${qna.userid}</div><div>${qna.regtime}</h6></div>`"
        class="mb-2"
        border-variant="dark"
        no-body
      >
        <b-card-body class="text-left">
          <div v-html="message"></div>
        </b-card-body>
      </b-card>
    </b-col>
  </b-row>
</template>

<script>
export default {
  name: "qnaviewdetail",
  computed: {
    message() {
      console.log(this.qna);
      if (this.qna.content) {
        return this.qna.content.split("\n").join("<br>");
      } else {
        return "";
      }
    },
  },
  props: {
    qna: Object,
  },
};
</script>

<style></style>

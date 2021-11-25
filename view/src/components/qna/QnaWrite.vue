<template>
  <b-container class="bv-example-row mt-3">
    <qna-write-form type="register" />
  </b-container>
</template>

<script>
import QnaWriteForm from "./child/QnaWriteForm.vue";

export default {
  name: "QnaWrite",
  components: {
    QnaWriteForm,
  },
};
</script>

<style></style>

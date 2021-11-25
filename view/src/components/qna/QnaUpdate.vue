<template>
  <b-container class="bv-example-row mt-3">
    <qna-write-form type="modify" />
  </b-container>
</template>

<script>
import QnaWriteForm from "./child/QnaWriteForm.vue";

export default {
  name: "QnaUpdate",
  components: {
    QnaWriteForm,
  },
};
</script>

<style></style>

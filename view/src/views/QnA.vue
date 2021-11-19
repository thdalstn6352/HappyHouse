<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-purple">
      <b-icon icon="question-circle"></b-icon> QnA Service
    </h3>
    <router-view></router-view>
  </b-container>
</template>
<script>
export default {
  name: "QnA",
};
</script>
<style scoped>
.underline-purple {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(183, 9, 226, 0) 70%,
    rgba(230, 11, 193, 0.3) 30%
  );
}
</style>

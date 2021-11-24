<template>
  <b-container class="bv-example-row text-center home" fluid>
    <b-row>
      <b-col></b-col>
      <b-col cols="12">
        <p>Vue + Bootstrap활용.</p>
        <p>Bootstrap-vue는 버전 <b>4.5.3</b>을 권장합니다.</p>
        <p><b>BoardList.vue</b>를 바꿔가면서 테스트하세요.</p>
        <p>Bootstrap의 <b>table</b> 사용법을 익히게됩니다.</p>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "Main",
  props: {
    msg: String,
  },
};
</script>

<style scoped>
.home {
  background-image: linear-gradient(
    134deg,
    rgb(19, 183, 207) -5%,
    rgb(54, 91, 180) 56%,
    rgb(54, 91, 180) 56%
  );
}
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>

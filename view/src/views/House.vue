<template>
  <b-container fluid class="bv-example-row mt-3 text-center">
    <navi-bar />
    <h3 class="underline-orange">
      <b-icon icon="house-fill"></b-icon> House Service
    </h3>
    <router-view></router-view>
  </b-container>
</template>
<script>
import NaviBar from "@/components/layout/NaviBar.vue";
export default {
  name: "House",
  components: {
    NaviBar,
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}
</style>

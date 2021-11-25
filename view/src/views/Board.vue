<template>
  <b-container class="bv-example-row mt-3 text-center board" fluid>
    <navi-bar />
    <router-view :key="$route.fullPath"></router-view>
  </b-container>
</template>
<script>
import NaviBar from "@/components/layout/NaviBar.vue";
export default {
  name: "Board",
  components: {
    NaviBar,
  },
};
</script>
<style scoped></style>

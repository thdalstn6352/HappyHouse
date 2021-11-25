<template>
  <b-container class="bv-example-row mt-3 text-center" fluid>
    <navi-bar />
    <router-view :key="$route.fullPath"></router-view>
  </b-container>
</template>
<script>
import NaviBar from "@/components/layout/NaviBar.vue";
export default {
  name: "QnA",
  components: {
    NaviBar,
  },
};
</script>
<style></style>

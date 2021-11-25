<template>
  <b-container class="bv-example-row mt-3 text-center board">
    <router-view :key="$route.fullPath"></router-view>
  </b-container>
</template>
<script>
export default {
  name: "Board",
};
</script>
<style scoped></style>

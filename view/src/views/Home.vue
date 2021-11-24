<template>
  <b-container class="bv-example-row text-center home" fluid>
    <b-row>
      <b-col></b-col>
      <b-col cols="12">
        <house-search-bar></house-search-bar>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import HouseSearchBar from "@/components/house/HouseSearchBar2.vue";
export default {
  name: "Main",
  props: {
    msg: String,
  },
  components: {
    HouseSearchBar,
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

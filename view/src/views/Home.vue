<template>
  <b-container class="bv-example-row text-center" fluid>
    <div class="home">
      <navi-bar />
      <b-col cols="12">
        <house-search-bar></house-search-bar>
      </b-col>
    </div>
    <b-col cols="12">
      <house-recent-view></house-recent-view>
    </b-col>
  </b-container>
</template>

<script>
import HouseSearchBar from "@/components/house/HouseSearchBar2.vue";
import NaviBar from "@/components/layout/NaviBar.vue";
import HouseRecentView from "@/components/house/HouseRecentView.vue";
export default {
  name: "Main",
  props: {
    msg: String,
  },
  components: {
    HouseSearchBar,
    HouseRecentView,
    NaviBar,
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

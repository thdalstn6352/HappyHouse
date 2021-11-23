<template>
  <b-row
    class="m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="4" class="text-center align-self-center">
      <b-img
        thumbnail
        src="https://picsum.photos/250/250/?image=58"
        alt="Image 1"
      ></b-img>
    </b-col>
    <b-col cols="8" class="align-self-center">
      <table></table>
      <h1>{{ house.아파트 }}</h1>
      <span>{{ house.전용면적 }}<span style="font-size: 20px">㎡</span></span>
      <p>{{ house.거래금액 }}(만원)</p>
    </b-col>
  </b-row>
</template>

<script>
import { mapActions } from "vuex";
const houseStore = "houseStore";

export default {
  name: "HouseListRow",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    colorChange(flag) {
      this.isColor = flag;
    },
    selectHouse() {
      this.detailHouse(this.house);
      console.log("hihi");
      this.moveDetail();
    },
    moveDetail() {
      this.$router.push({
        name: "HouseDetail",
      });
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}

h1 {
  font-size: 18px;
  font-weight: bold;
}
</style>

<template>
  <div v-if="recents && recents.length != 0">
    <div>
      <b-card-group deck>
        <b-col cols="4">
          <b-card
            v-if="this.house[0]"
            bg-variant="light"
            class="text-center"
            :header="this.house[0].name"
            @click="moveDetailView(0)"
          >
            <b-card-text>면적 : {{ this.house[0].area }}㎡</b-card-text>
            <b-card-text>최근 거래가 : {{ this.house[0].price }}원</b-card-text>
          </b-card>
        </b-col>
        <b-col cols="4">
          <b-card
            v-if="this.house[1]"
            bg-variant="light"
            class="text-center"
            :header="this.house[1].name"
            @click="moveDetailView(1)"
          >
            <b-card-text>면적 : {{ this.house[1].area }}㎡</b-card-text>
            <b-card-text>최근 거래가 : {{ this.house[1].price }}원</b-card-text>
          </b-card>
        </b-col>
        <b-col cols="4">
          <b-card
            v-if="this.house[2]"
            bg-variant="light"
            class="text-center"
            :header="this.house[2].name"
            @click="moveDetailView(2)"
          >
            <b-card-text>면적 : {{ this.house[2].area }}㎡</b-card-text>
            <b-card-text>최근 거래가 : {{ this.house[2].price }}원</b-card-text>
          </b-card>
        </b-col>
      </b-card-group>
    </div>
    <div class="mt-3">
      <b-card-group deck>
        <b-col cols="4">
          <b-card
            v-if="this.house[3]"
            bg-variant="light"
            class="text-center"
            :header="this.house[3].name"
            @click="moveDetailView(3)"
          >
            <b-card-text>면적 : {{ this.house[3].area }}㎡</b-card-text>
            <b-card-text>최근 거래가 : {{ this.house[3].price }}원</b-card-text>
          </b-card>
        </b-col>
        <b-col cols="4">
          <b-card
            v-if="this.house[4]"
            bg-variant="light"
            class="text-center"
            :header="this.house[4].name"
            @click="moveDetailView(4)"
          >
            <b-card-text>면적 : {{ this.house[4].area }}㎡</b-card-text>
            <b-card-text>최근 거래가 : {{ this.house[4].price }}원</b-card-text>
          </b-card>
        </b-col>
        <b-col cols="4">
          <b-card
            v-if="this.house[5]"
            bg-variant="light"
            class="text-center"
            :header="this.house[5].name"
            @click="moveDetailView(5)"
          >
            <b-card-text>면적 : {{ this.house[5].area }}㎡</b-card-text>
            <b-card-text>최근 거래가 : {{ this.house[5].price }}원</b-card-text>
          </b-card>
        </b-col>
      </b-card-group>
    </div>
    <div class="mt-3">
      <b-card-group deck>
        <b-col cols="4">
          <b-card
            v-if="this.house[6]"
            bg-variant="light"
            class="text-center"
            :header="this.house[6].name"
            @click="moveDetailView(6)"
          >
            <b-card-text>면적 : {{ this.house[6].area }}㎡</b-card-text>
            <b-card-text>최근 거래가 : {{ this.house[6].price }}원</b-card-text>
          </b-card>
        </b-col>
        <b-col cols="4">
          <b-card
            v-if="this.house[7]"
            bg-variant="light"
            class="text-center"
            :header="this.house[7].name"
            @click="moveDetailView(7)"
          >
            <b-card-text>면적 : {{ this.house[7].area }}㎡</b-card-text>
            <b-card-text>최근 거래가 : {{ this.house[7].price }}원</b-card-text>
          </b-card>
        </b-col>
        <b-col cols="4">
          <b-card
            v-if="this.house[8]"
            bg-variant="light"
            class="text-center"
            :header="this.house[8].name"
            @click="moveDetailView(8)"
          >
            <b-card-text>면적 : {{ this.house[8].area }}㎡</b-card-text>
            <b-card-text>최근 거래가 : {{ this.house[8].price }}원</b-card-text>
          </b-card>
        </b-col>
      </b-card-group>
    </div>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "RecentView",
  data() {
    return {
      house: [],
      titles: [],
      content: "",
    };
  },
  created() {
    this.house = JSON.parse(localStorage.getItem("recent-view"));
    // const value = JSON.parse(localStorage.getItem("recent-view"));
    // this.title = value[0].name;
    // this.content = value[0].area;
    console.log(this.house);
  },
  methods: {
    ...mapMutations(houseStore, ["SET_DETAIL_HOUSE"]),
    moveDetailView(index) {
      this.SET_DETAIL_HOUSE(this.recents[index]);
      this.$router.push({ name: "HouseDetail" });
    },
  },
  computed: {
    ...mapState(houseStore, ["recents"]),
  },
};
</script>

<style scoped>
.card-header {
  height: 80px;
}
.card-body {
  height: 130px;
}
</style>

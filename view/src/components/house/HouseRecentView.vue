<template>
  <b-row class="mt-4 mb-4" v-if="recents && recents.length != 0">
    <b-col cols="1"></b-col>
    <b-col cols="10" class="text-center">
      <b-row class="pl-3 title">
        <span class="text-left header"><strong>최근</strong> 본 거래기록</span>
      </b-row>
      <b-card-group deck>
        <b-col cols="3">
          <b-card
            class="text-center ml-0 mr-0"
            img-src="https://cdn.pixabay.com/photo/2012/11/19/16/26/house-66627_1280.jpg"
            @click="moveDetailView(0)"
          >
            <b-card-text class="card-content apt"
              >{{ house[0].name }}아파트</b-card-text
            >
            <b-card-text class="card-content price"
              >{{ house[0].price }}원
              <span class="description"
                >({{ house[0].recent_trade }} 기준)</span
              ></b-card-text
            >
            <b-card-text class="card-content content"
              ><p class="content-detail">
                {{ house[0].floor }}층, {{ house[0].area }}㎡
              </p>
              <p>{{ house[0].address }}</p>
            </b-card-text>
          </b-card>
        </b-col>
        <b-col cols="3">
          <b-card
            class="text-center ml-0 mr-0"
            img-src="https://cdn.pixabay.com/photo/2020/10/05/18/43/building-5630441_1280.jpg"
            @click="moveDetailView(1)"
          >
            <b-card-text class="card-content apt"
              >{{ house[1].name }}아파트</b-card-text
            >
            <b-card-text class="card-content price"
              >{{ house[1].price }}원
              <span class="description"
                >({{ house[1].recent_trade }} 기준)</span
              ></b-card-text
            >
            <b-card-text class="card-content content"
              ><p class="content-detail">
                {{ house[1].floor }}층, {{ house[1].area }}㎡
              </p>
              <p>{{ house[1].address }}</p>
            </b-card-text>
          </b-card>
        </b-col>
        <b-col cols="3">
          <b-card
            class="text-center ml-0 mr-0"
            img-src="https://cdn.pixabay.com/photo/2016/11/21/15/09/apartments-1845884__340.jpg"
            @click="moveDetailView(2)"
          >
            <b-card-text class="card-content apt"
              >{{ house[2].name }}아파트</b-card-text
            >
            <b-card-text class="card-content price"
              >{{ house[2].price }}원
              <span class="description"
                >({{ house[2].recent_trade }} 기준)</span
              ></b-card-text
            >
            <b-card-text class="card-content content"
              ><p class="content-detail">
                {{ house[2].floor }}층, {{ house[2].area }}㎡
              </p>
              <p>{{ house[2].address }}</p>
            </b-card-text>
          </b-card>
        </b-col>
        <b-col cols="3">
          <b-card
            class="text-center ml-0 mr-0"
            img-src="https://cdn.pixabay.com/photo/2018/02/12/10/07/apartment-lounge-3147892_1280.jpg"
            @click="moveDetailView(3)"
          >
            <b-card-text class="card-content apt"
              >{{ house[3].name }}아파트</b-card-text
            >
            <b-card-text class="card-content price"
              >{{ house[3].price }}원
              <span class="description"
                >({{ house[3].recent_trade }} 기준)</span
              ></b-card-text
            >
            <b-card-text class="card-content content"
              ><p class="content-detail">
                {{ house[3].floor }}층, {{ house[3].area }}㎡
              </p>
              <p>{{ house[3].address }}</p>
            </b-card-text>
          </b-card>
        </b-col>
      </b-card-group>
    </b-col>
    <b-col cols="1"></b-col>
  </b-row>
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
.card-content {
  text-align: left;
  margin-bottom: 3px;
}

.card-body {
  height: 130px;
  padding-top: 10px;
  padding-left: 5px;
  border: none;
}
.card-body .apt {
  display: inline-block;
  font-weight: 500;
  color: rgb(34, 34, 34);
  margin-bottom: 5px;
  background: linear-gradient(
    180deg,
    rgba(183, 9, 226, 0) 70%,
    rgba(230, 11, 193, 0.3) 30%
  );
}

.card-body .price {
  font-size: 20px;
  font-weight: 800;
  color: rgb(34, 34, 34);
}
.card-body .price .description {
  font-size: 10px;
  font-weight: 300;
  color: rgb(34, 34, 34);
}
.card-body .content {
  font-weight: 300;
  font-size: 13px;
}
.card-body .content .content-detail {
  margin-bottom: 0;
}
.title {
  margin: 80px 0px 40px 0px;
}
.title .header {
  font-size: 25px;
  color: black;
  font-weight: 400;
}
</style>

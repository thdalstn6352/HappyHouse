<template>
  <b-row class="mt-4 mb-4">
    <b-col cols="1"> </b-col>
    <b-col cols="10">
      <b-row class="pl-3 title">
        <span class="text-left">어떤 지역의 거래를 보고 싶으신가요?</span>
      </b-row>
      <vue-bootstrap-typeahead
        :data="addresses"
        v-model="addressSearch"
        size="lg"
        :maxMatches="100"
        placeholder="지역 또는 구 이름을 입력하세요."
        @hit="selectedAddress = $event"
        class="h-100 mb-5"
      >
      </vue-bootstrap-typeahead>
      <b-col cols="1"></b-col>
    </b-col>
  </b-row>
</template>

<script>
import VueBootstrapTypeahead from "vue-bootstrap-typeahead";
import _ from "underscore";
import { searchList, searchGugunCode } from "@/api/house.js";
import { mapActions } from "vuex";
const houseStore = "houseStore";

export default {
  components: { VueBootstrapTypeahead },
  data() {
    return {
      addresses: [],
      addressSearch: "",
      selectedAddress: null,
    };
  },
  methods: {
    ...mapActions(houseStore, ["getHouseList"]),
    async search(keyword) {
      const params = {
        keyword: keyword,
      };
      await searchList(
        params,
        ({ data }) => {
          console.log(data);
          let arr = [];
          for (let i = 0; i < data.length; i++) {
            arr.push(data[i].sidoName + " " + data[i].gugunName);
          }
          this.addresses = arr;
          // console.log(arr);
          console.log(this.addresses);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async searchAptList() {
      const params = {
        keyword: this.selectedAddress,
      };
      await searchGugunCode(params, ({ data }) => {
        this.getHouseList(data);
        this.$router.push({
          name: "HouseView",
        });
      }),
        (error) => {
          console.log(error);
        };
    },
  },
  watch: {
    addressSearch: _.debounce(function (addr) {
      this.search(addr);
    }, 500),
    selectedAddress: function () {
      this.searchAptList();
    },
  },
};
</script>
<style scoped>
.title {
  margin: 80px 0px;
}
.title span {
  font-size: 40px;
  color: white;
  font-weight: 300;
}
</style>

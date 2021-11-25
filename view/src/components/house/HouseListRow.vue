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
import { mapActions, mapMutations, mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "HouseListRow",
  data() {
    return {
      price: "",
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    ...mapMutations(houseStore, ["SET_RECENT_LIST"]),
    colorChange(flag) {
      this.isColor = flag;
    },
    selectHouse() {
      const value = JSON.parse(localStorage.getItem("recent-view"));
      this.price = this.numberToKorean(
        parseInt(this.house.거래금액.replace(",", "")) * 10000
      );
      const address = this.house.법정동 + " " + this.house.지번 + "번지";
      const recent_trade = `${this.house.년}년 ${this.house.월}월 ${this.house.일}일`;
      const val = {
        isbn: this.house.일련번호,
        name: this.house.아파트,
        area: this.house.전용면적,
        address: address,
        floor: this.house.층,
        recent_trade: recent_trade,
        price: this.price,
      };

      if (value !== null) {
        let index = value.findIndex((i) => i.isbn == this.house.일련번호);
        if (index === -1) {
          if (value.length === 4) {
            value.shift();
            this.recents.shift();
          }
          value.push(val);
          localStorage.setItem("recent-view", JSON.stringify(value));
          this.recents.push(this.house);
          this.SET_RECENT_LIST(this.recents);
        }
      } else {
        let list = [];
        list.push(val);
        localStorage.setItem("recent-view", JSON.stringify(list));
        this.recents.push(this.house);
        this.SET_RECENT_LIST(this.recents);
      }
      console.log(this.recents);
      this.detailHouse(this.house);
      this.moveDetail();
    },
    moveDetail() {
      this.$router.push({
        name: "HouseDetail",
      });
    },
    numberToKorean(number) {
      var inputNumber = number < 0 ? false : number;
      var unitWords = ["", "만", "억", "조", "경"];
      var splitUnit = 10000;
      var splitCount = unitWords.length;
      var resultArray = [];
      var resultString = "";

      for (let i = 0; i < splitCount; i++) {
        var unitResult =
          (inputNumber % Math.pow(splitUnit, i + 1)) / Math.pow(splitUnit, i);
        unitResult = Math.floor(unitResult);
        if (unitResult > 0) {
          resultArray[i] = unitResult;
        }
      }

      for (let i = 0; i < resultArray.length; i++) {
        if (!resultArray[i]) continue;
        resultString = String(resultArray[i]) + unitWords[i] + resultString;
      }
      return resultString;
    },
  },
  computed: {
    ...mapState(houseStore, ["recents"]),
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

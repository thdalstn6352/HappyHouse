<template>
  <b-container fluid class="bv-example-row body">
    <b-row class="house-info-detail">
      <section class="profile">
        <img
          src="@/assets/happyhouse.png"
          alt="아파트 이름"
          class="profile-image"
        />
        <h1 class="profile-name">{{ house.아파트 }}아파트</h1>
        <p class="profile-location">{{ house.일련번호 }}</p>

        <dl class="profile-detail">
          <div class="profile-detail-item">
            <dt>층</dt>
            <dd>{{ house.층 }}층</dd>
          </div>
          <div class="profile-detail-item">
            <dt>전용면적</dt>
            <dd>{{ this.area }}㎡ ({{ this.size }}평)</dd>
          </div>
          <div class="profile-detail-item">
            <dt>최근거래가</dt>
            <dd>{{ this.price }}원</dd>
          </div>
        </dl>
        <dl class="profile-detail">
          <div class="profile-detail-item">
            <dt>법정동</dt>
            <dd>{{ house.법정동 }}</dd>
          </div>
          <div class="profile-detail-item">
            <dt>도로명</dt>
            <dd>{{ house.도로명 }}</dd>
          </div>
          <div class="profile-detail-item">
            <dt>지번</dt>
            <dd>{{ house.지번 }}번지</dd>
          </div>
        </dl>
        <dl class="profile-detail">
          <div class="profile-detail-item">
            <dt>건축년도</dt>
            <dd>{{ house.건축년도 }}년</dd>
          </div>
          <div class="profile-detail-item">
            <dt>최근 거래일</dt>
            <dd>{{ house.년 }}.{{ house.월 }}.{{ house.일 }}</dd>
          </div>
          <div class="profile-detail-item"></div>
        </dl>
      </section>
    </b-row>
    <b-row class="house-info-detail">
      <section class="option">
        <h1 class="house-title">옵션</h1>
        <dl class="option-detail">
          <div class="option-detail-item">
            <dt><b-icon icon="wifi"></b-icon></dt>
            <dd>와이파이</dd>
          </div>
          <div class="option-detail-item">
            <dt><b-icon icon="person-check"></b-icon></dt>
            <dd>공동 현관문</dd>
          </div>
          <div class="option-detail-item">
            <dt><b-icon icon="tv-fill"></b-icon></dt>
            <dd>TV</dd>
          </div>
          <div class="option-detail-item">
            <dt><b-icon icon="asterisk"></b-icon></dt>
            <dd>에어컨</dd>
          </div>
        </dl>
        <dl class="option-detail">
          <div class="option-detail-item">
            <dt><b-icon icon="door-open-fill"></b-icon></dt>
            <dd>전자도어락</dd>
          </div>
          <div class="option-detail-item">
            <dt><b-icon icon="cart4"></b-icon></dt>
            <dd>신발장</dd>
          </div>
          <div class="option-detail-item">
            <dt></dt>
            <dd></dd>
          </div>
          <div class="option-detail-item">
            <dt></dt>
            <dd></dd>
          </div>
        </dl>
      </section>
    </b-row>
    <b-row>
      <b-col> <h1 class="house-title">위치 및 주변시설</h1></b-col>
    </b-row>
    <b-row>
      <b-col align="center">
        <house-facility-map />
      </b-col>
    </b-row>
    <div class="house-nearby-facility">
      <ul class="tab-menu">
        <li class="tab-menu-item">
          <a href="#">편의시설</a>
        </li>
        <li class="tab-menu-item active">
          <a href="#">안전시설</a>
        </li>
        <li class="tab-menu-item">
          <a href="#">학군정보</a>
        </li>
      </ul>
    </div>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import HouseFacilityMap from "@/components/house/HouseFacilityMap.vue";

const houseStore = "houseStore";

export default {
  name: "HouseDetail",
  components: {
    HouseFacilityMap,
  },
  data() {
    return {
      size: 0,
      price: "",
      area: 0,
    };
  },
  created() {
    this.size = Math.floor(this.house.전용면적 * 0.3082);
    this.price = this.numberToKorean(
      parseInt(this.house.거래금액.replace(",", "")) * 10000
    );
    this.area = this.house.전용면적.toFixed(2);
  },
  computed: {
    ...mapState(houseStore, ["house"]),
  },

  methods: {
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
};
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
}

.body {
  font-family: "Roboto", sans-serif;
  letter-spacing: -0.02em;
}

a {
  font-size: 18px;
  line-height: 20px;
  color: #8492a6;
  text-decoration: none;
}

.house-title {
  font-size: 30px;
  font-weight: 300;
}
.house-title {
  margin-bottom: 30px;
}
.house-nearby-facility {
  width: 600px;
  margin-left: auto;
  margin-right: auto;
}

.tab-menu {
  padding-left: 0;
  border-bottom: 1px solid #e5eaef;
  list-style-type: none;
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  justify-content: center;
  align-items: center;
}

.tab-menu::after {
  content: "";
  display: block;
  clear: both;
}

.tab-menu-item {
  margin-right: 16px;
}

.tab-menu-item.active {
  border-bottom: 2px solid #2860e1;
}

.tab-menu-item.active a {
  font-weight: 500;
  color: #2860e1;
}

.tab-menu-item:last-child {
  margin-right: 0;
}

.tab-menu-item a {
  display: block;
  padding: 16px 20px;
}

.profile-name {
  font-size: 30px;
  font-weight: 600;
  line-height: 1.3333333333;
}

.profile-location {
  font-size: 15px;
  line-height: 1.4285714286;
  color: #8492a6;
}

.profile-detail dt {
  font-size: 12px;
  font-weight: 600;
  line-height: 1.6666666667;
  color: #8492a6;
}

.profile-detail dd {
  font-size: 25px;
  font-weight: 300;
  line-height: 1.25;
  color: #0066ff;
}

.house-info-detail {
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  justify-content: center;
  width: 100%;
}

.profile {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 70%;
  padding: 32px 40px;
  border-radius: 16px;
  text-align: center;
  background-color: #fff;
}

.profile-image {
  display: block;
  width: 100px;
  height: 100px;
  border-radius: 50%;
  margin-bottom: 16px;
}

.profile-name {
  margin-bottom: 4px;
}

.profile-location {
  margin-bottom: 32px;
}

.profile-detail {
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  margin-bottom: 50px;
  padding-bottom: 20px;
  border-bottom: solid 1px #dbdee4;
}
.profile-detail-item {
  width: 300px;
}
.profile-detail dt {
  margin-bottom: 4px;
}

.option {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 70%;
  padding: 32px 40px;
  border-radius: 16px;
  text-align: center;
  background-color: #fff;
}

.option-detail {
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  margin-bottom: 50px;
  padding-bottom: 20px;
  border-bottom: solid 1px #dbdee4;
}
.option-detail dt {
  font-size: 30px;
  font-weight: 600;
  line-height: 1.6666666667;
  color: #8492a6;
}

.option-detail dd {
  font-size: 13px;
  font-weight: 300;
  line-height: 1.25;
}
</style>

<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row list">
    <house-list-row
      v-for="(house, index) in houses"
      :key="index"
      :house="house"
    />
  </b-container>
  <b-container v-else class="bv-example-row">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import HouseListRow from "@/components/house/HouseListRow.vue";
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListRow,
  },
  data() {
    return {
      fields: ["아파트명", "거래금액(만원)", "면적(m^2)", "층", "건축년도"],
    };
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
};
</script>

<style scoped>
.list {
  overflow: scroll;
  max-height: 65vh;
}
</style>

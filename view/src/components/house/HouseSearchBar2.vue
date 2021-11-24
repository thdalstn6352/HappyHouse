<template>
  <b-row class="mt-4 mb-4">
    <b-col cols="3"> </b-col>
    <b-col cols="6">
      <vue-bootstrap-typeahead
        :data="addresses"
        v-model="addressSearch"
        size="lg"
        :serializer="(s) => s.text"
        placeholder="Type an address..."
        @hit="selectedAddress = $event"
      />
    </b-col>
    <b-col cols="3"> </b-col>
  </b-row>
</template>

<script>
import VueBootstrapTypeahead from "vue-bootstrap-typeahead";
import _ from "underscore";
import { searchList } from "@/api/house.js";

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
    search(keyword) {
      const params = {
        keyword: keyword,
        // DEAL_YMD: "202110",
      };
      searchList(
        params,
        ({ data }) => {
          console.log(data);
          //   this.addresses.push(data[0].sidoName); 여기서 에러남
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
  watch: {
    addressSearch: _.debounce(function (addr) {
      //   console.log(addr);
      this.search(addr);
    }, 500),
  },
};
</script>
<style></style>

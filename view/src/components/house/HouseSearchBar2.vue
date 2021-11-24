<template>
  <b-row class="mt-4 mb-4">
    <b-col cols="3"> </b-col>
    <b-col cols="6">
      <vue-bootstrap-typeahead
        :data="addresses"
        v-model="addressSearch"
        size="lg"
        placeholder="Type an address..."
      >
        <template slot="suggestion" slot-scope="{ data, htmlText }">
          <span v-html="htmlText"></span>&nbsp;<small>{{ data.code }}</small>
        </template>
      </vue-bootstrap-typeahead>
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
    async search(keyword) {
      const params = {
        keyword: keyword,
        // DEAL_YMD: "202110",
      };
      await searchList(
        params,
        ({ data }) => {
          console.log(data);
          // this.addresses.push(data);
          this.addresses = ["Hihi", "hello"];
          console.log(this.addresses);
        },
        // eslint-disable-next-line prettier/prettier
        (error) => {
          console.log(error);
        }
      );
    },
  },
  watch: {
    addressSearch: _.debounce(function (addr) {
      this.search(addr);
    }, 500),
  },
};
</script>
<style></style>

<template>
  <b-tr @click="moveView()">
    <b-td class="text-left">{{ subject }}</b-td>
    <b-td class="text-right">{{ userid }}</b-td>
    <b-td class="text-right">{{ regtime }}</b-td>
    <b-td>{{ hit }}</b-td>
  </b-tr>
</template>

<script>
// import moment from "moment";

export default {
  name: "BoardListRow",
  props: {
    articleno: Number,
    userid: String,
    subject: String,
    hit: Number,
    regtime: String,
  },
  methods: {
    moveView() {
      this.$router.push({
        name: "BoardView",
        params: { articleno: this.articleno },
      });
    },
  },
  computed: {
    // changeDateFormat() {
    //   return moment(new Date(this.regtime)).format("YY.MM.DD hh:mm:ss");
    // },
  },
};
</script>

<style></style>

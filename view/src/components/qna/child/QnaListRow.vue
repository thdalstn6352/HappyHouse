<template>
  <b-tr @click="moveView()">
    <b-td class="text-left">{{ subject }}</b-td>
    <b-td>{{ userid }}</b-td>
    <b-td>{{ regtime }}</b-td>
    <b-td v-if="answered === true">
      <div class="answered-true">답변완료</div>
    </b-td>
    <b-td v-else>
      <div class="answered-false">답변대기</div>
    </b-td>
  </b-tr>
</template>

<script>
export default {
  name: "QnaListRow",
  props: {
    qnano: Number,
    userid: String,
    subject: String,
    regtime: String,
    answered: Boolean,
  },
  computed: {
    // ...mapState(memberStore, ["userInfo"]),
    // changeDateFormat() {
    //   return moment(new Date(this.regtime)).format("YY.MM.DD hh:mm:ss");
    // },
  },
  methods: {
    moveView() {
      this.$router.push({
        name: "QnaView",
        params: { qnano: this.qnano },
      });
    },
  },
};
</script>

<style scoped>
.answered-true {
  display: inline-block;
  font-size: 18px;
  border-style: solid;
  border-color: blue;
  padding: 3px;
}
.answered-false {
  display: inline-block;
  font-size: 18px;
  border-style: solid;
  border-color: red;
  padding: 3px;
}
</style>

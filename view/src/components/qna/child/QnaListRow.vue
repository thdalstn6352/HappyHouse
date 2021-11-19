<template>
  <b-tr>
    <b-td>{{ qnano }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'QnaView', params: { qnano: qnano } }">{{
        subject
      }}</router-link>
    </b-th>
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
// import moment from "moment";

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
    // changeDateFormat() {
    //   return moment(new Date(this.regtime)).format("YY.MM.DD hh:mm:ss");
    // },
  },
};
</script>

<style>
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

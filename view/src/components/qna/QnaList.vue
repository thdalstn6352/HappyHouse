<template>
  <b-container class="bv-example-row mt-3">
    <h3 class="underline-purple">
      <b-icon icon="question-circle"></b-icon> QnA 목록
    </h3>

    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="qnas.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="light">
            <b-tr>
              <b-th class="title">제목</b-th>
              <b-th class="writer text-right">작성자</b-th>
              <b-th class="regtime text-right">작성일</b-th>
              <b-th class="answered">답변여부</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <qna-list-row
              v-for="(qna, index) in qnas"
              :key="index"
              v-bind="qna"
              style="cursor: pointer"
            />
          </tbody>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>

    <b-pagination
      v-model="currentPage"
      :total-rows="totalCount"
      align="center"
      :per-page="this.perPage"
      @page-click="pageClick"
    >
    </b-pagination>
  </b-container>
</template>

<script>
// import http from "@/util/http-common";
import QnaListRow from "@/components/qna/child/QnaListRow";
import { mapActions, mapGetters } from "vuex";

const qnaStore = "qnaStore";

export default {
  name: "QnaList",
  data() {
    return {
      currentPage: 1,
      perPage: 10,
    };
  },
  components: {
    QnaListRow,
  },
  computed: {
    ...mapGetters(qnaStore, [
      "qnas",
      // "pageNav",
      "totalCount",
      "getCurrentPage",
    ]),
  },
  created() {
    this.getQnas({ pg: this.currentPage, spp: 10 });
  },
  methods: {
    ...mapActions(qnaStore, ["getQnas"]),
    pageClick: function (button, page) {
      this.currentPage = page;
      this.getQnas({ pg: this.currentPage, spp: 10 });
    },
    moveWrite() {
      this.$router.push({ name: "QnaWrite" });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
.underline-purple {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(183, 9, 226, 0) 70%,
    rgba(230, 11, 193, 0.3) 30%
  );
}
.title {
  width: 60%;
}
.writer,
.regtime,
.answered {
  width: 10%;
}
</style>

<template>
  <b-container class="bv-example-row mt-3">
    <h3 class="underline-hotpink">공지사항</h3>
    <b-row v-if="userInfo !== null && userInfo.userid === `admin`" class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="articles.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="light">
            <b-tr>
              <b-th class="title">제목</b-th>
              <b-th class="writer">작성자</b-th>
              <b-th class="regtime">작성일</b-th>
              <b-th class="hit">조회수</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <board-list-row
              v-for="(article, index) in articles"
              :key="index"
              v-bind="article"
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
      :per-page="perPage"
      @page-click="pageClick"
    >
    </b-pagination>
  </b-container>
</template>

<script>
// import http from "@/util/http-common";
import BoardListRow from "@/components/board/child/BoardListRow";
import { mapActions, mapGetters, mapState } from "vuex";

const boardStore = "boardStore";
const memberStore = "memberStore";

export default {
  name: "BoardList",
  data() {
    return {
      currentPage: 1,
      perPage: 10,
    };
  },
  components: {
    BoardListRow,
  },
  created() {
    this.getArticles({ pg: this.currentPage, spp: 10 });
  },

  computed: {
    ...mapGetters(boardStore, [
      "articles",
      // "pageNav",
      "totalCount",
      "getCurrentPage",
    ]),
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(boardStore, ["getArticles"]),
    moveWrite() {
      this.$router.push({ name: "BoardWrite" });
    },
    pageClick: function (button, page) {
      this.currentPage = page;
      this.getArticles({ pg: this.currentPage, spp: 10 });
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
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}

.title {
  width: 70%;
}

.writer,
.regtime,
.hit {
  width: 10%;
}
</style>

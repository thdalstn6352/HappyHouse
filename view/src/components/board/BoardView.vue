<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-left">
        <div class="header">
          <div id="title">
            <h1>{{ article.subject }}</h1>
          </div>

          <b-row>
            <b-col>
              <div class="userinfo">
                <span class="userid">{{ article.userid }}</span>
                <span> · </span>
                <span class="date">{{ this.changeDateFormat }}</span>
              </div>
            </b-col>
            <b-col
              v-if="userInfo !== null && userInfo.userid === 'admin'"
              class="text-right"
            >
              <b-button
                variant="outline-info"
                size="sm"
                @click="moveModifyArticle()"
                class="mr-2"
                >글수정</b-button
              >
              <b-button
                variant="outline-danger"
                size="sm"
                @click="doDeleteArticle()"
                >글삭제</b-button
              >
            </b-col>
          </b-row>
        </div>
        <div class="content">
          <div v-html="message"></div>
        </div>
        <div class="profile">
          <b-row>
            <b-col cols="6">
              <b-row>
                <b-col cols="3" class="pr-0">
                  <img src="@/assets/subway.png" class="image" />
                </b-col>
                <b-col cols="9" class="text-left pl-0 pt-3">
                  <div class="username">
                    {{ writer.username }}
                  </div>
                  <div v-if="writer.userintro" class="userintro">
                    {{ writer.userintro }}
                  </div>
                  <div v-else>안녕하세요. {{ writer.username }}입니다.</div>
                </b-col>
              </b-row>
            </b-col>
            <b-col cols="6" class="text-right mt-5">
              <b-button variant="outline-primary" @click="listArticle"
                >목록</b-button
              >
            </b-col>
          </b-row>
        </div>
      </b-col>
    </b-row>
    <b-row mb-5>
      <b-col cols="4">
        <b-card
          v-if="this.articleno > 1"
          class="card text-left"
          @click="movePreviousBoard()"
          style="cursor: pointer"
        >
          <b-row>
            <b-col cols="2">
              <font-awesome-icon icon="arrow-circle-left" />
            </b-col>
            <b-col>
              <div>
                <div class="prev-next">
                  <span>이전 공지사항</span>
                </div>
                <div class="subject">
                  <!-- <b-row class="subject"> -->
                  <span>{{ this.previousArticle.subject }}</span>
                  <!-- </b-row> -->
                </div>
              </div>
            </b-col>
          </b-row>
        </b-card>
      </b-col>
      <b-col> </b-col>
      <b-col cols="4">
        <b-card
          v-if="this.articles.length > this.articleno"
          class="card"
          @click="moveNextBoard()"
          style="cursor: pointer"
        >
          <b-row>
            <b-col cols="10" class="text-right">
              <div>
                <div class="prev-next">
                  <span>이후 공지사항</span>
                </div>
                <div class="subject">
                  <!-- <b-row class="subject"> -->
                  <span>{{ this.nextArticle.subject }}</span>
                  <!-- </b-row> -->
                </div>
              </div>
            </b-col>
            <b-col cols="2">
              <font-awesome-icon icon="arrow-circle-right" />
            </b-col>
          </b-row>
        </b-card>
      </b-col>
    </b-row>
    <b-row class="mb-3"> </b-row>
  </b-container>
</template>

<script>
import moment from "moment";
// import http from "@/util/http-common";
import { mapActions, mapState, mapGetters, mapMutations } from "vuex";
import { getArticle } from "@/api/board.js";
import { checkId } from "@/api/member.js";

const boardStore = "boardStore";
const memberStore = "memberStore";

export default {
  data() {
    return {
      articleno: "",
      writer: {},
      article: {},
      previousArticle: {},
      nextArticle: {},
    };
  },
  computed: {
    ...mapGetters(boardStore, ["articles"]),
    ...mapState(memberStore, ["userInfo", "check"]),
    // ...mapState(boardStore, ["previousArticle", "nextArticle"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
    changeDateFormat() {
      return moment(new Date(this.article.regtime)).format("YYYY년 MM월 DD일");
    },
  },
  created() {
    this.articleno = this.$route.params.articleno;
    getArticle(
      this.articleno,
      (response) => {
        this.article = response.data;
        checkId(
          this.article.userid,
          (response) => {
            this.writer = response.data;
          },
          (error) => {
            console.log(error);
          }
        );
        if (this.articleno > 1) {
          getArticle(this.articleno - 1, (response) => {
            this.previousArticle = response.data;
          });
        }
        if (this.articleno < this.articles.length) {
          getArticle(this.articleno + 1, (response) => {
            this.nextArticle = response.data;
          });
        }
      },
      (error) => {
        console.log(error);
      }
    );
    // console.log(this.previousArticle);
    // console.log(this.nextArticle);
  },
  methods: {
    ...mapActions(boardStore, ["deleteArticle"]),
    ...mapMutations(boardStore, ["setClearArticle"]),
    listArticle() {
      this.$router.push({ name: "BoardList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "BoardUpdate",
        params: { articleno: this.article.articleno },
      });
    },
    movePreviousBoard() {
      this.$router.push({
        name: "BoardView",
        params: { articleno: this.previousArticle.articleno },
      });
    },
    moveNextBoard() {
      this.$router.push({
        name: "BoardView",
        params: { articleno: this.nextArticle.articleno },
      });
    },
    async doDeleteArticle() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        await this.deleteArticle(this.articleno);
      }
      this.listArticle();
    },
  },
};
</script>

<style scoped>
.image {
  width: 80px;
  height: 80px;
  border-radius: 50%;
}
.svg-inline--fa {
  font-size: 30px;
  color: rgb(32, 201, 151);
}
.content {
  height: 300px;
}
#title {
  margin-bottom: 30px;
}
#title h1 {
  font-weight: 600;
}
.userid {
  font-weight: 600;
}
.date {
  color: grey;
}
.profile,
.header {
  border-bottom: 1px solid rgb(231, 231, 231);
  padding-bottom: 15px;
  margin-bottom: 30px;
}
.userinfo,
.hit {
  display: inline-block;
}
.hit {
  float: right;
}
.content {
  font-size: 30px;
  margin-bottom: 50px;
}

.card {
  border: none;
  background-color: rgb(248, 249, 250);
  box-shadow: rgb(0 0 0 / 6%) 0px 0px 4px 0px;
}
.username {
  font-size: 20px;
  font-weight: 600;
}
.subject {
  overflow: hidden;
  max-height: 30px;
  color: rgb(73, 80, 87);
  font-weight: 700;
}
.prev-next {
  font-size: 10px;
  font-weight: 600;
  color: rgb(73, 80, 87);
}
</style>

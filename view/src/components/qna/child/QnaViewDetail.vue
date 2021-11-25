<template>
  <b-container>
    <b-row class="mb-1">
      <b-col class="text-left">
        <div class="header">
          <div id="title">
            <h1>{{ qna.subject }}</h1>
          </div>

          <b-row>
            <b-col>
              <div class="userinfo">
                <span class="userid">{{ qna.userid }}</span>
                <span> · </span>
                <span class="date">{{ this.changeDateFormat }}</span>
              </div>
            </b-col>
            <b-col
              v-if="
                userInfo.userid === 'admin' || qna.userid === userInfo.userid
              "
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
                @click="deleteArticle()"
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
          @click="movePreviousQna()"
          style="cursor: pointer"
        >
          <b-row>
            <b-col cols="2">
              <font-awesome-icon icon="arrow-circle-left" />
            </b-col>
            <b-col>
              <div>
                <div class="prev-next">
                  <span>이전 질문</span>
                </div>
                <div class="subject">
                  <span>{{ this.previousArticle.subject }}</span>
                </div>
              </div>
            </b-col>
          </b-row>
        </b-card>
      </b-col>
      <b-col> </b-col>
      <b-col cols="4">
        <b-card
          v-if="qnas.length > this.articleno"
          class="card"
          @click="moveNextQna()"
          style="cursor: pointer"
        >
          <b-row>
            <b-col cols="10" class="text-right">
              <div>
                <div class="prev-next">
                  <span>이후 질문</span>
                </div>
                <div class="subject">
                  <span>{{ this.nextArticle.subject }}</span>
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
import { mapActions, mapState, mapGetters } from "vuex";
import { getQna } from "@/api/qna.js";
import { checkId } from "@/api/member.js";

const qnaStore = "qnaStore";
const memberStore = "memberStore";

export default {
  data() {
    return {
      articleno: "",
      writer: {},
      previousArticle: {},
      nextArticle: {},
    };
  },
  props: {
    qna: Object,
  },
  computed: {
    ...mapGetters(qnaStore, ["qnas"]),
    ...mapState(memberStore, ["userInfo", "check"]),
    // ...mapState(boardStore, ["previousArticle", "nextArticle"]),
    message() {
      if (this.qna.content) return this.qna.content.split("\n").join("<br>");
      return "";
    },
    changeDateFormat() {
      return moment(new Date(this.qna.regtime)).format("YYYY년 MM월 DD일");
    },
  },
  created() {
    this.articleno = this.$route.params.qnano;
    checkId(
      this.qna.userid,
      (response) => {
        this.writer = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
    if (this.articleno > 1) {
      getQna(this.articleno - 1, (response) => {
        this.previousArticle = response.data;
      });
    }
    if (this.articleno < this.qnas.length) {
      getQna(this.articleno + 1, (response) => {
        this.nextArticle = response.data;
      });
    }
  },
  methods: {
    ...mapActions(qnaStore, ["getQna", "deleteQna", "getComments"]),
    ...mapActions(memberStore, ["checkId"]),
    listArticle() {
      this.$router.push({ name: "QnaList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "QnaUpdate",
        params: { qnano: this.qna.qnano },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    async deleteArticle() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        await this.deleteQna(this.qna.qnano);
      }
      this.listArticle();
    },
    movePreviousQna() {
      this.$router.push({
        name: "QnaView",
        params: { qnano: this.previousArticle.qnano },
      });
    },
    moveNextQna() {
      this.$router.push({
        name: "QnaView",
        params: { qnano: this.nextArticle.qnano },
      });
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

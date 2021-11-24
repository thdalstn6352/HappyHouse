<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-orange">
      <b-icon icon="house-fill"></b-icon> House Service
    </h3>
    <b-row class="nav-bar">
      <ul class="tab-menu">
        <b-nav-item href="#"
          ><router-link :to="{ name: 'RecentView' }" class="link"
            ><b-icon icon="layers-half" font-scale="1"></b-icon> 최근 본
            거래</router-link
          ></b-nav-item
        >
        <b-nav-item href="#"
          ><router-link :to="{ name: 'WatchList' }" class="link"
            ><b-icon icon="heart-fill" font-scale="1"></b-icon> 찜
            목록</router-link
          ></b-nav-item
        >
      </ul>
    </b-row>
    <router-view />
  </b-container>
</template>

<script>
export default {};
</script>

<style>
* {
  box-sizing: border-box;
  margin: 0;
}

body {
  font-family: "Roboto", sans-serif;
  letter-spacing: -0.02em;
}

a {
  font-size: 18px;
  line-height: 20px;
  color: #8492a6;
  text-decoration: none;
}

/* ▼ WHERE YOUR CODE BEGINS */
.house-info-detail {
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  justify-content: center;
  width: 100%;
}
b-row .nav-bar {
  width: 300px;
  margin-left: auto;
  margin-right: auto;
}
.tab-menu {
  padding-left: 0;
  border-bottom: 1px solid #e5eaef;
  list-style-type: none;
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  justify-content: center;
  align-items: center;
}

.tab-menu::after {
  content: "";
  display: block;
  clear: both;
}

.tab-menu-item {
  float: left;
  margin-right: 16px;
}

.tab-menu-item.active {
  border-bottom: 2px solid #2860e1;
}

.tab-menu-item.active a {
  font-weight: 500;
  color: #2860e1;
}

.tab-menu-item:last-child {
  margin-right: 0;
}

.tab-menu-item a {
  display: block;
  padding: 16px 20px;
}
</style>

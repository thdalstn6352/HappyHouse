<template>
  <div id="app">
    <navi-bar />
    <router-view />
  </div>
</template>

<script>
import NaviBar from "./components/layout/NaviBar.vue";
// import HomeNaviBar from "./components/layout/HomeNaviBar.vue";

export default {
  name: "App",
  components: {
    NaviBar,
    // HomeNaviBar,
  },
};
</script>

<style>
a:hover {
  text-decoration: none;
  font-weight: bold;
}

a .router-link-active {
  color: rgb(50, 108, 249);
}
</style>

<template>
  <div id="app">
    <AppHeader v-if="!$route.meta.hideHeader"></AppHeader> <!-- Header를 AppHeader로 변경 -->
    <router-view/>
  </div>
</template>

<script>

import AppHeader from '@/components/Header.vue'
export default {
  name: 'App',
  components: {
    AppHeader
    // eslint-disable-next-line vue/no-unused-components
  }
}
</script>

<style>

#app {
  font-family: "Pretendard Variable", Pretendard, Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
</style>
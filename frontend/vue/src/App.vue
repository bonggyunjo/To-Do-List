<template>
  <div id="app">
    <AppHeader v-if="!$route.meta.hideHeader"></AppHeader>
    <router-view/>
    <Footer v-if="!$route.meta.hideFooter"></Footer>
  </div>
</template>

<script>

import AppHeader from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'
export default {
  name: 'App',
  components: {
    AppHeader,
    Footer
    // eslint-disable-next-line vue/no-unused-components
  },
  created() {
    const token = localStorage.getItem('token');
    if (token) {
      this.$store.commit('SET_LOGIN', true);
    }
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
<template>
  <div id="app">
    <AppHeader v-if="!$route.meta.hideHeader"></AppHeader> <!-- Header를 AppHeader로 변경 -->
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
    AppHeader
    Footer
    // eslint-disable-next-line vue/no-unused-components
  },
  created() {
    const token = localStorage.getItem('token');
    if (token) {
      // 토큰이 존재하면 로그인 상태로 설정
      this.$store.commit('SET_LOGIN', true);
      // userId와 nickname도 복원할 수 있다면 복원
      // this.$store.commit('setUserId', userIdFromToken);
      // this.$store.commit('setUserNickname', nicknameFromToken);
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
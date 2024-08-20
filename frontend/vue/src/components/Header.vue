<template>
  <header class="app-header">
    <div class="logo">
      <h1>할 일 목록</h1>
    </div>
    <p class="slogan">당신의 일정을 관리하세요</p>
    <nav class="navigation">
      <ul>
        <li><router-link to="/">홈</router-link></li>
        <li><router-link to="/add-task">작업 추가</router-link></li>
        <li><router-link to="/task-list">작업 목록</router-link></li>
        <li><router-link to="/task-list">자유 게시판</router-link></li>
        <li><router-link to="/settings">설정</router-link></li>
      </ul>
    </nav>

    <template v-if="isUserLogin">
      <button @click="logout" class="signup-button"><span style="font-size: 15px;">로그아웃</span></button>
    </template>

    <template v-else>
    <div class="auth-buttons">
      <router-link to="/signup" class="signup-button"><span style="font-size: 15px;">회원가입</span></router-link>
      <router-link to="/login" class="login-button"><span style="font-size: 15px;">로그인</span></router-link>
    </div>
    </template>
    <div class="icon-animation">
      <img src="@/assets/header/header_3d_icon_image.png" alt="아이콘" />
    </div>
  </header>
</template>

<script>
export default {
  name: 'AppHeader',
  computed: {
    // 로그인 유무 확인
    isUserLogin() {
      return this.$store.getters.isLogin;
    }
  },
  methods: {
    logout() {
      // 로그아웃 로직
      this.$store.dispatch('logout'); // Vuex로 로그아웃 처리
      localStorage.removeItem('token'); // 토큰 삭제

      // 현재 경로와 이동할 경로를 비교하여 중복 탐색 방지
      if (this.$route.path !== '/') {
        this.$router.push('/'); // 홈으로 리다이렉션
      }
    }
  }
}
</script>

<style scoped>
.app-header {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  background-color: #f8f9fa;
  border-bottom: 1px solid #e7e7e7;
}

.logo {
  display: flex;
  align-items: center;
}

h1 {
  font-size: 24px;
  color: #333;
}

.slogan {
  font-size: 16px;
  color: #666;
  margin: 5px 0 15px;
}

.navigation ul {
  display: flex;
  list-style: none;
  padding: 0;
  margin: 10px 0;
}

.navigation li {
  margin: 0 15px;
}

.navigation a {
  text-decoration: none;
  color: #007bff;
}

.auth-buttons {
  display: flex; /* Flexbox로 레이아웃 조정 */
}

.signup-button, .login-button {
  margin: 20px 13px;
  padding: 10px 15px;
  color: #fff;
  background-color: #007bff;
  border: none;
  border-radius: 15px;
  text-decoration: none;
  position: relative;
  top: 10px;
}

.icon-animation {
  position: absolute; /* 위치 조정을 위해 절대 위치 설정 */
  right: 10%; /* 오른쪽 중앙에 위치 */
  transform: translateX(50%); /* 중앙 정렬 */
  animation: float 2.5s ease-in-out infinite; /* 애니메이션 설정 */
}

@keyframes float {
  0%, 100% {
    transform: translateY(0); /* 원래 위치 */
  }
  50% {
    transform: translateY(20px); /* 아래로 이동 */
  }
}
.icon-animation img {
  width: 280px; /* 이미지 크기 조정 */
  height: 280px; /* 이미지 크기 조정 */
}

</style>

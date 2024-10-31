<template>
  <header class="app-header">
    <div class="logo">
      <h1>할 일 목록</h1>
    </div>
    <p class="slogan">일정을 관리하여 보세요.</p>


    <template v-if="isUserLogin">
      <nav class="navigation">
        <ul>
          <li><router-link to="/">홈</router-link></li>
          <li><router-link to="/add-task">작업 추가</router-link></li>
          <li><router-link to="/task-list">작업 목록</router-link></li>
          <li><router-link to="/board">자유 게시판</router-link></li>
          <li><router-link to="/mypage">설정</router-link></li>
        </ul>
      </nav>
      <button @click="logout" class="logout-button"><span style="font-size: 15px;">로그아웃</span></button>
    </template>

    <template v-else>
    <div class="auth-buttons">
      <router-link to="/signup" class="signup-button"><span style="font-size: 14px;">회원가입</span></router-link>
      <router-link to="/login" class="login-button"><span style="font-size: 14px;">로그인</span></router-link>
    </div>
    </template>
    <div class="icon-animation">
      <img src="@/assets/header/header_3d_icon_image.png" alt="아이콘" />
    </div>

    <loading-spinner v-if="loading" />
  </header>
</template>

<script>
import LoadingSpinner from './LoadingSpinner.vue';
export default {
  name: 'AppHeader',
  components: {
    LoadingSpinner,
  },
  data() {
    return {
      loading: false,
    };
  },
  computed: {
    // 로그인 유무 확인
    isUserLogin() {
      return this.$store.getters.isLogin;
    }
  },
  methods: {
    logout() {
      this.loading = true;
      setTimeout(() => {
        this.$store.dispatch('logout'); // Vuex로 로그아웃 처리
        localStorage.removeItem('token'); // 토큰 삭제

        if (this.$route.path !== '/') {
          this.$router.push('/');
        }
        this.loading = false; // 로딩 종료
      }, 1000);
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
  width: 105%;
  position: relative;
  left:-3%;
  top:-20px;
  height: 220px;

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
  position: relative;
  top:10px;
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
  display: flex;
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
.logout-button{
  margin: 20px 13px;
  padding: 10px 15px;
  color: #fff;
  background-color: #007bff;
  border: none;
  border-radius: 15px;
  text-decoration: none;
  position: relative;
  top: -5px;
}
.icon-animation {
  position: absolute;
  right: 10%;
  transform: translateX(50%);
  animation: float 2.5s ease-in-out infinite;
}

@keyframes float {
  0%, 100% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(20px);
  }
}
.icon-animation img {
  width: 280px;
  height: 280px;
}
</style>

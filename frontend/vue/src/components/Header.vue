<template>
  <header class="app-header">
    <div class="logo">
      <h1>오늘의 일기</h1>
    </div>
    <p class="slogan">당신의 하루를 기록하세요</p>
    <nav class="navigation">
      <ul>
        <li><router-link to="/">홈</router-link></li>
        <li><router-link to="/write">일기 작성</router-link></li>
        <li><router-link to="/my-diary">내 일기</router-link></li>
        <li><router-link to="/settings">설정</router-link></li>
      </ul>
    </nav>
    <div class="search-container">
      <input type="text" placeholder="일기 검색..." />
    </div>
    <div class="auth-buttons">
      <router-link to="/signup" class="signup-button">회원가입</router-link>
      <router-link to="/login" class="login-button">로그인</router-link>
    </div>
    <div class="social-icons">
      <a href="https://facebook.com" target="_blank">Facebook</a>
      <a href="https://twitter.com" target="_blank">Twitter</a>
      <a href="https://instagram.com" target="_blank">Instagram</a>
    </div>
  </header>
</template>

<script>
export default {
  name: 'AppHeader',

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

.logo img {
  width: 50px; /* 로고 크기 조정 */
  margin-right: 10px;
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

.search-container {
  margin: 10px 0;
}

.search-container input {
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.auth-buttons {
  margin: 10px 0;
}

.signup-button, .login-button {
  margin: 0 5px;
  padding: 10px 15px;
  color: #fff;
  background-color: #007bff;
  border: none;
  border-radius: 4px;
  text-decoration: none;
}

.social-icons {
  margin: 10px 0;
}

.social-icons a {
  margin: 0 10px;
  text-decoration: none;
  color: #007bff;
}
</style>
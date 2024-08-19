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
    <!--
    <div class="filter-container">
      <select>
        <option value="all">모든 작업</option>
        <option value="completed">완료된 작업</option>
        <option value="pending">진행 중인 작업</option>
      </select>
    </div>
    -->
    <div class="auth-buttons">
      <router-link to="/signup" class="signup-button"><span style="font-size: 15px;">회원가입</span></router-link>
      <router-link to="/login" class="login-button"><span style="font-size: 15  px;">로그인</span></router-link>
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

.filter-container {
  margin: 10px 0;
}

.filter-container select {
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
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
</style>

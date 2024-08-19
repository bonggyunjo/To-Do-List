<template>
  <footer class="app-footer">
    <div class="footer-content">
      <p class="copyright">&copy; 2024 Today’s Diary. All rights reserved.</p>
      <nav class="footer-nav">
        <router-link to="/">홈</router-link>
        <router-link to="/about">소개</router-link>
        <router-link to="/contact">연락처</router-link>
      </nav>
      <div class="social-icons">
        <a href="https://facebook.com" target="_blank" aria-label="Facebook">
          <i class="fab fa-facebook-f"></i>
        </a>
        <a href="https://twitter.com" target="_blank" aria-label="Twitter">
          <i class="fab fa-twitter"></i>
        </a>
        <a href="https://instagram.com" target="_blank" aria-label="Instagram">
          <i class="fab fa-instagram"></i>
        </a>
      </div>
    </div>
  </footer>
</template>

<script>
/* eslint-disable vue/multi-word-component-names */
export default {
  name: 'Footer',
}
</script>

<style scoped>
.app-footer {
  background-color: #ffffff;
  padding: 30px 20px;
  text-align: center;
  border-top: 2px solid #e7e7e7;
  box-shadow: 0 -2px 10px rgba(0, 0, 0, 0.1);
}

.footer-content {
  max-width: 1200px;
  margin: 0 auto;
}

.copyright {
  font-size: 14px;
  color: #333;
  margin-bottom: 15px;
}

.footer-nav {
  margin: 10px 0;
}

.footer-nav a {
  margin: 0 25px;
  text-decoration: none;
  color: #007bff;
  transition: color 0.3s ease;
}

.footer-nav a:hover {
  color: #0056b3;
}

.social-icons {
  margin-top: 10px;
}

.social-icons a {
  margin: 0 20px;
  text-decoration: none;
  color: #007bff;
  font-weight: bold;
  transition: color 0.3s ease;
  font-size: 25px;
  position: relative;
  left:-5px;
  top:5px;
}

.social-icons a:hover {
  color: #0056b3;
}
</style>

<template>
  <div class="login-container">
    <div class="login-box">
      <h4 style="color: black;">로그인</h4>
      <form @submit.prevent="login">
        <div class="input-group">
          <input type="text"  style="color: black;position: relative;top:7.49px;" class="login-text" id="username" v-model="userId" placeholder="아이디" />
        </div>
        <div class="input-group">
          <input type="password" style="position: relative;top:-8.516px;" class="password-text" id="password" v-model="password" placeholder="비밀번호" />
        </div>
        <button type="submit" id="login-btn" class="btn btn-outline-primary">로그인</button>
      </form>
      <h6 class="link-text">
        <router-link to="/" style="font-weight: bolder; color: #0056b3; top:10px; position: relative;">비밀번호를 잊으셨나요?</router-link>
        <br><br><br>
        <span style="font-size: 14.5px; font-weight: bolder; color: #333333;">계정이 없으신가요?</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<router-link to="/signup" style="font-weight: bolder; font-size: 15px;">가입하기</router-link>
      </h6>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'UserLogin',
  data() {
    return {
      userId: '',
      password: ''
    };
  },
  methods: {
    async login() {
      // 로그인 로직
      if (!this.userId) {
        alert("아이디를 입력하세요.");
        return;
      }
      if (!this.password) {
        alert("비밀번호를 입력하세요.");
        return;
      }

      const userData = {
        userId: this.userId,
        password: this.password,

      };

      try {
        const res = await axios.post('http://localhost:8081/login', userData);

        console.log('API 응답:', res);
        console.log('응답 데이터:', res.data);

        if (res.data && res.data.token) {
          alert('로그인에 성공하였습니다.');
          localStorage.setItem('token', res.data.token);
          axios.defaults.headers.common['Authorization'] = `Bearer ${res.data.token}`;
          this.$store.commit('setUserId', res.data.userId);
          this.$store.commit('setUserNickname', res.data.nickname);
          this.$router.push('/');
        } else {
          alert('토큰이 응답에 없습니다. 로그인 실패.');
        }
      } catch (error) {
        alert('로그인에 실패하였습니다. 아이디와 비밀번호를 확인해 주세요.');
        console.error('Error data', error);
      }


    }
  }
};
</script>

<style scoped>

.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.login-box {
  width: 400px;
  padding: 40px;
  background: white;
  border-radius: 10px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  text-align: center;
}

h4 {
  margin-bottom: 20px;
  color: #333;
  font-family: 'Arial', sans-serif;
}

.input-group {
  margin-bottom: 15px;
  text-align: left;
}

.input-group input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-sizing: border-box;
  transition: border-color 0.3s;
  border-radius: 2px;
}

.input-group input:focus {
  border-color: #007bff;
  outline: none;
}

.btn {
  width: 100%;
  height: 50px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.btn:hover {
  background-color: #0056b3;
}

.link-text {
  margin-top: 20px;
  font-size: 14px;
  position: relative;
  top:20px;

}

.link-text a {
  text-decoration: none;
  color: #007bff;
}

.link-text a:hover {
  text-decoration: underline;
}
.login-text, .password-text{
  font-size: 13.5px;
}

</style>

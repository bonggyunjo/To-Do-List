<template>
  <div class="login-container">
    <div class="login-box">
      <h2>로그인</h2>

        <div class="input-group">
          <label for="username">이메일</label>
          <input type="text" id="username" v-model="userId" placeholder="email"/>
        </div>
        <div class="input-group">
          <label for="password">비밀번호</label>
          <input type="password" id="password" v-model="password" placeholder="password" />
        </div>
        <button type="submit" class="login-button" @click="login">로그인</button>
      <h5 style="position: relative; top:30px; font-weight: normal"><router-link to="/" style="text-decoration: none; color: #333333;">홈으로</router-link> | <router-link to="/signup" style="text-decoration: none; color: #333333;">회원가입</router-link></h5>
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
  mounted() {
    // $store가 제대로 정의되었는지 확인
    console.log(this.$store);
  },
  methods: {
    async login(event) {
      event.preventDefault();
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
        console.log(res.data); // 응답 데이터 확인
        alert('로그인에 성공하였습니다.');
        this.$store.commit('setUserId', res.data.userId);
        this.$router.push('/');
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
  height: 80vh;
}

.login-box {
  width: 400px;
  height: 330px;
  padding: 40px;
  background: white;
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  text-align: center;
}

h2 {
  margin-bottom: 20px;
  color: #333;
}

.input-group {
  margin-bottom: 15px;
  text-align: left;
}

.input-group label {
  display: block;
  margin-bottom: 5px;
  color: #555;
  position: relative;
  left:40px;
  font-size: 13px;
}

.input-group input {
  width: 80%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  box-sizing: border-box;
  position: relative;
  left:40px;
}

.login-button {
  width: 75%;
  height: 50px;
  padding: 10px;
  background-color: #007BFF;
  border: none;
  border-radius: 5px;
  color: white;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s;
  position: relative;
  top:30px;
}

.login-button:hover {
  background-color: #0056b3;
}
</style>

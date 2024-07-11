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

    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'UserLogin',  // 여기에서 컴포넌트 이름을 변경합니다.
  data() {
    return {
      userId: '',
      password: ''
    };
  },
  methods: {

    async login() {

      // 여기에 로그인 로직을 추가하세요.
      if(!this.userId){
        alert("아이디를 입력하세요.");
        return;
      }
      if(!this.password){
        alert("비밀번호를 입력하세요.");
        return;
      }

      try {
        const response = await axios.post('http://localhost:8081/login', {
          userId: this.userId,
          password: this.password
        });
        alert("로그인에 성공하였습니다.");
        console.log('로그인 성공:', response.data);
        // 로그인 성공 후 처리 로직 추가
      } catch (error) {
        console.error('로그인 실패:', error);
        if (error.response && error.response.status === 401) {
          alert('잘못된 사용자 이름 또는 비밀번호입니다.');
        } else {
          alert('로그인 중 오류가 발생했습니다.');
        }
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
  width: 80%;
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

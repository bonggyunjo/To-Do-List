<template>
  <div class="sign-up">
    <h1>회원가입</h1>
    <form @submit.prevent="submitForm">
      <section>
      <div class="form-group">
        <label for="userId">아이디</label>
        <input type="text" id="userId" v-model="userId"   @input="checkId" >
      </div>
      <div><span id="checkId" style="font-size: 13px; position: relative; left:-70px; top:-10px;"  @input="checkId"></span></div>
      </section>

      <section>
      <div class="form-group">
        <label for="password">비밀번호</label>
        <input type="password" id="password" v-model="password" >
      </div>
      <section :style="{ color: inputStarted ? (isValid ? 'green' : 'red') : 'dimgray' }" id="pw-length" class="form-text-nickname form-text">
        <span style="font-size: 11.5px; position: relative; top:-10px;">대소문자, 숫자, 특수문자를 포함한 8자 이상을 입력하세요.</span>
      </section>
      </section>

      <div class="form-group">
        <label for="nickname">닉네임</label>
        <input type="text" id="nickname" v-model="nickname" >
      </div>
      <span style="font-size: 11.5px; position: relative; top:-10px; left:-59px;">닉네임은 4글자 이상 입력해 주세요.</span>
      <div class="form-group">
        <label for="intro">대표 한마디</label>
        <input type="text" id="intro" v-model="intro" >
      </div>
      <button type="submit" @click="signup" class="submit-btn">가입하기</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      userId: '',
      nickname: '',
      password: '',
      intro: '',
      isUserIdValid: false
    };
  },

  methods: {
    checkId() {
      const validateEmail = (email) => {
        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        return emailPattern.test(email);
      };

      // ID(email) 형식 확인
      const email = this.userId; // Vue 데이터 속성 사용
      const emailMessage = document.querySelector("#checkId");

      if (validateEmail(email)) {
        emailMessage.textContent = '올바른 이메일 형식입니다.';
        emailMessage.style.color = 'darkgreen';
        document.getElementById("emailInput").classList.remove("is-invalid");
        document.getElementById("emailInput").classList.add("is-valid");
        const signUpButton = document.querySelector("#signUpButton");
        signUpButton.disabled = false;
        this.isUserIdValid=true;
      } else {
        emailMessage.textContent = '일치하지 않은 형식입니다.';
        emailMessage.style.color = 'red';
        document.getElementById("emailInput").classList.remove("is-valid");
        document.getElementById("emailInput").classList.add("is-invalid");
        const signUpButton = document.querySelector("#signUpButton");
        signUpButton.disabled = true;
        this.isUserIdValid=false;
      }
    },
    async submitForm() {
      if (!this.userId.trim() || !this.password.trim() || !this.nickname.trim() || !this.intro.trim()) {
        alert('빈칸을 입력해주세요.');
        return;
      }

      const userData = {
        userId: this.userId,
        password: this.password,
        nickname: this.nickname,
        intro: this.intro
      };

      try {
        const response = await axios.post('http://localhost:8081/signup', userData);
        console.log("성공", response.data);
      } catch (error) {
        console.error("실패", error);
      }
    }
  }
};
</script>

<style scoped>
.sign-up {
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  background-color: #fff;
  position: relative;
  top:130px;
}

.sign-up h1 {
  text-align: center;
  margin-bottom: 20px;
  font-size: 24px;
  color: #333;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: inline-block;
  width: 100px; /* 원하는 크기로 설정 */
  margin-bottom: 5px;
  font-weight: bold;
  color: #555;
  text-align: left;
  position: relative;
  left:-98px;
  font-size: 14px;
}

.form-group input {
  width: calc(100% - 110px); /* label의 width와 margin-right를 제외한 나머지 */
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
  margin-left: 10px; /* label과 input 사이 간격 */
}

.submit-btn {
  width: 70%;
  padding: 10px;
  background-color: #007BFF;
  color: #fff;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
  margin-top: 20px;
  height: 50px;
}

.submit-btn:hover {
  background-color: #0056b3;
}
</style>

<template>
  <div class="sign-up">
    <h1>Sign Up</h1>
    <form @submit.prevent="submitForm">
      <div>
        <label for="userId">userId:</label>
        <input type="text" id="userId" v-model="userId" required>
      </div>
      <div>
        <label for="nickname">nickname:</label>
        <input type="text" id="nickname" v-model="nickname" required>
      </div>
      <div>
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" required>
      </div>
      <div>
        <label for="intro">intro:</label>
        <input type="text" id="intro" v-model="intro" required>
      </div>
      <button type="submit" @click="signup">Sign Up</button>
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
      intro:''
    };
  },
  methods: {
    submitForm() {
      // 여기서 폼 데이터를 처리합니다.
      console.log('userId:', this.userId);
      console.log('nickname:', this.nickname);
      console.log('Password:', this.password);
      console.log('intro:', this.intro);
    },

    async signup(){
      const userData = {
        userId: this.userId,
        password: this.password,
        nickname: this.nickname,
        intro: this.intro
      };
      axios.post('http://localhost:8081/signup',userData)
          .then(response => {
            console.log("성공",response.data);
          })
          .catch(error => {
            console.error("실패",error);
          });
    }
  }
};
</script>



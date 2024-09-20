<template>
  <main class="content">
    <h6 class="content-title">비밀번호 변경</h6>
    <span style="font-size: 13px; position: relative; left:-421.5px; top:-25px; color: lightslategrey">새 비밀번호를 입력해 주세요.</span>
    <div class="content-line"></div>

    <div id="repassword">
      <span class="repassword-title">새 비밀번호</span>
      <div class="mb-3 row">
        <div class="col-sm-10">
          <input type="password" class="form-control" id="inputPassword" placeholder="new password.." style="font-size: 12px;" v-model="password" @input="checkPasswords">
        </div>
      </div>
    </div>

    <div id="repassword-check">
      <span class="repassword-check-title">새 비밀번호 확인</span>
      <div class="mb-3 row">
        <div class="col-sm-10">
          <input type="password" class="form-control" id="inputPassword" placeholder="please password check.." style="font-size: 12px;"  v-model="repassword" @input="checkPasswords">
        </div>
        <span class="password-match" v-if="passwordsMatch && repasswordEntered">비밀번호가 일치합니다.</span>
        <span class="password-not-match" v-else-if="repasswordEntered">비밀번호가 일치하지 않습니다.</span>
      </div>

      <section :style="{ color: inputStarted ? (isPasswordValid ? 'green' : 'red') : 'dimgray' }" id="pw-length" class="form-text-nickname form-text">
        <span style="position: relative; top:20px; left:-260px; font-size: 13px; color: #333333;">패스워드는 대문자, 소문자, 숫자, 특수 기호를 포함하여 8자 이상으로 설정해 주세요.</span>
      </section>
    </div>
   <button class="btn btn-secondary" id="cancel" @click="password_cancel">취소</button>
    <button class="btn btn-success" id="delete-user-button" :disabled="!passwordsMatch || !isPasswordValid" @click="changebutton">변경하기</button>
  </main>
</template>

<script>
import axios from "axios";
export default{
 name:'ChangePassword',
  data(){
    return{
      password:'',
      repassword:'',
      passwordMatch:'',
      repasswordEntered: false,
      isPasswordValid: false,
      inputStarted: false,
    }
  },
  methods :{
    async changebutton(){
      if (!this.passwordsMatch || !this.isPasswordValid) {
        alert('비밀번호가 유효해야 변경할 수 있습니다.');
        return;
      }

      try {
        const userId = this.$store.getters.getUserId; // Vuex에서 사용자 ID 가져오기
        const response = await axios.put(`http://localhost:8081/mypage/update`, {
          userId: userId,
          password: this.password
        }, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`
          }
        });
        
        alert('비밀번호가 변경되었습니다.');
        console.log("response",response);
        this.$router.push('/mypage'); // 변경 후 마이 페이지로 리다이렉트
      } catch (error) {
        console.error('비밀번호 변경 실패:', error);
        alert('비밀번호 변경에 실패했습니다. 다시 시도해 주세요.');
      }
    },
    checkPasswords() {
      this.inputStarted = true;
      const validatePassword = (password) => {
        const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
        return passwordPattern.test(password);
      };

      this.isPasswordValid = validatePassword(this.password);
      this.passwordsMatch = this.password === this.repassword;
      this.repasswordEntered = true; // 사용자가 입력을 시작하면 true로 설정
      var matchMessage = document.querySelector('.password-match');
      var notMatchMessage = document.querySelector('.password-not-match');
      var submitButton = document.getElementById("signInButton");

      if (this.passwordsMatch && this.password.length >= 8 && this.password.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W]).+$/)) {
        matchMessage.style.display = 'block';
        notMatchMessage.style.display = 'none';
        submitButton.disabled = false;
      } else {
        matchMessage.style.display = 'none';
        notMatchMessage.style.display = 'block';
        submitButton.disabled = true;
      }
    },
    password_cancel(){
      const userConfirmed = confirm("취소하시겠습니까?");
      if (userConfirmed) {
        this.$router.push('/mypage');
      }
    }
  }
}
</script>

<style scoped>
.content {
  width: 80%;
  position: relative;
  top:20px;
}

.content-title{
  margin-bottom: 10px;
  font-weight: bolder;
  color: black;
  font-size: 15px;
  position: relative;
  left:-460px;
  top:-20px;
}

.content-line{
  border-top: 1px solid lightgrey;
  width: 100%;
}

.repassword-title{
  position: relative;
  left:-468px;
  font-size: 14px;
  font-weight: bolder;
  color: #333333;
  top:20px;
}

.repassword-check-title{
  position: relative;
  left:-451px;
  font-size: 14px;
  font-weight: bolder;
  color: #333333;
  top:20px;
}

.col-sm-10{
  position: relative;
  top:25px;
  width: 360px;
}

#cancel{
  position: relative;
  left:405px;
  top:240px;
  font-size: 13.3px;
  font-weight: bolder;
  width: 65px;
  height: 33px;
  background-color: darkgray;
  border-color: darkgray;
}

#cancel:hover{
  background-color: #555555;
  border-color: #555555;
  font-weight: bolder;
}

#delete-user-button{
  position: relative;
  left:425px;
  top:240px;
}

.password-match, .password-not-match {
  font-size: 13px;
  position: absolute;
  left: 335px;
  top:180px;
  text-align: left;
}

.password-match {
  color: green;
}

.password-not-match {
  color: red;
}
</style>
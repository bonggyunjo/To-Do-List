<template>
  <main class="content">
    <h6 class="content-title">닉네임 변경</h6>
    <span style="font-size: 13px; position: relative; left:-415.5px; top:-25px; color: lightslategrey">새로운 닉네임을 입력해 주세요.</span>
    <div class="content-line"></div>

    <p style="font-size: 13px; color: #333333; margin-top: 5px; text-align: left; position: relative; top:5px;">현재 사용하고 있는 닉네임은 <span style="font-size: 13.5px; color: black; font-weight: bolder;">{{userNickname}}</span> 입니다.</p>
    <p style="font-size: 13px;  color: #333333; margin-top: 5px; text-align: left;">새로운 닉네임을 입력하고 중복 확인을 통해 사용 가능한 닉네임인지 확인하세요.</p>
    <div id="renickname">

      <span class="renickname-title">새 닉네임</span>
      <button @click="checkIdbut  " id="checkbtnid" type="submit" class="btn btn-light" style="position: relative; left:-185px; top:57px; font-weight: bolder; font-size: 13px;  color:#333333; background-color: lightgray; border: lightgray;">중복확인</button>
      <div class="mb-3 row">
        <div class="col-sm-10">
          <input type="password" class="form-control" id="inputnickname" placeholder="new nickname.." style="font-size: 12px; width: 300px;" v-model="nickname" @input="checkPasswords" >
        </div>
      </div>
    </div>
    <button class="btn btn-secondary" id="cancel" @click="password_cancel">취소</button>
    <button class="btn btn-success" id="delete-user-button" :disabled="!isNicknameAvailable" @click="changebutton" >변경하기</button>
  </main>
</template>
<script>

import axios from "axios";
import {mapGetters} from "vuex";

export default{
  name:'ChangeNickname',
  data() {
    return{
      nickname:'',
      isNicknameAvailable : false,
    }
  },
  computed: {
    ...mapGetters(['userNickname']),
  },
  methods :{
    checkIdbut(){
      if (!this.nickname.trim()) {
        alert('닉네임을 입력해주세요.');
        return;
      }
      if (this.nickname.length < 4) {
        alert('닉네임은 4글자 이상으로 입력해 주세요.');
        return;
      }
      this.isNicknameChecked=true;
      axios.get(`http://localhost:8081/checknickname/${encodeURIComponent(this.nickname)}`).then(res => {
        const result = res.data;
        if (result) {
          alert('사용 가능한 닉네임입니다.');
          this.isNicknameAvailable = true;
        } else if(!result) {
          alert("이미 사용중인 닉네임입니다..");
          this.isNicknameAvailable = false;
        }
      })
    },
    async changebutton(){
      if (!this.isNicknameAvailable) {
        alert('닉네임이 사용 가능해야 변경할 수 있습니다.');
        return;
      }

      try {
        const userId = this.$store.getters.getUserId;
        const response = await axios.put(`http://localhost:8081/mypage/update`, {
          userId: userId,
          nickname: this.nickname
        }, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`
          }
        });

        alert('닉네임이 변경되었습니다.');

        this.$store.commit('updateNickname', this.nickname);

        console.log("response",response);

        this.$router.push('/mypage');
      } catch (error) {
        console.error('닉네임 변경 실패:', error);
        alert('닉네임 변경에 실패했습니다. 다시 시도해 주세요.');
      }
    },
    password_cancel() {
      const userConfirmed = confirm("취소하시겠습니까?");
      if (userConfirmed) {
        this.$router.push('/mypage');
      }
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
  left:-468px;
  top:-20px;
}

.content-line{
  border-top: 1px solid lightgrey;
  width: 100%;
}

.renickname-title{
  position: relative;
  left:-437px;
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
  top:335px;
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
  top:335px;
}

</style>
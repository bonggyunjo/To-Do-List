<template>
  <div>
  <div class="lottie-animation" style="width:150px; height: 150px; margin: auto; position: relative; top:100px;"></div>
  <div class="sign-up">
    <h1 style="font-size: 21px; padding:20px; color: #007BFF; font-weight: bolder;"> <span style="position: relative; left:-75px;">회원가입을 위해 </span> <br><span style="position: relative; left:-50px; top:5px;">정보를 입력해 주세요.</span></h1>
      <section>
        <div class="form-group">
          <label for="userId" style="position: relative; left:-59px;">이메일</label>
          <button @click="checkIdbut  " id="checkbtnid" type="submit" class="btn btn-light" style="position: relative; left:-115px; top:-2px; font-weight: bolder; font-size: 13px;  color:#333333;">중복확인</button>
          <input type="text" id="userId" v-model="userId" @input="checkId" style="position: relative;  left:-10px; width: 280px; top:3px;">
        </div>
        <div>
          <span id="checkId" style="font-size: 13px; position: relative; left:-70px; top:-10px;"></span>
        </div>
      </section>

      <section>
        <div class="form-group">
          <label for="password" style="position: relative; top:-1px; left:-98px;">비밀번호</label>
          <input type="password" id="password" v-model="password" @input="checkPassword" style="position: relative; width: 280px; left:-10px;">
        </div>
        <section :style="{ color: inputStarted ? (isPasswordValid ? 'green' : 'red') : 'dimgray' }" id="pw-length" class="form-text-nickname form-text">
          <span style="font-size: 11.5px; position: relative; top:-10px; left:3px;">대소문자, 숫자, 특수문자를 포함한 8자 이상을 입력하세요.</span>
        </section>
      </section>

      <div class="form-group">
        <label for="nickname" style="position: relative; left:-59px;">닉네임</label>
        <button type="submit" @click="checknicknamebut" class="btn btn-light" style="position: relative; left:-115px; top:-2px; color:#333333; font-weight: bolder; font-size: 13px;">중복확인</button>
        <input type="text" id="nickname" v-model="nickname" minlength="4" nmaxlength="15" style="position: relative; width: 280px; left:-10px;">
      </div>
      <section>
      <span style="font-size: 11.5px; position: relative; top:-10px; left:-46px;">닉네임은 4~15자 사이로 입력해 주세요.</span>
      </section>
      <div class="form-group">
        <label for="intro" style="position: relative; top:-1px; left:-98px;">대표 한마디</label>
        <input type="text" id="intro" v-model="intro" style="position: relative; width: 280px; left:-10px;">
      </div>
      <button type="submit" @click="submitForm" class="btn btn-primary" style="width: 285px; top:5px; left:-5px; position: relative; height: 50px;">회원가입</button>
  </div>
  </div>
</template>

<script>
import lottie from 'lottie-web';
import axios from 'axios';
export default {
  mounted() {
    lottie.loadAnimation({
      container: this.$el.querySelector('.lottie-animation'), // 애니메이션을 표시할 DOM 요소
      renderer: 'svg',
      loop: true,
      autoplay: true,
      path: 'https://assets10.lottiefiles.com/packages/lf20_x62chJ.json' // 애니메이션 파일 경로
    });
  },
  data() {
    return {
      userId: '',
      nickname: '',
      password: '',
      intro: '',
      isUserIdValid: false,
      isPasswordValid: false,
      inputStarted: false,
      isIdChecked:false,
      isNicknameChecked:false,
      isIdAvailable: false,
      isNicknameAvailable: false,
    };
  },

  methods: {
      checkIdbut() {
        this.isIdChecked = true;
        if (!this.userId.trim()) {
          alert('이메일을 입력해주세요.');
          return;
        }
        if (!this.isUserIdValid ) {
          alert('유효하지 않은 이메일 형식입니다.');
          return; // 유효하지 않으면 여기서 함수 종료
        }      axios.get(`http://localhost:8081/checkuserId/${encodeURIComponent(this.userId)}`).then(res => {
              const result = res.data;
              if (result) {
                alert('사용 가능한 아이디입니다.');
                this.isIdAvailable = true; // 아이디 사용 가능 상태 업데이트
              } else{
                alert("이미 사용중인 아이디입니다.");
                this.isIdAvailable = false; // 아이디 사용 불가 상태 업데이트
              }
            }
        )
            .catch(error => {
              if (error.response) {
                alert("서버 오류가 발생했습니다. 나중에 다시 시도해주세요.");
              } else if (error.request) {
                alert("서버 응답이 없습니다. 네트워크 연결을 확인해주세요.");
              } else {
                alert("요청을 처리하는 중 오류가 발생했습니다.");
              }
            });
      },
    checkId() {
      const validateEmail = (email) => {
        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        return emailPattern.test(email);
      };

      const email = this.userId;
      const emailMessage = document.querySelector("#checkId");

      if (validateEmail(email)) {
        emailMessage.textContent = '올바른 이메일 형식입니다.';
        emailMessage.style.color = 'darkgreen';
        this.isUserIdValid = true;
      } else {
        emailMessage.textContent = '일치하지 않은 형식입니다.';
        emailMessage.style.color = 'red';
        this.isUserIdValid = false;
      }
    },
    checkPassword() {
      this.inputStarted = true;
      const validatePassword = (password) => {
        const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
        return passwordPattern.test(password);
      };

      this.isPasswordValid = validatePassword(this.password);
    },
    async submitForm() {
      if (!this.userId.trim() || !this.password.trim() || !this.nickname.trim() || !this.intro.trim()) {
        alert('빈칸을 입력해주세요.');
        return;
      }
      if (!this.isIdChecked || !this.isIdAvailable) {
        alert('아이디 중복 확인을 해주세요.');
        return;
      }
      if(!this.isNicknameChecked || !this.isNicknameAvailable){
        alert('닉네임 중복 확인을 해주세요.');
        return;
      }
      if (!this.isUserIdValid ) {
        alert('이메일 형식으로 입력해 주세요.');
        return;
      }
      if (!this.isPasswordValid ) {
        alert('비밀번호 형식이 일치하지 않습니다.');
        return;
      }
      const userData = {
        userId: this.userId,
        password: this.password,
        nickname: this.nickname,
        intro: this.intro,
      };

      try {
        const response = await axios.post('http://localhost:8081/signup', userData);
        console.log("성공", response.data);
        alert('회원가입에 성공하였습니다.');
        this.$router.push('/login');
      } catch (error) {
        console.error("실패", error);
      }
    },
    checknicknamebut(){
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
  top:30px;
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
  width: 100px;
  margin-bottom: 5px;
  font-weight: bold;
  color: #555;
  text-align: left;
  position: relative;
  left:33px;
  font-size: 14px;
}

.form-group input {
  width: calc(100% - 110px);
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
  margin-left: 10px;
}

</style>

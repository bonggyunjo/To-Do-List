<template>
  <main class="content">
    <h6 class="content-title">계정</h6>
    <span style="font-size: 13px; position: relative; left:-405px; top:-25px; color: lightslategrey">이곳은 개인정보 보호 공간 입니다.</span>
    <div class="content-line"></div>
    <div id="email">
      <span class="email-title">이메일</span>
      <br>
      <span style="position: absolute; left:-1px; top:95px; text-align: left; font-size: 15px;">{{ userId }}</span>
    </div>

    <div id="nickname">
      <span class="nickname-title">닉네임</span>
      <div class="input-group mb-3" id="nickname-field">
        <span style="font-size: 14px; position: relative; top:30px; left:2px;">{{ nickname }}</span>
        <router-link to="/mypage/user/nicknamechange"><button class="btn btn-outline-secondary" type="button" id="button-addon2" style="font-size: 11.5px; font-weight: bolder; border-radius: 6px; border: lightgray; position: absolute; top:53px; left:0px;height: 31px; width: 90px; background-color: lightgray; color: #333333;">닉네임 변경</button></router-link>
      </div>

    </div>
    <div id="password">
      <span class="passowrd-title">패스워드</span>
      <br>
      <router-link to="/mypage/user/passwordchange"><button class="btn btn-secondary" id="password-change-button">비밀번호 변경</button></router-link>
    </div>
    <div class="content-line" style="position: relative; top:100px;"></div>

    <div id="delete-user">
      <span class="delete-user-title"> 계정 삭제</span>
    </div>

    <span style="font-size: 13px; position: relative; left:-130px; top:130px; color: #333333;">이것은 작업, 프로젝트, 댓글, 및 그 이상을 포함한 당신의 모든 데이터를 즉시 삭제합니다. 실행 취소될 수 없습니다.</span>
    <router-link to="/mypage/user/delete"><button class="btn btn-outline-danger" id="delete-user-button">계정 삭제</button></router-link>
  </main>
</template>

<script >
import { mapGetters } from 'vuex';
export default {
  name:'AccountSettings',
  computed: {
    ...mapGetters(['getUserId', 'userNickname']),
    userId() {
      return this.getUserId; // userId를 computed 속성으로 가져옴
    },
    nickname(){
      return this.userNickname;
    }
  }
}
</script>

<style>
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
  left:-490px;
  top:-20px;
}

.content-line{
  border-top: 1px solid lightgrey;
  width: 100%;
}

.nickname-title{
  color: black;
  font-size: 14px;
  font-weight: bolder;
  position: relative;
  left:-485px;
  top:50px;
}

#nickname-field{
  position: relative;
  top: 20px;
  width: 300px;
  left:-3px;
}

.email-title{
  color: black;
  font-size: 14px;
  font-weight: bolder;
  position: relative;
  left:-485px;
  top:20px;
}

.passowrd-title{
  color: black;
  font-size: 14px;
  font-weight: bolder;
  position: relative;
  left:-475px;
  top:70px;
}

#email-change-button:hover, #password-change-button{
  background-color: lightgray;
}

#password-change-button{
  position: relative;
  left:-453px;
  top:75px;
  background-color: lightgray;
  font-size: 12px;
  width: 110px;
  height: 33px;
  border-color: #cccccc;
  color: #333333;
  font-weight: bolder;
  border: lightgray;
}

.delete-user-title{
  color: black;
  font-size: 14px;
  font-weight: bolder;
  position: relative;
  left:-476px;
  top:130px;
}

#delete-user-button{
  position: relative;
  left:-793px;
  top:175px;
  font-size: 13px;
  width: 90px;
  height: 33px;
  font-weight: bolder;
}
</style>
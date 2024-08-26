
<template>
  <main class="content">
    <h6 class="content-title">계정</h6>
    <span style="font-size: 13px; position: relative; left:-405px; top:-25px; color: lightslategrey">이곳은 개인정보 보호 공간 입니다.</span>
    <div class="content-line"></div>

    <div id="nickname">
      <span class="nickname-title">닉네임</span>
      <div class="input-group mb-3" id="nickname-field">
        <input type="text" class="form-control" placeholder="username" aria-label="username" aria-describedby="button-addon2" style="border-right: none; font-size: 14px;">
        <button class="btn btn-outline-secondary" type="button" id="button-addon2" style="font-size: 13px; font-weight: bolder; border-radius: 10px; position: relative; left:-10px;">변경</button>
      </div>

    </div>
    <div id="email">
      <span class="email-title">이메일</span>
      <br>
      <span style="position: relative; left:-464px; top:20px;">이메일 공간</span>
      <br>
      <button class="btn btn-secondary" id="email-change-button">이메일 변경</button>
    </div>
    <div id="password">
      <span class="passowrd-title">패스워드</span>
      <br>
      <button class="btn btn-secondary" id="password-change-button">비밀번호 변경</button>
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
export default {
  name:'AccountSettings'
}
</script>

<style>

.content {
  width: 80%; /* 오른쪽 섹션 너비 */
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
  top:10px;
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
  left:-480px;
  top:45px;
}
#email-change-button{
  position: relative;
  left:-460px;
  top:30px;
  background-color : #dddddd;
  font-size: 13px;
  width: 95px;
  height: 33px;
  border-color: #cccccc;
  color: #333333;
  font-weight: bolder;
}
#email-change-button:hover, #password-change-button{
  background-color: lightgray;
}

#password-change-button{
  position: relative;
  left:-453px;
  top:55px;
  background-color : #dddddd;
  font-size: 13px;
  width: 110px;
  height: 33px;
  border-color: #cccccc;
  color: #333333;
  font-weight: bolder;
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
  top:165px;
  font-size: 13px;
  width: 90px;
  height: 33px;
  font-weight: bolder;
}
</style>
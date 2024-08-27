<template>
  <main class="content">
    <h6 class="content-title">비밀번호 변경</h6>
    <span style="font-size: 13px; position: relative; left:-421.5px; top:-25px; color: lightslategrey">새 비밀번호를 입력해 주세요.</span>
    <div class="content-line"></div>

    <div id="repassword">
      <span class="repassword-title">새 비밀번호</span>
      <div class="mb-3 row">
        <div class="col-sm-10">
          <input type="password" class="form-control" id="inputPassword" placeholder="new password.." style="font-size: 12px;">
        </div>
      </div>
    </div>

    <div id="repassword-check">
      <span class="repassword-check-title">새 비밀번호 확인</span>
      <div class="mb-3 row">
        <div class="col-sm-10">
          <input type="password" class="form-control" id="inputPassword" placeholder="please password check.." style="font-size: 12px;">
        </div>
      </div>
      <span style="position: relative; top:20px; left:-255px; font-size: 13px; color: #333333;">패스워드는 대문자, 소문자, 영문자, 특수 기호를 포함하여 8자 이상으로 설정해 주세요.</span>
    </div>
   <button class="btn btn-secondary" id="cancel" @click="password_cancel">취소</button>
    <button class="btn btn-success" id="delete-user-button">변경하기</button>
  </main>
</template>

<script>


export default{
 name:'ChangePassword',

  methods :{
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
  left:400px;
  top:230px;
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
  left:420px;
  top:230px;
}
</style>
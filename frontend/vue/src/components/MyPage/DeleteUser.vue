<script >
export default {
  name : 'DeleteUser'
}
</script>

<template>
  <main class="content">
    <h6 class="content-title">계정 삭제</h6>
    <span style="font-size: 14px; position: relative; left:-382px; top:-25px; color: lightslategrey">To-Do-List를 이용해 주셔서 감사합니다!</span>
    <div class="content-line"></div>

    <div id="content-for-delete">
      <span class="delete-content">당신의 계정이 영구 삭제됩니다. 당신의 <span style="font-weight: bolder; color: black">모든 데이터는 즉시 삭제되며</span> 복원될 수 없습니다.</span>
    </div>
    <span class="content-for-delete-ismain">
      계정 삭제 시 <span style="color: red; font-weight: bolder;">모든 데이터는 즉시 삭제되며</span> 복구할 수 없습니다.
      <br>
      <span style="position: relative; left:99px;">이로 인해 이전에 저장한 작업, 프로젝트, 설정 등이 모두 사라지니 신중하게 결정하시기 바랍니다.</span>
      <br>
      <span style="position: relative; left:-105px;">필요시 백업을 고려하세요.</span>
      <br>
      <span style="position: relative; left:-17px; top:14px; color: green; font-weight: bolder; font-size: 13px;">위의 모든 사항을 확인하였으며, 이에 동의합니다.</span>
      </span>
    <div class="form-check" style="position: relative; top:40px; left:-4px;">
      <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
      <label class="form-check-label" for="defaultCheck1">
      </label>
    </div>
    <button class="btn btn-outline-danger" id="delete-user-button">계정 삭제</button>
  </main>
</template>

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
  left:-474px;
  top:-20px;
}
.content-line{
  border-top: 1px solid lightgrey;
  width: 100%;
}

.delete-content{
  position: relative;
  left:-230px;
  top:15px;
  font-size: 14px;
}
.content-for-delete-ismain{
  position: relative;
  top:50px;
  left:-327px;
  font-size: 13px;
  color:lightslategrey;
}
#delete-user-button{
  position: relative;
  top:260px;
  left:450px;
}
</style>
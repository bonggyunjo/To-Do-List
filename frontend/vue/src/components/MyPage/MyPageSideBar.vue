<template>
  <div class="sidebar">
    <h6 class="header-title">설정</h6>

  </div>
</template>

<script>
export default {
  name: 'MyPageSideBar',
}
</script>

<style scoped>
.sidebar {
  width: 250px; /* 왼쪽 섹션 너비 */
  padding: 20px;
  background-color : white;
  border-right: 1px solid #dee2e6; /* 경계선 추가 */
  box-shadow: 2px 0 5px rgba(0.1, 0.1, 0.1, 0.001); /* 그림자 효과를 더 연하게 변경 */
  position: relative;
  left: -20px;
  border-radius: 4px;
  height: 550px;
  top: -20px;
}

.header-title {
  margin-bottom: 20px;
  font-weight: bold;
  color: #343a40; /* 어두운 텍스트 색상 */
  font-size: 18px;

}

</style>

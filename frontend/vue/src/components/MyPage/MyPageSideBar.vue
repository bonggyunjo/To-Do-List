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
  width: 20%; /* 왼쪽 섹션 너비 */
  padding: 20px;
}

.header-title {
  margin-bottom: 10px;
  font-weight: bolder;
  color: black;
  font-size: 18px;
  position: relative;
  left:-110px;
  top:-20px;
}
</style>
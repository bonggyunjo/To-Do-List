<template>
  <div class="sidebar">
    <h6 class="header-title">설정</h6>
    <button id="board-btn" @click="goToMyPosts">내가 작성한 게시글</button>
  </div>
</template>

<script>
export default {
  name: 'MyPageSideBar',
  methods: {
    goToMyPosts() {
      // 버튼 클릭 시 /mypage/board/get 경로로 이동
      this.$router.push('/mypage/board/get');
    }
  }
}
</script>

<style scoped>
.sidebar {
  width: 250px;
  padding: 20px;
  background-color: white;
  border-right: 1px solid #dee2e6;
  box-shadow: 2px 0 5px rgba(0.1, 0.1, 0.1, 0.001);
  position: relative;
  left: -20px;
  border-radius: 4px;
  height: 550px;
  top: -20px;
}

.header-title {
  margin-bottom: 20px;
  font-weight: bold;
  color: #343a40;
  font-size: 18px;
}
#board-btn{
  background-color: white;
  border:none;
}
</style>

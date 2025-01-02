<template>
  <div class="sidebar">
    <h6 class="header-title">설정</h6>
    <button id="board-btn" @click="goToMyPosts">작성한 게시글</button>
    <button id="board-btn" @click="goToMyRecords">작업 목록</button>
    <button id="board-btn" @click="goToMyTrash">휴지통</button>
  </div>
</template>

<script>
export default {
  name: 'MyPageSideBar',
  methods: {
    goToMyPosts() {
      // 버튼 클릭 시 /mypage/board/get 경로로 이동
      this.$router.push('/mypage/board/get');
    },
    goToMyRecords(userId) {
      this.$router.push(`/pages/${userId}`);
    },
    goToMyTrash(){
      this.$router.push(`/trash`);
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

#board-btn {
font-size: 14px;
  width: 150px;
  height: 40px;
  background-color: white;
  border: none;
  color: #333333;
  font-weight: bolder;
}

</style>

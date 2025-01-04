<template>
  <main class="content">
    <div class="board-container">
      <div class="table-header">
        <div class="table-title">NO</div>
        <div class="table-title">ID</div>
        <div class="table-title">제목</div>
        <div class="table-title">작성일</div>
      </div>
      <div style="border-top: 1px solid #e0e0e0;"></div>
      <ul>
        <li v-for="post in boards" :key="post.postId" class="post-item">
          <div class="post-info">
            <span class="post-id" @click="goToPost(post.postId)">{{ post.postId }}</span>
            <span class="post-user" @click="goToPost(post.postId)">{{ post.nickname }}</span>
            <span class="post-title" @click="goToPost(post.postId)">{{ post.title }}</span>
            <small class="post-date" @click="goToPost(post.postId)">{{ formatDate(post.createdAt) }}</small>
          </div>
        </li>
      </ul>
      <button @click="goBack" class="back-button">뒤로가기</button> <!-- 뒤로가기 버튼 추가 -->
      <p v-if="!boards.length" class="no-boards">게시글이 없습니다.</p>
    </div>
  </main>
</template>
<script>
import axios from 'axios';

export default {
  name: 'MyBoardList',
  data() {
    return {
      boards: [], // 게시글 목록을 저장할 배열
      isLoading: true, // 로딩 상태 추가
    };
  },
  created() {
    this.fetchBoards();
  },

  methods: {
    async fetchBoards() {
      const userId = this.$store.getters.getUserId;
      const token = localStorage.getItem('token');
      if (!userId || !token) {
        console.error('사용자 ID 또는 토큰이 없습니다. 로그인 후 다시 시도하세요.');
        this.isLoading = false; // 로딩 종료
        return;
      }
      try {
        const response = await axios.get(`http://localhost:8081/mypage/board/get/${encodeURIComponent(userId)}`, {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });

        console.log('게시글 응답:', response.data); // 응답 데이터 확인
        console.log('필터링된 게시글:', this.boards); // 필터링 후 게시글 로그 추가

        // 응답 데이터가 배열인지 확인
        if (Array.isArray(response.data)) {
          this.boards = response.data.filter(post => post.postId && post.nickname && post.title && post.createdAt);

          console.log('필터링된 게시글:', this.boards); // 필터링된 게시글 확인
        } else {
          console.error('응답 데이터가 배열이 아닙니다:', response.data);
          this.boards = []; // 비어있는 배열로 초기화
        }

        console.log('현재 boards 배열:', this.boards); // 현재 boards 배열의 내용 확인
      } catch (error) {
        console.error('게시글을 가져오는 데 실패했습니다:', error);
      } finally {
        this.isLoading = false; // 로딩 종료
      }
    },
    goToPost(postId) {
      this.$router.push({ path: `/board/detail/${postId}` });
    },
    goBack() {
      this.$router.push('/mypage'); // mypage 경로로 이동
    },
    formatDate(dateString) {
      const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
      return new Date(dateString).toLocaleDateString('ko-KR', options);
    }
  },
};
</script>

<style scoped>
.content {
  width: 80%;
  margin: auto;
  padding: 20px;
}

.board-container {
  max-width: 1100px;
  margin: auto;
  border-radius: 10px;
  background-color: #ffffff;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  padding: 20px;
}

.back-button {
  margin-bottom: 20px; /* 버튼과 게시글 목록 사이의 간격 */
  padding: 10px 15px;
  font-size: 16px;
  color: #fff;
  background-color: #007bff; /* 버튼 색상 */
  border: none;
  border-radius: 5px;
  cursor: pointer;
  position: relative;
  left: 400px;
  top: 20px;
}

.back-button:hover {
  background-color: #0056b3; /* 버튼 호버 색상 */
}

h1 {
  text-align: center;
  color: #333;
  margin-bottom: 20px;
  font-family: 'Arial', sans-serif;
}

.table-header {
  display: grid;
  grid-template-columns: 1fr 1fr 2fr 1fr;
  padding: 10px;
  border-radius: 8px;
  margin-bottom: 10px;
  color: #555555;
  font-size: 14px;
  font-weight: bolder;
}

.table-title {
  font-weight: bold;
  text-align: center;
}

ul {
  list-style-type: none;
  padding: 0;
}

.post-item {
  border-top: 1px solid #e0e0e0;
  margin: 15px 0;
  padding: 15px;
  transition: transform 0.2s;
  color: #333333;
  font-size: 15px;
}

.post-info {
  display: grid;
  grid-template-columns: 1fr 1fr 2fr 1fr;
  align-items: center;
}

.post-id,
.post-user,
.post-title,
.post-date {
  text-align: center;
  cursor: pointer;
}

.no-boards {
  text-align: center;
  color: #888;
  font-size: 18px;
}
</style>

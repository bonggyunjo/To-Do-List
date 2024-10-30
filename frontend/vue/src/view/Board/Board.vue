<template>
  <div class="board-list">
    <div class="board-container">
      <div class="table-header">
        <div class="table-title" style="position: relative; left:-60px;">NO</div>
        <div class="table-title" style="position: relative; left:-60px;">ID</div>
        <div class="table-title">제목</div>
        <div class="table-title">작성일</div>
      </div>
      <ul>
        <li v-for="post in paginatedPosts" :key="post.postId" class="post-item">
          <div class="post-info">
            <span class="post-id" style="position: relative; left:-60px;">{{ post.postId }}</span>
            <span class="post-user" style="position: relative; left:-60px;">{{ post.nickname }}</span>
            <h6 class="post-title">{{ post.title }}</h6>
            <small class="post-date">{{ formatDate(post.createdAt) }}</small>
          </div>
        </li>
      </ul>
      <router-link to="/board/write" class="add-post">새 게시글 작성</router-link>

      <div class="pagination">
        <button @click="prevPage" :disabled="currentPage === 1">이전</button>
        <span>페이지 {{ currentPage }} / {{ totalPages }}</span>
        <button @click="nextPage" :disabled="currentPage === totalPages">다음</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'BoardList',
  data() {
    return {
      posts: [],
      currentPage: 1,
      postsPerPage: 10
    };
  },
  created() {
    this.fetchPosts();
  },
  computed: {
    totalPages() {
      return Math.ceil(this.posts.length / this.postsPerPage);
    },
    paginatedPosts() {
      const start = (this.currentPage - 1) * this.postsPerPage;
      return this.posts.slice(start, start + this.postsPerPage);
    }
  },
  methods: {
    async fetchPosts() {
      try {
        const response = await axios.get('http://localhost:8081/boards');
        this.posts = response.data;
      } catch (error) {
        console.error('게시글을 가져오는 데 오류가 발생했습니다:', error);
      }
    },
    formatDate(dateString) {
      const options = { year: 'numeric', month: 'long', day: 'numeric' };
      return new Date(dateString).toLocaleDateString('ko-KR', options);
    },
    goToPost(postId) {
      this.$router.push({ name: 'BoardDetail', params: { postId } }); // 상세보기 페이지로 이동
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    }
  }
};
</script>

<style scoped>
.board-list {
  padding: 20px;
  max-width: 1100px;
  margin: auto;
  border-radius: 10px;
}

h1 {
  text-align: center;
  color: #333;
  margin-bottom: 20px;
  font-family: 'Arial', sans-serif;
}

.board-container {
  border-radius: 10px;
  padding: 20px;
}

.table-header {
  display: grid;
  grid-template-columns: 1fr 1fr 2fr 1fr; /* 열의 너비 조정 */
  background-color: #f1f1f1; /* 헤더 배경색 */
  padding: 10px;
  border-radius: 8px;
  margin-bottom: 10px;
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
  border: 1px solid #e0e0e0;
  margin: 15px 0;
  padding: 15px;
  transition: transform 0.2s;
}

.post-item:hover {
  transform: translateY(-3px);
}

.post-info {
  display: grid;
  grid-template-columns: 1fr 1fr 2fr 1fr; /* 열의 너비 조정 */
  align-items: center; /* 세로 정렬 */
}

.post-id,
.post-user,
.post-title,
.post-date {
  text-align: center; /* 가운데 정렬 */
}

.post-content {
  margin: 10px 0;
}

.read-more {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 15px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.read-more:hover {
  background-color: #0056b3;
}

.add-post {
  background-color: #28a745;
  color: white;
  border: none;
  padding: 10px 15px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
  display: block;
  margin: 20px auto;
  text-decoration: none;
  width: 160px;
  text-align: center;
}

.add-post:hover {
  background-color: #218838;
}

.pagination {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.pagination button {
  margin: 0 10px;
  padding: 5px 10px;
  border: none;
  border-radius: 5px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s;
}

.pagination button:hover {
  background-color: #0056b3;
}

.pagination button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}
</style>

<template>
  <div class="board-list">
    <h1>게시글 목록</h1>
    <div class="board-container">
      <ul>
        <li v-for="post in posts" :key="post.postId" class="post-item">
          <div class="post-header">
            <h2>{{ post.title }}</h2>
            <small class="post-date">{{ formatDate(post.createdAt) }}</small>
          </div>
          <p class="post-content">{{ post.content }}</p>
          <button class="read-more" @click="goToPost(post.postId)">상세보기</button>
        </li>
      </ul>
      <router-link to="/board/write" class="add-post">새 게시글 작성</router-link>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'BoardList',
  data() {
    return {
      posts: []
    };
  },
  created() {
    this.fetchPosts();
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
    }
  }
};
</script>

<style scoped>
.board-list {
  padding: 20px;
  max-width: 800px;
  margin: auto;
  background-color: #f8f9fa; /* 배경색 추가 */
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

h1 {
  text-align: center;
  color: #333;
  margin-bottom: 20px;
  font-family: 'Arial', sans-serif; /* 폰트 설정 */
}

.board-container {
  background-color: #ffffff; /* 게시판 배경색 */
  border-radius: 10px;
  padding: 20px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

ul {
  list-style-type: none;
  padding: 0;
}

.post-item {
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  margin: 15px 0;
  padding: 15px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s;
  background-color: #f9f9f9; /* 게시글 배경색 */
}

.post-item:hover {
  transform: translateY(-3px);
}

.post-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.post-date {
  font-size: 0.9em;
  color: #888;
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
  text-align: center; /* 텍스트 가운데 정렬 */
}

.add-post:hover {
  background-color: #218838;
}
</style>

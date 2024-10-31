<template>
  <div class="board-detail-page">
    <div class="post-container" v-if="post">
      <h1 class="post-title">{{ post.title }}</h1>
      <div class="post-meta">
        <span class="post-author">작성자: {{ post.nickname }}</span>
        <span class="post-date">작성일: {{ formatDate(post.createdAt) }}</span>
      </div>
      <div class="post-content">
        <p>{{ post.content }}</p>
      </div>
      <router-link to="/board" class="back-button">목록으로 돌아가기</router-link>
    </div>
    <div v-else>로딩 중...</div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      post: null, // 게시글 데이터를 저장할 변수
    };
  },
  created() {
    const postId = this.$route.params.postId; // URL에서 postId 가져오기
    this.fetchPost(postId); // 게시글 데이터 가져오기
  },
  methods: {
    async fetchPost(postId) {
      try {
        const response = await axios.get(`http://localhost:8081/boards/detail/${postId}`);
        this.post = response.data;
      } catch (error) {
        console.error('게시글을 가져오는 데 오류가 발생했습니다:', error);
      }
    },
    formatDate(dateString) {
      const options = { year: 'numeric', month: 'long', day: 'numeric' };
      return new Date(dateString).toLocaleDateString('ko-KR', options); // 날짜 포맷팅
    },
  },
};
</script>

<style scoped>
.board-detail-page {
  max-width: 800px;
  margin: 20px auto;
  padding: 20px;
  background-color: #ffffff;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.post-container {
  padding: 20px;
}

.post-title {
  font-size: 28px;
  font-weight: bold;
  margin-bottom: 10px;
  color: #333;
}

.post-meta {
  font-size: 14px;
  color: #777;
  margin-bottom: 15px;
}

.post-author,
.post-date {
  margin-right: 15px;
}

.post-content {
  font-size: 16px;
  line-height: 1.6;
  color: #444;
}

.back-button {
  display: inline-block;
  margin-top: 20px;
  padding: 10px 15px;
  background-color: #007bff;
  color: white;
  text-decoration: none;
  border-radius: 5px;
  transition: background-color 0.3s;
}

.back-button:hover {
  background-color: #0056b3;
}
</style>

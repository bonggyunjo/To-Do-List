<template>
  <div class="board-detail-page">
    <div class="post-container" v-if="post">

      <div class="post-header">
        <span class="post-label">제목 :</span>
        <h1 class="post-title">{{ post.title }}</h1>
      </div>
      <div style="border-bottom: 1px solid lightgray"></div>
      <div class="post-meta">
        <span class="post-author">작성자: {{ post.nickname }}</span>
        <span class="post-date">작성일: {{ formatDate(post.createdAt) }}</span>
      </div>
      <div style="border-bottom: 1px solid lightgray; position: relative; top:5px;"></div>
      <div class="post-content">
        <span class="post-label">내용</span>
        <p>{{ post.content }}</p>
      </div>
      <!-- 수정 및 삭제 버튼 추가 -->
      <div v-if="isAuthor" class="post-actions">
        <router-link :to="`/board/edit/${post.postId}`" style="text-decoration: none; color: black;">
          <span>수정</span>
        </router-link>
        <span style="position: relative; left:5px; color: red;" @click="deletePost(post.postId)">삭제</span>
      </div>

    </div>
    <div v-else>로딩 중...</div>
    <router-link to="/board">
      <button class="btn btn-secondary" style="font-size: 13.5px; width: 90px; height: 35px; position: relative; left:415px; top:280px;">뒤로가기</button>
    </router-link>
  </div>
</template>

<script>
import axios from 'axios';
import { mapGetters } from 'vuex';

export default {
  data() {
    return {
      post: null,
    };
  },
  computed: {
    ...mapGetters(['userNickname']),
    isAuthor() {
      return this.post && this.post.nickname === this.userNickname;
    },
  },
  created() {
    const postId = this.$route.params.postId;
    this.fetchPost(postId);
  },
  methods: {
    async fetchPost(postId) {
      try {
        const response = await axios.get(`http://localhost:8081/boards/detail/${postId}`);
        this.post = response.data;
        console.log(response);
      } catch (error) {
        console.error('게시글을 가져오는 데 오류가 발생했습니다:', error);
      }
    },
    async deletePost(postId){
      try {
        const response = await axios.delete(`http://localhost:8081/boards/${postId}`);
        this.post = response.data;
        console.log(response);
        const userConfirmed = confirm("삭제하시겠습니까?");
        if (userConfirmed) {
          alert("삭제되었습니다.");
          this.$router.push('/board');
        }
      } catch (error) {
        console.error('게시글을 가져오는 데 오류가 발생했습니다:', error);
      }
    },
    formatDate(dateString) {
      const options = {year: 'numeric', month: 'long', day: 'numeric'};
      return new Date(dateString).toLocaleDateString('ko-KR', options);
    },
  },
};
</script>

<style scoped>
.board-detail-page {
  max-width: 1000px;
  margin: 20px auto;
  padding: 20px;
  border-radius: 8px;
}

.post-container {
  padding: 20px;
}

.post-header {
  display: flex;
  align-items: baseline;
  margin-bottom: 15px;
  position: relative;
  top: 5px;
}

.post-label {
  font-weight: bold;
  margin-right: 5px;
  font-size: 14.5px;
  color: #333333;
}

.post-title {
  font-size: 28px;
  font-weight: bold;
  color: #333;
  margin: 0;
  flex: 1;
  text-align: left;
  position: relative;
  top: 4px;
}

.post-meta {
  font-size: 14px;
  color: #777;
  margin-bottom: 15px;
  position: relative;
  left: -308px;
  top: 10px;
}

.post-author {
  margin-right: 15px;
}

.post-date {
  margin-right: 15px;
  position: relative;
  left: 630px;
  top: -40px;
}

.post-content {
  font-size: 16px;
  line-height: 1.6;
  color: #444;
  text-align: left;
  position: relative;
  top: 25px;
}

.post-content .post-label {
  font-weight: bold;
  margin-top: 20px;
}

.post-actions {
  margin-top: 20px;
  position: relative;
  left:425px;
  font-size: 14px;
  top:-95px;

}

.btn {
  margin-right: 10px;
}
</style>

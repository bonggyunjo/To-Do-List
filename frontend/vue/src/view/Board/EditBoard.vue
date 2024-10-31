<template>
  <div class="board-edit-page">
    <div class="post-container">
      <form @submit.prevent="updatePost">
        <div class="post-header">
          <span class="post-label">제목 :</span>
          <input type="text" class="post-title" v-model="post.title" />
        </div>
        <div style="border-bottom: 1px solid lightgray"></div>
        <div class="post-content">
          <span class="post-label">내용</span>
          <textarea v-model="post.content" required class="textarea-content"></textarea>
        </div>
        <button type="submit" class="btn btn-success" id="edit-button">수정하기</button>
      </form>
    </div>
    <router-link to="/board">
      <button class="btn btn-secondary" style="font-size: 13.5px; width: 90px; height: 35px; position: relative; left:415px;">뒤로가기</button>
    </router-link>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      post: {
        title: '',
        content: '',
        nickname: '',
        createdAt: '',
      },
    };
  },
  created() {
    const postId = this.$route.params.postId; // URL에서 게시글 ID 가져오기
    this.fetchPost(postId); // 게시글 데이터 가져오기
  },
  methods: {
    async fetchPost(postId) {
      try {
        const response = await axios.get(`http://localhost:8081/boards/detail/${postId}`);
        this.post = response.data; // 가져온 데이터로 post 업데이트
      } catch (error) {
        console.error('게시글을 가져오는 데 오류가 발생했습니다:', error);
      }
    },
    async updatePost() {
      try {
        const postId = this.$route.params.postId; // URL에서 게시글 ID 가져오기
        await axios.put(`http://localhost:8081/boards/${postId}`, this.post); // 수정 API 호출
        alert("수정되었습니다.")
        this.$router.push('/board');
      } catch (error) {
        console.error('게시글 수정에 실패했습니다:', error);
      }
    },
    formatDate(dateString) {
      const options = { year: 'numeric', month: 'long', day: 'numeric' };
      return new Date(dateString).toLocaleDateString('ko-KR', options);
    },
  },
};
</script>

<style scoped>
.board-edit-page {
  max-width: 1000px;
  margin: 20px auto;
  padding: 20px;
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
  font-size: 15px;
  font-weight: bold;
  color: #333;
  margin: 0;
  flex: 1;
  text-align: left;
  position: relative;
  width: 100%;
  border: none;
  outline: none;

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

.btn {
  margin-right: 10px;
}
#edit-button{
  position: relative;
  top:55px;
  left:310px;
  width: 90px;
  height: 34.5px;
  font-size: 14.5px;
}
.textarea-content{
  position: relative;
  top:15px;
  width: 920px;
  height: 400px;
}
</style>

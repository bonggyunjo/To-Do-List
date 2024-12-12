<template>
  <div class="comment-section">
    <h6 class="commnet-title">댓글</h6>
    <div v-if="comments.length === 0" class="no-comments">  .</div>
    <div v-for="comment in comments" :key="comment.commentId" class="comment">
      <div class="comment-header">
        <strong>{{ comment.nickname }}</strong>
        <span class="comment-date">{{ formatDate(comment.createdAt) }}</span>
      </div>
      <p class="comment-content">{{ comment.content }}</p>
      <button
          class="delete-button"
          v-if="userId && comment.userId === userId"
          @click="deleteComment(comment.commentId)"
      >
        삭제
      </button>
    </div>
    <div class="comment-input-container">
      <textarea
          v-model="newCommentContent"
          placeholder="댓글을 입력하세요"
          class="comment-input"
      ></textarea>
      <button class="add-comment-button" @click="addComment">댓글 추가</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { mapGetters } from 'vuex';

export default {
  props: {
    postId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      comments: [],
      newCommentContent: ''
    };
  },
  computed: {
    ...mapGetters(['userNickname', 'userId']),
    userId() {
      return this.$store.getters.getUserId;
    }
  },
  methods: {
    async fetchComments() {
      try {
        const response = await axios.get(`http://localhost:8081/comments/board/${this.postId}`);
        this.comments = response.data;
        console.log(this.comments); // 댓글 데이터 확인
      } catch (error) {
        console.error('댓글을 가져오는 데 실패했습니다:', error);
      }
    },
    async addComment() {
      if (!this.newCommentContent) {
        alert('댓글 내용을 입력하세요.');
        return;
      }

      const userId = this.userId;
      const nickname = this.userNickname;

      if (!userId) {
        alert('로그인 후 댓글을 추가할 수 있습니다.');
        return;
      }

      const comment = {
        board: { postId: this.postId },
        user: { userId: userId, nickname: nickname },
        content: this.newCommentContent,
        createdAt: new Date().toISOString()
      };

      try {
        await axios.post('http://localhost:8081/comments', comment);
        this.newCommentContent = '';
        await this.fetchComments();
      } catch (error) {
        console.error('댓글 추가에 실패했습니다:', error);
      }
    },
    async deleteComment(commentId) {
      if (confirm('정말로 이 댓글을 삭제하시겠습니까?')) {
        try {
          await axios.delete(`http://localhost:8081/comments/${commentId}`);
          await this.fetchComments();
        } catch (error) {
          console.error('댓글 삭제에 실패했습니다:', error);
        }
      }
    },
    formatDate(dateString) {
      const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' };
      return new Date(dateString).toLocaleDateString('ko-KR', options);
    }
  },
  mounted() {
    this.fetchComments();
  }
};
</script>

<style scoped>
.comment-section {
  margin-top: 20px;
  padding: 20px;
  position: relative;
  top:300px;
}
.commnet-title{
  position: relative;
  left:-445px;
}
.no-comments {
  color: #888;
  font-style: italic;
}

.comment {
  margin-bottom: 15px;
  padding: 10px;
  border-top: 1px solid #e0e0e0;

  background-color: #fff;
  transition: box-shadow 0.3s;
}


.comment-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.comment-date {
  font-size: 0.9em;
  color: #888;
}

.comment-content {
  margin: 5px 0;
}

.delete-button {
  background-color: transparent;
  border: none;
  color: red;
  cursor: pointer;
  font-size: 0.9em;
  position: relative;
  left:440px;
}

.comment-input-container {
  display: flex;
  margin-top: 20px;
}

.comment-input {
  flex: 1;
  height: 50px;
  resize: none;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-right: 10px;
  font-size: 1em;
}

.add-comment-button {
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px 15px;
  cursor: pointer;
  font-size: 1em;
  transition: background-color 0.3s;
}


</style>

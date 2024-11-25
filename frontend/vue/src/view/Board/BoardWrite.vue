<template>
  <div id="boardwrite" class="board-write container">
    <form @submit.prevent="submitPost" class="post-form">
      <div class="form-group">
        <label for="title" class="board-title">제목</label>
        <input type="text" id="title" v-model="title" class="form-control" placeholder="게시글 제목" />
      </div>
      <div class="form-group">
        <label for="content" class="board-content">내용</label>
        <textarea id="content" v-model="content" class="form-control" style="height: 450px;" placeholder="내용을 입력하세요.." rows="10"></textarea>
      </div>
      <div class="d-flex justify-content-end">
        <button type="submit" class="btn btn-success mx-2" id="create-button">등록하기</button>
        <button class="btn btn-secondary mx-2" id="cancel-button" @click="cancel">취소</button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "BoardWrite",
  data() {
    return {
      title: '',
      content: ''
    };
  },

  methods: {
    cancel() {
      const userConfirmed = confirm("취소하시겠습니까?");
      if (userConfirmed) {
        this.$router.push('/board');
      }
    },
    async submitPost() {
      if (!this.title || !this.content) {
        alert("내용을 입력하세요");
        return;
      }
      const userId = this.$store.state.userId;
      try {
        const response = await axios.post('http://localhost:8081/boards/create', {
          title: this.title,
          content: this.content,
          user: { userId: userId }
        });
        console.log('게시글 작성 성공:', response.data);
        alert("글이 등록되었습니다.");
        this.$router.push('/board');
        this.title = '';
        this.content = '';
      } catch (error) {
        console.error('게시글 작성 중 오류 발생:', error);
      }
    }
  }
};
</script>

<style scoped>
.container {
  max-width: 1000px;
  margin: auto;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  background-color: #fff;
}

h1 {
  color: #333;
}

.form-group {
  margin-bottom: 15px;
}

label {
  font-weight: bold;
  text-align: left;
}

input[type="text"],
textarea {
  font-size: 16px;
  text-align: left;
}

textarea {
  resize: vertical;
}

.btn {
  transition: background-color 0.3s, transform 0.2s;
}

.btn:hover {
  transform: translateY(-2px);
}

#cancel-button {
  background-color: #6c757d;
}

#cancel-button:hover {
  background-color: #5a6268;
}
.board-title, .board-content{
  position: relative;
  left:-450px;
}
</style>

<template>
  <div id="boardwrite" class="board-write">
    <form @submit.prevent="submitPost" class="post-form">
      <div class="form-group">
        <label for="title" class="board-title">제목</label>
        <input  type="text" id="title" v-model="title" style="font-size: 13.5px;" placeholder="게시글 제목"  />

      </div>
      <div class="form-group">
        <label for="content" class="board-content">내용</label>
        <textarea id="content" v-model="content" placeholder="내용을 입력하세요.."  style="height: 400px; font-size: 13.5px;"></textarea>
      </div>
      <button type="submit" class="btn btn-success" id="create-button">등록하기</button>
      <button class="btn btn-secondary" id="cancel-button" @click="cancel">취소</button>
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
      if(!this.title || !this.content){
        alert("내용을 입력하세요");
        return;
      }
      const userId = this.$store.state.userId; // Vuex에서 사용자 ID 가져오기
      try {
        const response = await axios.post('http://localhost:8081/boards/create', {
          title: this.title,
          content: this.content,
          user:  { userId : userId}
        });
        console.log('게시글 작성 성공:', response.data);
        alert("글이 등록되었습니다.");
        this.$router.push('/board');
        // 성공 후 다른 작업 수행 (예: 목록 페이지로 리다이렉션)
        this.title = '';
        this.content = ''; // 입력 필드 초기화
      } catch (error) {
        console.error('게시글 작성 중 오류 발생:', error);
      }
    }
  }
};
</script>

<style scoped>


h1 {
  text-align: center;
  color: #333;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

input[type="text"],
textarea {
  width: 45%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
  box-sizing: border-box;
  font-size: 16px;
  position: relative;
  top:50px;
}

input[type="text"]:focus,
textarea:focus {
  border-color: #007bff;
  outline: none;
}

textarea {
  resize: vertical;
  min-height: 150px;
}

.submit-button {
  background-color: #28a745;
  color: white;
  border: none;
  padding: 10px 15px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  transition: background-color 0.3s;
  width: 10%;
  position: relative;
  top:50px;
}

.submit-button:hover {
  background-color: #218838;
}
.board-title{
 position: relative;
  left:-400px;
  top:50px;
}
.board-content{
  position: relative;
  left:-400px;
  top:50px;
}
#create-button{
 position: relative;
  top:50px;
  left:315px;
  font-size: 13.3px;
  width: 90px;
  height: 38px;
}
#cancel-button{
  position: relative;
  top:50px;
  left:330px;
  font-size: 13.3px;
  width: 90px;
  height: 38px;
}
#cancel-button:hover{
  background-color: #555555;
}
</style>

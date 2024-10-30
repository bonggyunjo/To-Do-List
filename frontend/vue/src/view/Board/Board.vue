<template>
  <div class="board-list">
    <div class="board-container">
      <div class="table-header">
        <div class="table-title" style="position: relative; left:-60px;">NO</div>
        <div class="table-title" style="position: relative; left:-100px;">ID</div>
        <div class="table-title"  style="position: relative; left:-75px;">제목</div>
        <div class="table-title">작성일</div>
      </div>
      <div style="border-top: 1px solid #e0e0e0;"></div>
      <ul>
        <li v-for="post in paginatedPosts" :key="post.postId" class="post-item">
          <div class="post-info">
            <span class="post-id" style="position: relative; left:-60px;">{{ post.postId }}</span>
            <span class="post-user" style="position: relative; left:-100px;">{{ post.nickname }}</span>
            <span class="post-title"  style="position: relative; left:-75px;">{{ post.title }}</span>
            <small class="post-date" style="font-size: 14px">{{ formatDate(post.createdAt) }}</small>
          </div>
        </li>
      </ul>
      <div class="pagination">
        <div class="page-numbers">
          <button
              v-for="page in totalPages"
              :key="page"
              :class="{ active: currentPage === page }"
              @click="goToPage(page)"
              class="page-number-button"
          >
            {{ page }}
          </button>
        </div>
      </div>
      <span class="current-page">페이지 {{ currentPage }} / {{ totalPages }}</span>
    </div>
    <router-link to="/board/write">
      <button type="submit" class="btn btn-success" id="create-button">작성하기</button>
    </router-link>
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
        this.posts = response.data.reverse();
      } catch (error) {
        console.error('게시글을 가져오는 데 오류가 발생했습니다:', error);
      }
    },
    formatDate(dateString) {
      const options = { year: 'numeric', month: 'long', day: 'numeric' };
      return new Date(dateString).toLocaleDateString('ko-KR', options);
    },
    goToPost(postId) {
      this.$router.push({ name: 'BoardDetail', params: { postId } });
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
    },
    goToPage(page) {
      this.currentPage = page;
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
}

.pagination {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 20px;
}

.current-page {
  margin: 0 15px;
  font-weight: bold;
  font-size: 16px;
  position: relative;
  top:20px;
  color: #333333;
  font-size: 14.5px;
  font-weight : normal;
}

.page-numbers {
  display: flex;
  align-items: center;
}

.page-number-button {
  background-color: #f0f0f0;
  color: #333;
  border: 1px solid #e0e0e0;
  padding: 5px 10px;
  border-radius: 5px;
  cursor: pointer;
  margin: 0 3px;
  transition: background-color 0.3s;
}

.page-number-button:hover {
  background-color: #007bff;
  color: white;
}

.page-number-button.active {
  background-color: #007bff;
  color: white;
}
#create-button {
  font-size: 14px;
  width: 100px;
  height: 43px;
  position: relative;
  left:400px;
}
</style>

<template>
  <div class="board-list">

    <div class="dropdown" style="position: relative; left:-470px; top:-15px; background-color: white; padding: 0;">
      <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false" style="background-color: white; color: #333333; font-size: 13.5px; border: none;">
        {{ selectedSort }}
      </a>
      <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
        <li class="dropdown-item" @click="sortPosts('latest')">최신 순</li>
        <li class="dropdown-item" @click="sortPosts('oldest')">오래된 순</li>
        <li class="dropdown-item" @click="sortPosts('likes')">좋아요 순</li>
      </ul>
    </div>

    <div class="board-container">
      <div class="table-header">
        <div class="table-title" style="position: relative; left:-60px;">NO</div>
        <div class="table-title" style="position: relative; left:-100px;">ID</div>
        <div class="table-title" style="position: relative; left:-75px;">제목</div>
        <div class="table-title">작성일</div>
        <div class="table-title">좋아요</div>
      </div>
      <div style="border-top: 1px solid #e0e0e0;"></div>
      <ul>
        <li v-for="post in paginatedPosts" :key="post.postId" class="post-item">
          <div class="post-info">
            <span class="post-id" style="position: relative; left:-85px;" @click="goToPost(post.postId)">{{ post.postId }}</span>
            <span class="post-user" style="position: relative; left:-150px;" @click="goToPost(post.postId)">{{ post.nickname }}</span>
            <span class="post-title" style="position: relative; left:-175px;" @click="goToPost(post.postId)">{{ post.title }}</span>
            <small class="post-date" style="font-size: 14px; position: relative; left:-150px;" @click="goToPost(post.postId)">{{ formatDate(post.createdAt) }}</small>
            <span id="like-icon" class="fas fa-thumbs-up" :class="isLike ? 'fa-thumbs-up' : 'fa-thumbs-down'"></span>
            <span class="like-count" style="margin-left: 10px;">{{ post.likeCount }}</span>
          </div>
        </li>
      </ul>

      <form class="d-flex" @submit.prevent="filterPosts">
        <input class="form-control me-2" type="text" v-model="searchQuery"
               placeholder="search..." aria-label="Search">
        <button type="submit" id="search-btn">
          <i class="fas" :class="isLike ? 'fa-search' : 'fa-search-minus'"></i>
          <!-- 필요에 따라 버튼 텍스트 추가 -->
        </button>




      </form>

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
      <span class="current-page"> {{ currentPage }} / {{ totalPages }}</span>
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
      postsPerPage: 10,
      sortOrder: 'latest',
      selectedSort: '최신 순',
      searchQuery: '',
      filteredPosts: []
    };
  },
  created() {
    this.fetchPosts();
  },
  computed: {
    totalPages() {
      return Math.ceil(this.filteredPosts.length / this.postsPerPage);
    },
    paginatedPosts() {
      const start = (this.currentPage - 1) * this.postsPerPage;
      return this.filteredPosts.slice(start, start + this.postsPerPage);
    }
  },
  methods: {
    async fetchPosts() {
      try {
        const response = await axios.get('http://localhost:8081/boards');
        this.posts = response.data.reverse();
        await Promise.all(this.posts.map(post => this.fetchLikeCount(post)));
        this.filteredPosts = this.posts;
        this.sortPosts(this.sortOrder);
      } catch (error) {
        console.error('게시글을 가져오는 데 오류가 발생했습니다:', error);
      }
    },
    async fetchLikeCount(post) {
      try {
        const response = await axios.get(`http://localhost:8081/${post.postId}/like/count`);
        post.likeCount = response.data;
      } catch (error) {
        console.error('좋아요 수를 가져오는 데 오류가 발생했습니다:', error);
      }
    },
    sortPosts(order) {
      this.selectedSort = order === 'latest' ? '최신 순' : order === 'oldest' ? '오래된 순' : '좋아요 순';
      this.sortOrder = order;

      if (order === 'latest') {
        this.filteredPosts.sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
      } else if (order === 'oldest') {
        this.filteredPosts.sort((a, b) => new Date(a.createdAt) - new Date(b.createdAt));
      } else if (order === 'likes') {
        this.filteredPosts.sort((a, b) => b.likeCount - a.likeCount); // 좋아요 수 기준 정렬
      }

      this.currentPage = 1;
    },
    filterPosts() {

      const query = this.searchQuery.toLowerCase();
      this.filteredPosts = this.posts.filter(post =>
          post.title.toLowerCase().includes(query) || post.nickname.toLowerCase().includes(query)
      );
      this.currentPage = 1;
    },
    formatDate(dateString) {
      const options = { year: 'numeric', month: 'long', day: 'numeric' };
      return new Date(dateString).toLocaleDateString('ko-KR', options);
    },
    goToPost(postId) {
      this.$router.push({ path: `/board/detail/${postId}` });
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
  grid-template-columns: 1fr 1fr 2fr 1fr 1fr;
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
  position: relative;
  top:-15px;
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
  width: 90px;
  height: 40px;
  position: relative;
  left:400px;
}
.dropdown-menu {
  min-width: 85px !important;
  text-align: left;
  font-size: 13.5px;
  padding: 1px;
  position: relative;
}
.dropdown-menu {
  padding: 0;
}

.dropdown-item {
  padding: 5px 10px;
  border-bottom: 1px solid #e0e0e0;
  cursor: pointer;
}

.dropdown-item:last-child {
  border-bottom: none;
}

.dropdown-item:hover {
  background-color: #f8f9fa;
}
#like-icon{
  position: relative;
  left:800px;
  top:-25px;
}
.like-count{
  position: relative;
  left:620px;
  top:-23px;
}
.d-flex{
  width: 500px;
  margin: auto;
}
#search-btn{
  font-size: 13px;
  width: 40px;
  height: 33px;
  font-weight: bolder;
  position: relative;
  top:2px;
  border: 2px solid #333333;
  color: #333333;
  background-color: white;
  border-radius: 3px;
}
</style>

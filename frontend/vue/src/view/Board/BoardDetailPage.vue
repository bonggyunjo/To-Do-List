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
    <div class="like-action">
  <span @click="toggleLike" class="like-icon" :class="{ 'active': isLike }">
 <i class="fas fa-thumbs-up" :class="isLike ? 'fa-thumbs-up' : 'fa-thumbs-down'"></i>
  </span>
      <span class="like-count">{{ likeCount > 0 ? likeCount : 0 }}</span>
    </div>
    <div class="favorite-action">
  <span @click="toggleFavorite" class="favorite-icon" :class="{ 'active': isFavorite }">
    <i class="fas fa-star" :class="isFavorite ? 'fa-star' : 'fa-star-o'"></i>
  </span>
      <span class="bookmark-count"> {{ bookmarkCount > 0 ? bookmarkCount : 0 }}</span>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { mapGetters } from 'vuex';

export default {
  data() {
    return {
      post: null,
      isFavorite: false,
      bookmarkCount: 0,
      isLike: false,
      likeCount: 0,
    };
  },
  computed: {
    ...mapGetters(['getUserId', 'userNickname']),
    isAuthor() {
      return this.post && this.post.nickname === this.userNickname;
    },
  },
  created() {
    const postId = this.$route.params.postId;
    this.fetchPost(postId);
    this.checkFavorite(postId);
    this.fetchBookmarkCount(postId);
    this.checkLike(postId);
    this.fetchLikeCount(postId);
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
    async deletePost(postId) {
      try {
        const userConfirmed = confirm("삭제하시겠습니까?");
        if (userConfirmed) {
          await axios.delete(`http://localhost:8081/boards/${postId}`);
          alert("삭제되었습니다.");
          this.$router.push('/board');
        }
      } catch (error) {
        console.error('게시글을 삭제하는 데 오류가 발생했습니다:', error);
      }
    },
    async toggleFavorite() {

      const userId = this.$store.getters.getUserId;
      const postId = this.post.postId;

      try {
        if (this.isFavorite) {
          await axios.delete(`http://localhost:8081/bookmark/delete/${userId}/${postId}`);
          this.isFavorite = false;
          this.bookmarkCount--;
          alert("취소하였습니다");
        } else {

          await axios.post(`http://localhost:8081/bookmark/post/${userId}/${postId}`, {
            userId: userId,
            postId: postId
          });

          this.isFavorite = true;
          this.bookmarkCount++;
          alert("즐겨찾기에 추가되었습니다.");
        }

        this.fetchLikeCount(postId);
      } catch (error) {
        console.error('즐겨찾기 처리 중 오류가 발생했습니다:', error);
      }
    },
    async toggleLike() {

      const userId = this.$store.getters.getUserId;
      const postId = this.post.postId;

      try {
        if (this.isLike) {
          await axios.delete(`http://localhost:8081/like/delete/${userId}/${postId}`);
          this.isLike = false;
          this.likeCount--; // 좋아요 수 감소
          alert("취소하였습니다");
        } else {

          await axios.post(`http://localhost:8081/like/post/${userId}/${postId}`, {
            userId: userId,
            postId: postId
          });

          this.isLike = true;
          this.likeCount++; // 좋아요 수 감소
          alert("좋아요를 눌렀습니다.");
        }

        this.fetchBookmarkCount(postId);
      } catch (error) {
        console.error('좋아요 처리 중 오류가 발생했습니다:', error);
      }
    },
    async fetchLikeCount(postId) {
      try {
        const response = await axios.get(`http://localhost:8081/${postId}/like/count`);
        this.likeCount = response.data;
      } catch (error) {
        console.error('좋아요 수를 가져오는 데 오류가 발생했습니다:', error);
      }
    },
    async checkLike(postId){
      const userId = this.$store.getters.getUserId;
      try {
        const response = await axios.get(`http://localhost:8081/like/exists/${userId}/${postId}`);
        this.isLike = response.data;
        console.log('즐겨찾기 상태:', this.isLike);
      } catch (error) {
        console.error('즐겨찾기 상태 확인 중 오류가 발생했습니다:', error);
      }
    },
    async checkFavorite(postId) {
      const userId = this.$store.getters.getUserId;
      try {
        const response = await axios.get(`http://localhost:8081/bookmark/exists/${userId}/${postId}`);
        this.isFavorite = response.data;
        console.log('즐겨찾기 상태:', this.isFavorite);
      } catch (error) {
        console.error('즐겨찾기 상태 확인 중 오류가 발생했습니다:', error);
      }
    },
    async fetchBookmarkCount(postId) {
      try {
        const response = await axios.get(`http://localhost:8081/${postId}/bookmark/count`);
        this.bookmarkCount = response.data;
      } catch (error) {
        console.error('즐겨찾기 수를 가져오는 데 오류가 발생했습니다:', error);
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
  left: 425px;
  font-size: 14px;
  top: -95px;
}

.favorite-action{
  position: relative;
  top:187px;
  left:15px;
}
.favorite-icon {
  cursor: pointer;
  font-size: 25px;
  color: #dee2e6;
}

.favorite-icon.active {
  color: #ffcc00;
}

.bookmark-count {
  margin-left: 10px;
  font-size: 14px;
  color: #333;
}

.btn {
  margin-right: 10px;
}
.like-action{
  position: relative;
  top:225px;
  left:-50px;
}
.like-icon {
  cursor: pointer;
  margin-right: 10px;
  color: #dddddd;
  font-size: 27px;
}

.like-icon.active {
  color: #333333;
}

.like-count {
  font-size: 14px;
  color: #333;
}
</style>

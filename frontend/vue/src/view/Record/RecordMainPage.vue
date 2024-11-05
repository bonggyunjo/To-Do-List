<template>
  <div class="container">
    <div class="sidebar">
      <h2 class="sidebar-title">내 페이지</h2>
      <ul class="page-list">
        <li v-for="(pageItem, index) in pages" :key="index" @click="selectPage(index)" class="page-item">
          <div class="page-item-content">
            {{ pageItem.title }}
            <span class="time">{{ formatTime(pageItem.createdDate) }}</span>
          </div>
        </li>
      </ul>
    </div>
    <div class="content-area">
      <div class="header">
        <h1 class="page-title">{{ selectedPage.title }}</h1>
        <div class="time-shared">
          <span class="time">{{ formatTime(selectedPage.createdDate) }}</span>
          <button class="share-button" @click="shareLink">링크 공유</button>
        </div>
      </div>
      <p class="card-content">{{ selectedPage.content }}</p>
      <div class="blocks-container">
        <h2 class="blocks-title">블록 목록</h2>
        <div v-for="block in selectedPage.blocks" :key="block.id" class="block" @click="goToBlockDetail(block.id)">
          <h3 class="block-title">{{ block.title || '제목 없음' }}</h3>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { mapGetters } from 'vuex';

export default {
  name: 'MainPage-Record',
  data() {
    return {
      pages: [],
      selectedPage: {}
    };
  },
  computed: {
    ...mapGetters(['getUserId']),
  },
  created() {
    this.fetchPageData();
  },
  methods: {
    async fetchPageData() {
      try {
        const response = await axios.get(`http://localhost:8081/pages/${this.getUserId}`);
        this.pages = response.data;
        if (this.pages.length > 0) {
          this.selectedPage = this.pages[0];
          await this.fetchBlocks(this.selectedPage.id);
        }
        console.log("res", response);
      } catch (error) {
        console.error('페이지 데이터를 가져오는 데 실패했습니다:', error);
      }
    },
    async fetchBlocks(pageId) {
      try {
        const response = await axios.get(`http://localhost:8081/pages/blocks/${pageId}`);
        this.selectedPage.blocks = response.data;
        console.log("res1", response);
      } catch (error) {
        console.error('블록 데이터를 가져오는 데 실패했습니다:', error);
      }
    },
    selectPage(index) {
      this.selectedPage = this.pages[index];
      this.fetchBlocks(this.selectedPage.id);
    },
    goToBlockDetail(blockId) {
      this.$router.push({ name: 'BlockDetail', params: { id: blockId } }); // 블록 상세 페이지로 이동
    },
    formatTime(date) {
      const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' };
      return new Date(date).toLocaleString(undefined, options);
    },
    shareLink() {
      const shareUrl = window.location.href;
      navigator.clipboard.writeText(shareUrl).then(() => {
        alert('링크가 클립보드에 복사되었습니다!');
      }).catch(err => {
        console.error('링크 복사 실패:', err);
      });
    }
  }
};
</script>
<style scoped>
.container {
  display: flex;
  max-width: 100%;
  height: 100vh;
  margin: 0;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.sidebar {
  flex: 0 0 20%;
  border-right: 1px solid #e7e7e7;
  padding-right: 20px;
}

.content-area {
  flex: 1;
  padding-left: 20px;
  display: flex;
  flex-direction: column;
}

.page-title {
  font-size: 28px;
  color: #333;
  margin-bottom: 20px;
}

.card-content {
  font-size: 16px;
  color: #666;
  margin-bottom: 15px;
  text-align: left;
}

.sidebar {
  flex: 0 0 20%;
  border-right: 1px solid #e7e7e7;
  padding-right: 20px;
}

.sidebar-title {
  font-size: 24px;
  color: #333;
  margin-bottom: 15px;
}

.page-list {
  list-style: none;
  padding: 0;
}

.page-item {
  cursor: pointer;
  padding: 10px;
  transition: background-color 0.3s;
}

.page-item:hover {
  background-color: #f0f0f0;
}

.page-item-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.time {
  font-size: 12px;
  color: #999;
}

.content-area {
  flex: 1;
  padding-left: 20px;
  display: flex;
  flex-direction: column;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.page-title {
  font-size: 28px;
  color: #333;
  margin-bottom: 20px;
}

.time-shared {
  display: flex;
  align-items: center;
}

.share-button {
  padding: 5px 10px;
  font-size: 14px;
  color: white;
  background-color: #28a745;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.share-button:hover {
  background-color: #218838;
}

.card-content {
  font-size: 16px;
  color: #666;
  margin-bottom: 15px;
  text-align: left;
}

.blocks-container {
  display: flex;
  flex-direction: column;
}

.blocks-title {
  font-size: 24px;
  margin-bottom: 10px;
  color: #333;
}

.block {
  padding: 10px;
  border: 1px solid #e7e7e7;
  border-radius: 5px;
  margin-bottom: 10px;
}

.block-title {
  font-weight: bold;
}
</style>

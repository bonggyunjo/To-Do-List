<template>
  <div class="container">
    <Sidebar
        :pages="pages"
        :selectPage="selectPage"
        :formatTime="formatTime"
        :goToMainPage="goToMainPage"
        @create-page="handleCreatePage"
    />
    <div class="content-area">
      <div class="header">
        <h1 class="page-title">{{ selectedPage.title }}</h1>
        <div class="time-shared">
          <span class="time">{{ formatTime(selectedPage.createdDate) }}</span>
          <button class="share-button" @click="shareLink">공유하기</button>
        </div>
      </div>
      <p class="card-content">{{ selectedPage.content }}</p>
      <div class="blocks-container">
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
import Sidebar from '@/components/Record/SideBar.vue';

export default {
  name: 'MainPage-Record',
  components: {
    Sidebar
  },
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
    goToMainPage() {
      this.$router.push(`/pages/${this.userId}`);
    },
    async fetchPageData() {
      try {
        const response = await axios.get(`http://localhost:8081/pages/${this.getUserId}`);
        this.pages = response.data;
        if (this.pages.length > 0) {
          this.selectedPage = this.pages[0];
          await this.fetchBlocks(this.selectedPage.id);
        }
      } catch (error) {
        console.error('페이지 데이터를 가져오는 데 실패했습니다:', error);
      }
    },
    async fetchBlocks(pageId) {
      try {
        const response = await axios.get(`http://localhost:8081/pages/blocks/${pageId}`);
        this.selectedPage.blocks = response.data;
      } catch (error) {
        console.error('블록 데이터를 가져오는 데 실패했습니다:', error);
      }
    },
    selectPage(index) {
      this.selectedPage = this.pages[index];
      this.fetchBlocks(this.selectedPage.id);
    },
    goToBlockDetail(blockId) {
      this.$router.push({ name: 'BlockDetail', params: { id: blockId } });
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
    },
    async handleCreatePage() {
      const newPage = {
        title: '새 페이지',
        content: '새 페이지 내용',
        createdDate: new Date().toISOString(),
        userId: this.getUserId 
      };
      try {
        const response = await axios.post(`http://localhost:8081/pages/create`, newPage);
        this.pages.push(response.data); // 새 페이지를 목록에 추가
        this.selectPage(this.pages.length - 1); // 새로 생성한 페이지를 선택
      } catch (error) {
        console.error('새 페이지 생성 중 오류 발생:', error);
      }
    }
  }
};
</script>

<style scoped>
/* 기존 스타일 유지 */
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

.content-area {
  flex: 1;
  padding-left: 20px;
  display: flex;
  flex-direction: column;
}

.page-title {
  font-size: 24px;
  color: #333;
  margin-bottom: 20px;
}

.card-content {
  font-size: 14px;
  color: #666;
  margin-bottom: 15px;
  text-align: left;
}

.time {
  font-size: 12px;
  color: #999;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.time-shared {
  display: flex;
  align-items: center;
}

.share-button {
  padding: 5px 10px;
  font-size: 15.5px;
  color: #333333;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
  background-color: #f9f9f9;
  text-decoration: underline;
  position: relative;
}

.blocks-container {
  display: flex;
  flex-direction: column;
}

.block {
  padding: 10px;
  border-radius: 5px;
  margin-bottom: 10px;
}

.block-title {
  font-weight: bold;
  text-align: left;
  text-decoration: underline;
  cursor: pointer;
  font-size: 16px;
}
</style>

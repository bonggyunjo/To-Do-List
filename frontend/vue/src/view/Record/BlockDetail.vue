<template>
  <div class="container block-detail-page">
    <Sidebar
        :pages="pages"
        :selectPage="selectPage"
        :formatTime="formatTime"
        :goToMainPage="goToMainPage"
    />
    <div class="content-area">
      <div class="header">
        <span class="block-title">{{ block.title || '제목 없음' }}</span>
        <button class="delete-button" @click="deletebt">삭제</button>
      </div>
      <div style="border-bottom: 1px solid lightgray; position:relative; top:16px;"></div>
      <p class="block-content">{{ block.content }}</p>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import axios from 'axios';
import Sidebar from '@/components/Record/SideBar.vue';

export default {
  name: 'BlockDetail',
  components: {
    Sidebar
  },
  data() {
    return {
      block: {},
      pages: [],
      selectedPage: {}
    };
  },
  computed: {
    ...mapGetters(['getUserId']),
    userId() {
      return this.getUserId;
    },
  },
  created() {
    this.fetchPageData();
    this.fetchBlockData();
  },
  methods: {
    async deletebt() {
      const blockId = this.block.id; // 현재 블록의 ID를 가져옵니다.
      try {
        const userConfirmed = confirm("이 블록을 삭제하시겠습니까?");
        if (!userConfirmed) {
          return;
        }
        await axios.delete(`http://localhost:8081/pages/blocks/${blockId}`);

        console.log("블록 삭제 성공");
        this.goBack(); // 삭제 후 이전 페이지로 돌아가기
      } catch (error) {
        console.error('블록 삭제 중 오류 발생:', error.response.data);
      }
    },
    async fetchPageData() {
      try {
        const response = await axios.get(`http://localhost:8081/pages/${this.userId}`);
        this.pages = response.data;
        if (this.pages.length > 0) {
          this.selectedPage = this.pages[0];
        }
      } catch (error) {
        console.error('페이지 데이터를 가져오는 데 실패했습니다:', error);
      }
    },
    async fetchBlockData() {
      const blockId = this.$route.params.id;
      try {
        const response = await axios.get(`http://localhost:8081/pages/block/${blockId}`);
        this.block = response.data;
        console.log("res", response);
      } catch (error) {
        console.error('블록 데이터를 가져오는 데 실패했습니다:', error);
      }
    },
    goBack() {
      this.$router.push(`/pages/${this.userId}`);
    },
    goToMainPage() {
      this.$router.push(`/pages/${this.userId}`);
    },
    selectPage(index) {
      this.selectedPage = this.pages[index];
      this.$router.push(`/pages/${this.userId}`);
    },
    formatTime(date) {
      const options = { year: 'numeric', month: 'long', day: 'numeric' };
      return new Date(date).toLocaleString(undefined, options);
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
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.content-area {
  flex: 1;
  padding-left: 20px;
  display: flex;
  flex-direction: column;
  position: relative;
  top: 30px;
  color: #333333;
}
.block-title {
  font-size: 24px;
}
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.block-content {
  font-size: 14px;
  color: #666;
  margin: 20px 0;
  text-align: left;
}

.delete-button {
  padding: 5px 10px;
  font-size: 13.5px;
  color: #333333;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
  background-color: #f9f9f9;
}
</style>

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
        <input
            type="text"
            v-model="block.title"
            @input="updateBlockTitle"
            class="block-title-input"
            placeholder="블록 제목을 입력하세요"
        />
        <div class="time-shared">
          <span class="time">{{ formatTime(selectedPage.createdDate) }}</span>
          <button class="share-button" @click="shareLink">공유</button>
          <button class="delete-button" @click="deletebt">삭제</button>
        </div>
      </div>
      <div style="border-bottom: 1px solid lightgray; position:relative; top:16px;"></div>
      <textarea
          v-model="block.content"
          @input="updateBlockContent"
      class="block-content"
      placeholder="내용을 입력하세요"
      style="width: 100%; height: 200px; resize: none;"
      ></textarea>
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
      selectedPage: {},

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
    async updateBlockTitle() {
      const blockId = this.block.id;
      try {
        const payload = {
          title: this.block.title,
          content: this.block.content,
          userId: this.userId
        };
        await axios.put(`http://localhost:8081/pages/blocks/${blockId}`, payload);
        console.log('블록 제목 업데이트 성공');
      } catch (error) {
        console.error('블록 제목 업데이트 중 오류 발생:', error.response.data);
      }
    },
    shareLink() {
      const shareUrl = window.location.href;
      navigator.clipboard.writeText(shareUrl).then(() => {
        alert('링크가 클립보드에 복사되었습니다!');
      }).catch(err => {
        console.error('링크 복사 실패:', err);
      });
    },
    async deletebt() {
      const blockId = this.block.id;
      try {
        const userConfirmed = confirm("이 블록을 삭제하시겠습니까?");
        if (!userConfirmed) {
          return;
        }
        await axios.delete(`http://localhost:8081/pages/blocks/${blockId}/permanent`);
        console.log("삭제되었습니다.");
        this.$emit('blockDeleted', blockId);
        this.goBack();
      } catch (error) {
        console.error('블록 삭제 중 오류 발생:', error.response.data);
      }
    },

    async fetchPageData() {
      try {
        const response = await axios.get(`http://localhost:8081/pages/${this.getUserId}?deleted=false`);
        this.pages = response.data.filter(page => !page.deleted); // 삭제된 페이지 필터링
        if (this.pages.length > 0) {
          this.selectedPage = { ...this.pages[0], content: this.pages[0].content || '', title: this.pages[0].title || '' };
          await this.fetchBlocks(this.selectedPage.id);
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
    async updateBlockContent() {
      const blockId = this.block.id;
      try {
        const payload = {
          title: this.block.title,
          content: this.block.content,
          userId: this.userId
        };
        await axios.put(`http://localhost:8081/pages/blocks/${blockId}`, payload);
        console.log('블록 내용 업데이트 성공');
      } catch (error) {
        console.error('블록 내용 업데이트 중 오류 발생:', error.response.data);
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

.block-title-input{
  font-size: 24px;
  color: #333333;
  border: none;
  background-color: #f9f9f9;
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
  background-color: #f9f9f9;
  border: none;
  outline: none;
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
  position: relative;
  top:11px;
}

.share-button {
  padding: 5px 10px;
  font-size: 13.5px;
  color: #333333;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
  background-color: #f9f9f9;
  position: relative;
  top:11px;
  left:10px;
}

.time {
  font-size: 12px;
  color: #999;
  position: relative;
  top:11px;
}
</style>

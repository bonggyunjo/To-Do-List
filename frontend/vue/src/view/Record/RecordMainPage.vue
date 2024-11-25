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
        <input
            type="text"
            v-model="selectedPage.title"
            class="page-title-input"
            @input="updatePageTitle"
            placeholder="제목을 입력하세요"
            maxlength="10"
        />
        <div class="time-shared">
          <span class="time">{{ formatTime(selectedPage.createdDate) }}</span>
          <button class="create-page-button" @click="createBlockPage">새 페이지</button>
          <button class="share-button" @click="shareLink">공유</button>
          <button class="delete-button" @click="deletebt">삭제</button>
        </div>
      </div>
      <div style="border-bottom: 1px solid lightgray; position:relative; top:-6px;"></div>

      <div class="blocks-container">
        <div v-for="block in selectedPage.blocks" :key="block.id" class="block" @click="goToBlockDetail(block.id)">
          <h3 class="block-title">{{ block.title || '제목 없음' }}</h3>
        </div>
      </div>

      <textarea
          ref="cardContent"
          v-model="selectedPage.content"
          class="card-content"
          @input="handleTextareaInput"
          placeholder="내용을 입력하세요"
          style="overflow: hidden; resize: none;"
      ></textarea>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { mapGetters } from 'vuex';
import Sidebar from "@/components/Record/SideBar.vue";

export default {
  name: 'MainPage-Record',
  components: {
    Sidebar
  },
  data() {
    return {
      pages: [],
      selectedPage: {},
      deletedPages: []
    };
  },
  computed: {
    ...mapGetters(['getUserId']),
  },
  created() {
    this.fetchPageData();
  },
  methods: {
    handleTextareaInput(event) {
      this.updatePageContent();
      this.adjustTextareaHeight(event);
      this.checkForCommand(event.target.value);
    },
    adjustTextareaHeight(event) {
      const textarea = event.target;
      textarea.style.height = 'auto';
      textarea.style.height = `${textarea.scrollHeight}px`;
    },
    async checkForCommand(content) {
      if (content.trim().endsWith('/페이지')) {
        await this.createBlockPage();
        this.selectedPage.content = content.slice(0, -4);
      }
    },
    async createBlockPage() {
      const newBlock = {
        title: '새 블록',
        content: '블록 내용 입력',
        userId: this.getUserId,
        id: this.selectedPage.id,
        type: 'text'
      };

      try {
        const response = await axios.post(`http://localhost:8081/pages/block/create`, newBlock);
        this.selectedPage.blocks.push(response.data);
        console.log("블록 생성 성공:", response.data);
      } catch (error) {
        console.error('블록 생성 중 오류 발생:', error.response.data);
      }
    },
    async deletebt() {
      const id = this.selectedPage.id;
      try {
        console.log("페이지 id:", this.selectedPage.id);
        const userConfirmed = confirm("삭제하시겠습니까?");
        if (!userConfirmed) {
          return;
        }
        await axios.patch(`http://localhost:8081/pages/${id}/delete`);
        console.log("페이지가 휴지통으로 이동했습니다.");
        this.pages = this.pages.filter(selectedPage => selectedPage.id !== id);

        if (this.pages.length > 0) {
          this.selectedPage = { ...this.pages[0] };
          await this.fetchBlocks(this.selectedPage.id);
        } else {
          this.selectedPage = {};
        }

        console.log("삭제 성공");
      } catch (error) {
        console.error('페이지 삭제 중 오류 발생:', error.response.data);
      }
    },
    goToMainPage() {
      this.$router.push(`/pages/${this.userId}`);
    },
    async fetchPageData() {
      try {
        const response = await axios.get(`http://localhost:8081/pages/${this.getUserId}?deleted=false`);
        this.pages = response.data;
        if (this.pages.length > 0) {
          this.selectedPage = { ...this.pages[0], content: this.pages[0].content || '', title: this.pages[0].title || '' };
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
      const options = { year: 'numeric', month: 'long', day: 'numeric'};
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
        this.pages.push(response.data);
        this.selectPage(this.pages.length - 1);
      } catch (error) {
        console.error('새 페이지 생성 중 오류 발생:', error);
      }
    },
    async updatePageTitle() {
      try {
        await axios.put(`http://localhost:8081/pages/${this.selectedPage.id}`, { title: this.selectedPage.title });
        console.log('제목 업데이트 성공');
      } catch (error) {
        console.error('제목 업데이트 중 오류 발생:', error.response.data);
      }
    },
    async updatePageContent() {
      this.adjustTextareaHeight({ target: this.$refs.cardContent });
      try {
        console.log('업데이트할 제목:', this.selectedPage.title);
        const payload = {
          title: this.selectedPage.title,
          content: this.selectedPage.content,
          userId: this.getUserId
        };
        await axios.put(`http://localhost:8081/pages/${this.selectedPage.id}`, payload);
        console.log('내용 업데이트 성공');
      } catch (error) {
        console.error('내용 업데이트 중 오류 발생:', error.response.data);
      }
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
}

.page-title-input {
  font-size: 24px;
  color: #333;
  margin-bottom: 20px;
  border: none;
  outline: none;
  background-color: #f9f9f9;
}

.card-content {
  font-size: 14px;
  color: #666;
  margin-bottom: 15px;
  text-align: left;
  background-color: #f9f9f9;
  border: none;
  outline: none;
  min-height: 150px;
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
.create-page-button{
  padding: 5px 10px;
  font-size: 13.5px;
  color: #333333;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
  background-color: #f9f9f9;
  position: relative;
  left: 20px;
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
  left: 10px;
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

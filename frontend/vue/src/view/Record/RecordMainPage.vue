<template>
  <div class="container">
    <Sidebar
        :pages="pages"
        :selectPage="selectPage"
        :formatTime="formatTime"
        :goToMainPage="goToMainPage"
        @create-page="handleCreatePage"
        @sort-pages="sortPages"
    />

    <div v-if="pages.length === 0" class="no-pages-message">
      <i class="fas fa-plus-circle" @click="handleCreatePage" id="no-pages-btn"></i>
      <p>페이지를 생성하세요</p>
    </div>

    <div v-else class="content-area">
      <div class="header">
        <input
            type="text"
            v-model="selectedPage.title"
            class="page-title-input"
            @input="updatePageTitle"
            placeholder="제목을 입력하세요"
            maxlength="10"
        />

        <div class="priority-area">
          <label for="priority-slider"><span class="priority-title">중요도</span></label>
          <div class="slider-container">
            <input
                type="range"
                id="priority-slider"
                v-model="selectedPage.priority"
                @input="updatePagePriority"
                class="priority-slider"
                min="0"
                max="10"
            />
            <span class="priority-value">{{ selectedPage.priority }}</span>
          </div>
        </div>

        <div class="progress-status-area">
          <label for="progress-status-select">진행 상태:</label>
          <select v-model="selectedPage.progressStatus" @change="updateProgressStatus" class="progress-status-select">
            <option value="PENDING">대기 중</option>
            <option value="IN_PROGRESS">진행 중</option>
            <option value="COMPLETED">완료</option>
          </select>
        </div>

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
      <div v-if="showStatusMessage" class="status-message" :style="{ color: getStatusColor(selectedPage.progressStatus), opacity: messageOpacity }">
        <i :class="getStatusIcon(selectedPage.progressStatus)"></i>
        <p>{{ getStatusMessage(selectedPage.progressStatus) }}</p>
      </div>
      <textarea
          ref="cardContent"
          v-model="selectedPage.content"
          class="card-content"
          @input="handleTextareaInput"
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
      selectedPage: {
        title: '',
        content: '',
        priority: 0,
        createdDate: '',
        blocks: []
      },
      deletedPages: [],
      showStatusMessage: false, // 상태 메시지 표시 여부
      messageOpacity: 1 // 상태 메시지의 불투명도
    };
  },
  computed: {
    ...mapGetters(['getUserId']),
  },
  created() {
    this.fetchPageData();
  },

  methods: {
    getStatusColor(status) {
      switch (status) {
        case 'PENDING':
          return 'orange';
        case 'IN_PROGRESS':
          return 'blue';
        case 'COMPLETED':
          return 'green';
        default:
          return 'black';
      }
    },
    getStatusMessage(status) {
      switch (status) {
        case 'PENDING':
          return '작업이 대기 중입니다. 진행을 시작하세요.';
        case 'IN_PROGRESS':
          return '작업이 진행 중입니다. 완료를 위해 계속하세요.';
        case 'COMPLETED':
          return '작업이 완료되었습니다. 확인하세요!';
        default:
          return '';
      }
    },
    getStatusIcon(status) {
      switch (status) {
        case 'PENDING':
          return 'fas fa-hourglass-half';
        case 'IN_PROGRESS':
          return 'fas fa-spinner fa-spin';
        case 'COMPLETED':
          return 'fas fa-check-circle';
        default:
          return '';
      }
    },
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
          this.selectedPage = {...this.pages[0]};
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
          this.selectedPage = {
            ...this.pages[0],
            content: this.pages[0].content || '',
            title: this.pages[0].title || '',
            priority: this.pages[0].priority || 0,
            progressStatus: this.pages[0].progressStatus || 'PENDING'
          };
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
      this.$router.push({name: 'BlockDetail', params: {id: blockId}});
    },
    formatTime(date) {
      const options = {year: 'numeric', month: 'long', day: 'numeric'};
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
        priority: 0,
        createdDate: new Date().toISOString(),
        userId: this.getUserId,
        progressStatus: 'PENDING'
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
        await axios.put(`http://localhost:8081/pages/${this.selectedPage.id}`, {title: this.selectedPage.title});
        console.log('제목 업데이트 성공');
      } catch (error) {
        console.error('제목 업데이트 중 오류 발생:', error.response.data);
      }
    },
    async updatePageContent() {
      this.adjustTextareaHeight({target: this.$refs.cardContent});
      try {
        const payload = {
          title: this.selectedPage.title,
          content: this.selectedPage.content,
          priority: this.selectedPage.priority,
          userId: this.getUserId
        };
        await axios.put(`http://localhost:8081/pages/${this.selectedPage.id}`, payload);
        console.log('내용 업데이트 성공');
      } catch (error) {
        console.error('내용 업데이트 중 오류 발생:', error.response.data);
      }
    },
    async updatePagePriority() {
      try {
        const payload = {
          priority: this.selectedPage.priority
        };
        await axios.patch(`http://localhost:8081/pages/${this.selectedPage.id}/priority`, payload);
        console.log('우선순위 업데이트 성공');
      } catch (error) {
        console.error('우선순위 업데이트 중 오류 발생:', error.response.data);
      }
    },
    sortPages() {
      this.pages.sort((a, b) => {
        return (a.priority || Infinity) - (b.priority || Infinity);
      });
    },
    async updateProgressStatus() {
      try {
        let message;

        switch (this.selectedPage.progressStatus) {
          case 'PENDING':
            message = "대기 중으로 전환하시겠습니까?";
            break;
          case 'IN_PROGRESS':
            message = "진행 중으로 바꾸시겠습니까?";
            break;
          case 'COMPLETED':
            message = "완료로 바꾸시겠습니까?";
            break;
          default:
            return;
        }

        const userConfirmed = confirm(message);
        if (!userConfirmed) {
          return; // 사용자가 취소할 경우 아무 작업도 하지 않음
        }

        const payload = {
          status: this.selectedPage.progressStatus
        };

        await axios.patch(`http://localhost:8081/pages/${this.selectedPage.id}/progress`, payload);
        console.log('진행 상태 업데이트 성공');

        // 페이지 상태 갱신
        const updatedPage = this.pages.find(page => page.id === this.selectedPage.id);
        if (updatedPage) {
          updatedPage.progressStatus = this.selectedPage.progressStatus; // 진행 상태 갱신
        }

        // 상태 메시지를 표시하고 2초 후에 숨김
        this.showStatusMessage = true;
        this.messageOpacity = 1;
        setTimeout(() => {
          this.showStatusMessage = false;
        }, 2000);

      } catch (error) {
        console.error('진행 상태 업데이트 중 오류 발생:', error.response.data);
      }
    },
    fadeOutMessage() {
      const fadeInterval = setInterval(() => {
        if (this.messageOpacity > 0) {
          this.messageOpacity -= 0.1; // 점차적으로 불투명도 감소
        } else {
          clearInterval(fadeInterval);
          this.showStatusMessage = false; // 사라지면 상태 메시지 숨김
        }
      }, 100); // 0.1초마다 불투명도 감소
    },
  }
};
</script>

<style scoped>
.container {
  display: flex;
  max-width: 100%;
  height: 100vh;
  margin: 0;
  background-color: white;
}

.content-area {
  flex: 1;
  padding-left: 20px;
  display: flex;
  flex-direction: column;
  position: relative;
  top: 30px;
  background-color: white;
}

.page-title-input {
  font-size: 24px;
  color: #333;
  margin-bottom: 20px;
  border: none;
  outline: none;
}

.card-content {
  font-size: 14px;
  color: #666;
  margin-bottom: 15px;
  text-align: left;
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
.slider-container {
  display: flex;
  align-items: center;
}

.priority-slider {
  -webkit-appearance: none;
  width: 100%;
  background: #ddd;
  border-radius: 5px;
  height: 5px;
  margin: 0 10px;
}

.priority-slider::-webkit-slider-thumb {
  -webkit-appearance: none;
  appearance: none;
  width: 15px;
  height: 15px;
  border-radius: 50%;
  background: #4CAF50;
  cursor: pointer;
}

.priority-slider::-moz-range-thumb {
  width: 15px;
  height: 15px;
  border-radius: 50%;
  background: #4CAF50;
  cursor: pointer;
}

.priority-value {
  font-size: 16px;
  color: #333;
}
.priority-area{
  position: relative;
  left:360px;
  top:-10px;
}
.priority-title{
  position: relative;
  left:-110px;
  top:23px;
  font-size: 13.5px;
  color: #333333;
  font-weight: bolder;
}
.progress-status-area {
  margin-top: 20px;
  display: flex;
  align-items: center;
  position: relative;
  left: 180px;
  top: -8px;
  font-size: 13.5px;
}

.progress-status-area label {
  margin-right: 10px;
  font-size: 14px;
  color: #333;
  font-size: inherit;
}

.progress-status-select {
  padding: 5px;
  border-radius: 5px;
  border: 1px solid #ddd;
  background-color: #f9f9f9;
  cursor: pointer;
  transition: border 0.3s;
}

.progress-status-select:focus {
  outline: none;
  border-color: #4CAF50;
}

.priority-area {
  position: relative;
  left: 360px;
  top: -10px;
}

.priority-title {
  position: relative;
  left: -110px;
  top: 23px;
  font-size: 13.5px;
  color: #333333;
  font-weight: bolder;
}
.no-pages-message {
  font-size: 18px;
  color: #666;
  text-align: center;
  margin: 20px 0;
  position: relative;
  margin: auto;
}

#no-pages-btn{
  cursor: pointer;
}
.progress-status-area {
  margin-top: 20px;
  display: flex;
  align-items: center;
}

.progress-status-select {
  margin-left: 10px;
  padding: 5px;
  border-radius: 5px;
  border: 1px solid #ddd;
  background-color: #f9f9f9;
  cursor: pointer;
  transition: border 0.3s;
}

.progress-status-select:focus {
  outline: none;
  border-color: #4CAF50;
}
.status-message {

  display: flex;
  align-items: center;
  transition: opacity 0.5s ease; /* 애니메이션 효과 추가 */
  margin: auto;
}

.status-message i {
  margin-right: 8px;
  font-size: 20px;
}

</style>
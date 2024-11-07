<template>
  <div class="trash-container">
    <h2>휴지통</h2>

    <div class="sections">
      <!-- 페이지 섹션 -->
      <div class="section pages-section">
        <h3>페이지</h3>
        <div v-if="deletedPages.length === 0">
          <p>휴지통이 비어 있습니다.</p>
        </div>
        <div v-for="page in deletedPages" :key="page.id" class="deleted-item">
          <div class="buttons">
            <h4>{{ page.title || '제목 없음' }}</h4>
            <button @click="restorePage(page.id)" class="restore-button">복원</button>
            <button @click="permanentDeletePage(page.id)" class="delete-button">삭제</button>
          </div>
        </div>
      </div>

      <!-- 블록 섹션 -->
      <div class="section blocks-section">
        <h3>블록</h3>
        <div v-if="deletedBlocks.length === 0">
          <p>블록이 비어 있습니다.</p>
        </div>
        <div v-for="block in deletedBlocks" :key="block.id" class="deleted-item">
          <h4>{{ block.title || '제목 없음' }}</h4>
          <div class="buttons">
            <button @click="restoreBlock(block.id)" class="restore-button">복원</button>
            <button @click="permanentDeleteBlock(block.id)" class="delete-button">삭제</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios';
import { mapGetters } from 'vuex';

export default {
  name: 'TrashPage',
  data() {
    return {
      deletedPages: [],
      deletedBlocks: [] // 블록 데이터를 추가합니다.
    };
  },
  computed: {
    ...mapGetters(['getUserId']),
  },
  created() {
    this.fetchDeletedPages();
    this.fetchDeletedBlocks(); // 삭제된 블록도 가져옵니다.
  },
  methods: {
    async fetchDeletedPages() {
      try {
        const response = await axios.get(`http://localhost:8081/pages/deleted`);
        this.deletedPages = response.data;
      } catch (error) {
        console.error('삭제된 페이지 데이터를 가져오는 데 실패했습니다:', error);
      }
    },
    async fetchDeletedBlocks() {
      try {
        const response = await axios.get(`http://localhost:8081/pages/blocks/deleted`);
        this.deletedBlocks = response.data; // 삭제된 블록 데이터를 가져옵니다.
      } catch (error) {
        console.error('삭제된 블록 데이터를 가져오는 데 실패했습니다:', error);
      }
    },
    async restorePage(pageId) {
      try {
        await axios.patch(`http://localhost:8081/pages/${pageId}/restore`);
        this.deletedPages = this.deletedPages.filter(page => page.id !== pageId);
        console.log('페이지 복원 성공');
      } catch (error) {
        console.error('페이지 복원 중 오류 발생:', error.response.data);
      }
    },
    async permanentDeletePage(pageId) {
      try {
        await axios.delete(`http://localhost:8081/pages/${pageId}/permanent`);
        this.deletedPages = this.deletedPages.filter(page => page.id !== pageId);
        console.log('페이지 완전 삭제 성공');
      } catch (error) {
        console.error('페이지 완전 삭제 중 오류 발생:', error.response.data);
      }
    },
    async restoreBlock(blockId) {
      try {
        await axios.patch(`http://localhost:8081/pages/blocks/${blockId}/restore`);
        this.deletedBlocks = this.deletedBlocks.filter(block => block.id !== blockId);
        console.log('블록 복원 성공');
      } catch (error) {
        console.error('블록 복원 중 오류 발생:', error.response.data);
      }
    },
    async permanentDeleteBlock(blockId) {
      try {
        await axios.delete(`http://localhost:8081/pages/blocks/${blockId}/permanent`);
        this.deletedBlocks = this.deletedBlocks.filter(block => block.id !== blockId);
        console.log('블록 완전 삭제 성공');
      } catch (error) {
        console.error('블록 완전 삭제 중 오류 발생:', error.response.data);
      }
    }
  }
};
</script>

<style scoped>
.trash-container {
  padding: 20px;
}

.sections {
  display: flex;
  justify-content: space-between;
}

.section {
  width: 100%;
  padding: 15px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

h3 {
  font-size: 20px;
  margin-bottom: 15px;
  color: #333;
}

.deleted-item {
  margin: 10px 0;
  padding: 10px;
  border-bottom: 1px solid #ddd;

}

.deleted-item h4 {
  margin: 0;
  font-size: 16px;
}

.buttons {
  margin-top: 10px;
}


button:hover {
  opacity: 0.9;
}

.restore-button{
  background-color: #f9f9f9;
  border: none;
  color: #333333;
  font-size: 13.5px;
}

.delete-button{
  background-color:#f9f9f9;
  color: #333333;
  border: none;
  font-size: 13.5px;

}
</style>

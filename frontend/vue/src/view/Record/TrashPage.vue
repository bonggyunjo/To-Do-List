<template>
  <div class="trash-container">
    <h4 class="trash-title">휴지통</h4>

    <span style=" color: #333333; position: relative; left:-850px;">삭제된 페이지</span>
    <div class="section pages-section">
      <div v-if="deletedPages.length === 0" class="empty-trash">
        <p>휴지통이 비어 있습니다.</p>
      </div>
      <div v-for="page in deletedPages" :key="page.id" class="deleted-item">
        <div class="item-header">
          <h4>{{ page.title || '제목 없음' }}</h4>
          <div class="buttons">
            <button @click="confirmRestorePage(page.id)" class="restore-button">복원</button>
            <button @click="confirmPermanentDeletePage(page.id)" class="delete-button">삭제</button>
          </div>
        </div>
      </div>
    </div>

    <div class="footer">
      <button @click="goBack" class="btn btn-success" id="back-btn">뒤로가기</button>
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
    };
  },
  computed: {
    ...mapGetters(['getUserId']),
  },
  created() {
    this.fetchDeletedPages();
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
    confirmRestorePage(pageId) {
      if (confirm('복원하시겠습니까?')) {
        this.restorePage(pageId);
      }
    },
    confirmPermanentDeletePage(pageId) {
      if (confirm('삭제하시겠습니까?')) {
        this.permanentDeletePage(pageId);
      }
    },
    async restorePage(pageId) {
      try {
        await axios.patch(`http://localhost:8081/pages/${pageId}/restore`);
        this.deletedPages = this.deletedPages.filter(page => page.id !== pageId);
        alert('복원되었습니다.');
      } catch (error) {
        console.error('페이지 복원 중 오류 발생:', error.response.data);
      }
    },
    async permanentDeletePage(pageId) {
      try {
        await axios.delete(`http://localhost:8081/pages/${pageId}/permanent`);
        this.deletedPages = this.deletedPages.filter(page => page.id !== pageId);
        alert('삭제되었습니다.');
      } catch (error) {
        console.error('페이지 완전 삭제 중 오류 발생:', error.response.data);
      }
    },
    goBack() {
      this.$router.go(-1);
    }
  }
};
</script>

<style scoped>
.trash-container {
  padding: 20px;
  border-radius: 8px;
}

.trash-title {
  font-weight: bold;
  text-align: center;
  color: #333;
  margin-bottom: 20px;
}

.section {

  margin-top: 20px;
  padding: 15px;
  border-radius: 5px;
  background-color: #fff;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
}

h2 {
  font-size: 18px;
  margin-bottom: 10px;
  color: #444;
}

.empty-trash {
  text-align: center;
  color: #888;
  font-style: italic;
}

.deleted-item {
  margin: 10px 0;
  padding: 10px;
  border-bottom: 1px solid #ddd;
}

.item-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.deleted-item h4 {
  margin: 0;
  font-size: 16px;
  color: #333;
}

.buttons {
  margin-top: 10px;
}

button {
  padding: 8px 12px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.restore-button {
  color: #333333;
  font-size: 14px;
  background-color: white;
}

.delete-button {
  color: #333333;
  margin-left: 8px;
  font-size: 14px;
  background-color: white;
}



.footer {
  margin-top: 20px;
  text-align: center;
}

#back-btn{
  font-size: 13.5px;
}
</style>

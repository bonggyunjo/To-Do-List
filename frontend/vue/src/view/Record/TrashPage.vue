<template>
  <div class="trash-container">
    <h2>휴지통</h2>
    <div v-if="deletedPages.length === 0">
      <p>휴지통이 비어 있습니다.</p>
    </div>
    <div v-for="page in deletedPages" :key="page.id" class="deleted-page">
      <h3>{{ page.title || '제목 없음' }}</h3>
      <button @click="restorePage(page.id)">복원</button>
      <button @click="permanentDeletePage(page.id)">완전 삭제</button>
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
      deletedPages: []
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
    }
  }
};
</script>

<style scoped>
.trash-container {
  padding: 20px;
}

.deleted-page {
  margin: 10px 0;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
</style>

<template>
  <div class="container block-detail-page">
    <Sidebar
        :pages="pages"
        :selectPage="selectPage"
        :formatTime="formatTime"
        :goToMainPage="goToMainPage"
    />
    <div class="content-area">
      <div class="block-detail">
        <h2>{{ block.title }}</h2>
        <p>{{ block.content }}</p>
        <button @click="goBack">뒤로가기</button>
      </div>
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
      const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' };
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

.block-detail {
  padding: 20px;
  border: 1px solid #e7e7e7;
  border-radius: 5px;
  background-color: #fff;
}
</style>
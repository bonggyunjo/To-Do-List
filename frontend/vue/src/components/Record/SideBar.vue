<template>
  <div class="sidebar">
    <div class="sidebar-header">
      <h2 class="sidebar-title" @click="goToMainPage">개인 페이지</h2>
      <img src="@/assets/records/write.png" width="20" height="25" class="write-button" @click="createPage"/>
    </div>
    <ul class="page-list">
      <li v-for="(pageItem, index) in pages" :key="index" @click="selectPage(index)" class="page-item">
        <div class="page-item-content">
          {{ pageItem.title }}
          <span class="time">{{ formatTime(pageItem.createdDate) }}</span>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'SideBar',
  props: {
    pages: {
      type: Array,
      required: true
    },
    selectPage: {
      type: Function,
      required: true
    },
    formatTime: {
      type: Function,
      required: true
    },
    goToMainPage: {
      type: Function,
      required: true
    }
  },
  methods: {
    createPage() {
      this.$emit('create-page'); // 부모 컴포넌트에 이벤트를 발행
    }
  }
};
</script>

<style scoped>
.sidebar {
  flex: 0 0 16%;
  border-right: 1px solid #e7e7e7;
  padding-right: 20px;
  position: relative;
  top: 50px;
}

.sidebar-header {
  display: flex; /* Flexbox 사용 */
  justify-content: space-between; /* 공간을 균등하게 분배 */
  align-items: center; /* 수직 중앙 정렬 */
  margin-bottom: 15px; /* 제목과 페이지 리스트 간의 간격 */
}

.sidebar-title {
  font-size: 16px;
  color: #333;
  cursor: pointer;
  font-weight: bolder;
  position: relative;
  top:-30px;
}

.write-button {
  cursor: pointer;
  margin-left: 10px; /* 아이콘과 제목 간의 간격 */
  position: relative;
  top:-30px
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
  font-size: 15px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.time {
  font-size: 12px;
  color: #999;
}
</style>

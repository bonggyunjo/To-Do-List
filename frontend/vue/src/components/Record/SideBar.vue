<template>
  <div class="sidebar">
    <h2 class="sidebar-title" @click="goToMainPage">개인 페이지</h2>
    <button class="create-page-button" @click="createPage">새 페이지 만들기</button>
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
/* 기존 스타일 유지 */
.sidebar {
  flex: 0 0 18%;
  border-right: 1px solid #e7e7e7;
  padding-right: 20px;
  position: relative;
  top:50px;
}

.sidebar-title {
  font-size: 16px;
  color: #333;
  margin-bottom: 15px;
  cursor: pointer;
  position: absolute;
  top:-50px;
  left:10px;
  font-weight: bolder;
}

.create-page-button {
  margin-bottom: 15px; /* 버튼과 리스트 간의 간격 조정 */
  padding: 8px 12px;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.create-page-button:hover {
  background-color: #218838; /* 버튼 호버 시 색상 변경 */
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
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.time {
  font-size: 12px;
  color: #999;
}
</style>

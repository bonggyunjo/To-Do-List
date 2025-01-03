<template>
  <div class="sidebar">
    <div class="sidebar-header">
      <h2 class="sidebar-title" @click="goToMainPage">개인 페이지</h2>
      <div class="header-actions">
        <img src="@/assets/records/write.png" width="20" height="25" class="write-button" @click="createPage"/>
        <router-link to="/trash" class="trash">
          <img src="@/assets/records/trash.png" width="31" height="28" class="trash"/>
        </router-link>
      </div>
    </div>

    <ul class="page-list">
      <li v-for="(pageItem, index) in pages" :key="index" @click="selectPage(index)" class="page-item">
        <div class="page-item-content">
          {{ pageItem.title }}
          <span class="time">{{ formatTime(pageItem.createdDate) }}</span>
        </div>
      </li>
    </ul>
    <span class="sort-button" @click="sortPages">중요도</span> <!-- 정렬 버튼 추가 -->
    <router-link to="/" class="go-back">뒤로가기</router-link>
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
      this.$emit('create-page');
    },
    sortPages() {
      this.$emit('sort-pages'); // 부모에게 정렬 이벤트를 전달
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
  background-color: white;
}

.sidebar-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
}

.sidebar-title {
  font-size: 16px;
  color: #333;
  cursor: pointer;
  font-weight: bolder;
}

.header-actions {
  display: flex;
  align-items: center;
}

.write-button {
  cursor: pointer;
  margin-left: 10px;
}

.sort-button {
  margin-left: 10px; /* 정렬 버튼의 위치 조정 */
  cursor: pointer;
  color: #333333;
  font-size: 14px;
  font-weight: bolder;
  position: relative;
  left:-10px;
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

.trash {
  text-decoration: none;
  color: #333333;
  font-size: 15px;
  margin-left: 5px;
}

.go-back {
  text-decoration: none;
  color: #333333;
  font-size: 14px;
  margin-top: 20px;
  font-weight: bolder;
}

</style>

<template>
  <div class="sidebar">
    <div class="sidebar-header"><h2 class="sidebar-title" @click="goToMainPage">개인 페이지</h2>
      <div class="header-actions">
        <i class="fas fa-plus-circle"  width="20" height="25"  @click="createPage" id="write-button"></i>
        <router-link to="/trash"><i class="fas fa-trash" width="31" height="28" id="trash"></i></router-link>


      </div>
    </div>

    <h3 class="section-title">목록</h3>
    <ul class="page-list">
      <li v-for="(pageItem, index) in pages" :key="index" @click="selectPage(index)" class="page-item">
        <div class="page-item-content">
          <span class="page-title">{{ pageItem.title }}</span>
          <span class="time">{{ formatTime(pageItem.createdDate) }}</span>
          <i v-if="pageItem.progressStatus === 'COMPLETED'" class="fas fa-check" aria-hidden="true"></i>
        </div>

      </li>
    </ul>

    <router-link to="/" class="go-back">뒤로가기</router-link>
  </div>
</template>
<script>
export default {
  name: 'SideBar',
  props: {
    pages: {type: Array, required: true},
    selectPage: {type: Function, required: true},
    formatTime: {type: Function, required: true},
    goToMainPage: {type: Function, required: true}
  },
  methods: {
    createPage() {
      this.$emit('create-page');
    }, sortPages() {
      this.$emit('sort-pages');
    }
  }
}; </script>
<style scoped> .sidebar {
  flex: 0 0 16%;
  border-right: 1px solid #e7e7e7;
  padding: 20px;
  position: relative;
  background-color: #f9f9f9;
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
}

.sidebar-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.sidebar-title {
  font-size: 18px;
  color: #333;
  cursor: pointer;
  font-weight: bolder;
}

.header-actions {
  display: flex;
  align-items: center;
}

#write-button {
  cursor: pointer;
  margin-left: 10px;
}

.section-title {
  font-size: 15px;
  color: #333;
  margin: 15px 0 5px;
  position: relative;
  left: -105px;
  color: #333333;
  font-weight: bolder;
}

.page-list {
  list-style: none;
  padding: 0;
}

.page-item {
  cursor: pointer;
  padding: 12px;
  transition: background-color 0.3s, transform 0.2s;
}

.page-item:hover {
  background-color: #e7f5e7;
  transform: scale(1.02);
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

#trash {
  text-decoration: none;
  color: #333333;
  font-size: 15px;
  margin-left: 15px;
}

.go-back {
  text-decoration: none;
  color: darkgreen;
  font-size: 14px;
  margin-top: 20px;
  font-weight: bolder;
}
.page-item-content i {
  color: green; /* 체크 아이콘 색상 */
  margin-left: 5px; /* 제목과 체크 아이콘 사이의 간격 */
}
</style>
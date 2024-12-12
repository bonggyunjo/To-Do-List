<template>
  <div id="todo-app" class="todo-app">
  <MyPageSideBar></MyPageSideBar>
  <MyBoardList></MyBoardList>
  </div>
</template>

<script>

import MyPageSideBar from "@/components/MyPage/MyPageSideBar.vue";
import MyBoardList from "@/components/MyPage/MyBoardList.vue";

export default {
  name: 'MyPageBoard',
  components: {MyBoardList, MyPageSideBar},
}
</script>


<style scoped>
.todo-app {
  font-family: 'Arial', sans-serif;
  max-width: 1300px;
  margin: auto;
  padding: 20px;
  background-color: #ffffff;
  border-radius: 10px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  position: relative;
  top: 50px;
  display: flex;
  height: 550px;
}
</style>

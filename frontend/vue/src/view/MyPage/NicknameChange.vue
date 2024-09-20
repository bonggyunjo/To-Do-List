<template>
  <div id="todo-app" class="todo-app">
    <MyPageSideBar></MyPageSideBar>
    <ChangeNickname></ChangeNickname>
  </div>
</template>

<script>

import MyPageSideBar from "@/components/MyPage/MyPageSideBar.vue";
import ChangeNickname from "@/components/MyPage/ChangeNickname.vue";
export default {
  name: 'NicknameChange',
  components: { MyPageSideBar, ChangeNickname},
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
<template>
    <div id="todo-app" class="todo-app">
    <MyPageSideBar></MyPageSideBar>
    <AccountSettings></AccountSettings>
  </div>
</template>


<script>
import MyPageSideBar from "@/components/MyPage/MyPageSideBar.vue";
import AccountSettings from "@/components/MyPage/AccountSettings.vue"; // 변경된 파일 이름
export default {
  name: 'TodoApp',
  components: {AccountSettings, MyPageSideBar},
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
  display: flex; /* Flexbox를 사용하여 섹션을 나란히 배치 */
  height: 550px;
}
</style>

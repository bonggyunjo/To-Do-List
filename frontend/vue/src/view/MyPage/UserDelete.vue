<template>
  <div id="todo-app" class="todo-app">
  <MyPageSideBar></MyPageSideBar>
    <DeleteUser></DeleteUser>
  </div>
</template>


<script>
import MyPageSideBar from "@/components/MyPage/MyPageSideBar.vue";
import DeleteUser from "@/components/MyPage/DeleteUser.vue";

export default {
  name: 'UserDelete',
  components: {MyPageSideBar,DeleteUser},
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
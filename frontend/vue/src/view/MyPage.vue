<template>
  <div id="todo-app" class="todo-app">
    <div class="sidebar">
      <h6 class="header-title">설정</h6>
    </div>

    <main class="content">
      <h6 class="content-title">계정</h6>
      <div class="content-line"></div>

    </main>
  </div>
</template>


<script>
export default {
  name: 'TodoApp',
  data() {
    return {
      newTask: '',
      tasks: [],
    };
  },
  computed: {
    completedTasks() {
      return this.tasks.filter(task => task.completed);
    }
  },
  methods: {
    addTask() {
      if (this.newTask.trim()) {
        this.tasks.push({ id: Date.now(), name: this.newTask, completed: false });
        this.newTask = '';
      }
    },
    removeTask(id) {
      this.tasks = this.tasks.filter(task => task.id !== id);
    },
    editProfile() {
      alert('프로필 수정 페이지로 이동합니다.');
    }
  }
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
}

.sidebar {
  width: 20%; /* 왼쪽 섹션 너비 */
  padding: 20px;
}

.content {
  width: 80%; /* 오른쪽 섹션 너비 */
  position: relative;
  top:20px;
}

.section {
  margin-bottom: 20px; /* 섹션 간의 여백 */
}

.header-title {
  margin-bottom: 10px;
  font-weight: bolder;
  color: black;
  font-size: 18px;
  position: relative;
  left:-110px;
  top:-20px;
}
.content-title{
  margin-bottom: 10px;
  font-weight: bolder;
  color: black;
  font-size: 15px;
  position: relative;
  left:-490px;
  top:-20px;
}
.content-line{
  border-top: 1px solid lightgrey;
  width: 100%;
}
</style>

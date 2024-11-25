<template>
  <div class="loading-spinner">
    <div class="spinner"></div>
    <p>로그아웃..</p>
  </div>
</template>

<script>
export default {
  name: 'LoadingSpinner',
}
</script>

<style scoped>
.loading-spinner {
  top: 60%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  z-index: 1000;
  color: #333;
  font-family: 'Arial', sans-serif;
  position: relative;
}

.spinner {
  width: 50px;
  height: 50px;
  border: 8px solid rgba(255, 255, 255, 0.3);
  border-top: 8px solid #3498db;
  border-radius: 50%;
  animation: spin 1s linear infinite;
  margin-bottom: 10px;
}

@keyframes spin {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}

p {
  font-size: 16px;
  margin: 0;
}
</style>

<template>
  <div id="home">
    <div class="left">
      <!-- 첫번째-->
      <span class="first-title">마음을 비우세요</span>
      <div class="first-content">
        <span>머릿속에서 작업을 꺼내는</span>
        <span style="position: relative; left:-12px;">가장 신속한 방법입니다.</span>
      </div>
      <div class="task-list-message">
        <p class="task-intro">우선순위를 정하고, 필요한 작업을 관리해보세요.</p>
        <p class="task-benefit">목록화함으로써 스트레스를 줄이고, 효율적으로 목표를 달성할 수 있습니다.</p>
      </div>
      <br><br><br><br><br><br><br><br><br>
      <!-- 두번째 -->
      <span class="second-title">중요한 것에 집중하세요</span>
      <div class="second-content">
        <span>효과적으로 목표를 달성하고,</span>
        <span style="position: relative; left:-53px;">스트레스를 줄이세요.</span>
      </div>
      <div class="task-list-message2">
        <p class="task-intro2">이를 통해 목표를 명확히 하고, 불필요한 혼란을 줄이며,</p>
        <p class="task-benefit2">더 나은 결정을 내릴 수 있습니다.</p>
      </div>
      <br><br><br><br><br><br><br><br><br>
      <!-- 세번째 -->
      <span class="third-title">이야기를 펼쳐보세요.</span>
      <div class="third-content">
        <span>당신의 생각과 경험을 나누는</span>
        <span style="position: relative; left:-126px;">공간입니다.</span>
      </div>
      <div class="task-list-message3">
        <p class="task-intro3">다양한 주제로 자유롭게 소통하며 </p>
        <p class="task-benefit3">서로의 이야기를 듣고 배워보세요.</p>
      </div>
    </div>
    <!-- 세번째 -->

    <div class="right">
      <p>오른쪽 콘텐츠</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomePage'
}
</script>

<style>
body {
  font-family: 'Arial', sans-serif;
  margin: 0;
  padding: 20px;
  background-color: #f5f5f5; 
}

#home {
  display: flex; /* Flexbox를 사용하여 수평 정렬 */
  justify-content: space-between;
  align-items: flex-start;
  max-width: 1800px;
  margin: 0 auto;
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.first-title {
  font-weight: bold;
  color: #DAA520;
  font-size: 16px;
  position: relative;
  left: -200px;
}

.first-content {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
  font-size: 33px;
  line-height: 1.5;
  color: #333333;
  font-weight: bolder;
  position: relative;
  left: -70px;
}

.left {
  width: 60%;
}

.right {
  width: 35%;
  padding: 10px;
  background-color: #e9e9e9;
  border-radius: 8px;
}

.task-list-message {
  margin-top: 20px;
}

.task-intro {
  font-size: 20px;
  color: #666666;
  line-height: 1.6;
  margin: 10px 0;
  position: relative;
  left:-45px;
}

.task-benefit {
  font-size: 18px;
  color: #666666;
  line-height: 1.6;
  margin: 10px 0;
  position: relative;
  left:39px;
}
.second-title{
  font-weight: bolder;
  color : lightslategrey;
  font-size: 16px;
  position: relative;
  left: -175px;
}
.second-content{
  display: flex;
  flex-direction: column;
  margin-top: 20px;
  font-size: 33px;
  line-height: 1.5;
  color: #333333;
  font-weight: bolder;
  position: relative;
  left: -50px;
}
.task-list-message2 {
  margin-top: 20px;
}

.task-intro2 {
  font-size: 20px;
  color: #666666;
  line-height: 1.6;
  margin: 10px 0;
  position: relative;
  left:-16px;
}

.task-benefit2 {
  font-size: 18px;
  color: #666666;
  line-height: 1.6;
  margin: 10px 0;
  position: relative;
  left:-128px;
}
.third-title{
  font-weight: bolder;
  color: green;
  font-size: 16px;
  position: relative;
  left: -182px;
}
.third-content{
  display: flex;
  flex-direction: column;
  margin-top: 20px;
  font-size: 33px;
  line-height: 1.5;
  color: #333333;
  font-weight: bolder;
  position: relative;
  left: -50px;
}
.task-list-message3 {
  margin-top: 20px;
}

.task-intro3 {
  font-size: 20px;
  color: #666666;
  line-height: 1.6;
  margin: 10px 0;
  position: relative;
  left:-111px;
}

.task-benefit3 {
  font-size: 18px;
  color: #666666;
  line-height: 1.6;
  margin: 10px 0;
  position: relative;
  left:-124px;
}
</style>

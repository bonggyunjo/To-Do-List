<template>
  <div>
    <div class="thank-you-message">
      <span v-if="showFirst" class="fade-in slide-down" style="position: relative; left:-510px;">To-Do-List를</span><br>
      <span v-if="showSecond" class="fade-in slide-down">이렇게 사용해 주셔서</span><br>
      <span v-if="showThird" class="fade-in slide-down" style="position: relative; left:-433px;">진심으로 감사드립니다.</span>
    </div>
    <img src="../assets/main_image.png" width="700" height="380" style="position: relative; top:-300px; left:350px; border-radius: 5px;" class="modern-image fade-in">
    <div class="main-contents">
    <p class="intro-text" style="position: relative; left:-36px;">1️⃣️  ️당신의 일상적인 작업을 효율적으로 관리하세요.</p>
    <p class="intro-text" style="position: relative; left:-36px;">2️⃣ 편하게 작업을 추가하고 우선순위를 정해보세요.</p>
    <p class="intro-text">3️⃣ 스트레스를 줄이고 목표를 효과적으로 달성할 수 있습니다.</p>
    </div>
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
      <div class="first-image">
      <img src="@/assets/main/page.png"  class="first-image" width="700" height="380">
      </div>
      <div class="third-image">
        <img src="@/assets/main/pagem.png"  class="first-image" width="447" height="474">
      </div>

      <div class="second-image">
        <img src="@/assets/main/board.png"  class="second-image" width="712" height="350">
      </div>
    </div>
  </div>
  </div>
</template>

<script>
export default {
  name: 'HomePage',
  data() {
    return {
      showFirst: false,
      showSecond: false,
      showThird: false,
    };
  },
  mounted() {
    this.animateText();
  },
  methods: {
    animateText() {
      setTimeout(() => {
        this.showFirst = true;
      }, 500);
      setTimeout(() => {
        this.showSecond = true;
      }, 1500);
      setTimeout(() => {
        this.showThird = true;
      }, 3500);
    }
  }
}
</script>

<style>
.thank-you-message {
  text-align: center;
  margin-bottom: 20px;
  font-family: 'Arial', sans-serif;
  font-size: 24px;
  color: #333;
  line-height: 1.5;
  height: 500px;
}

.fade-in {
  animation: fadeIn 1s forwards;
  display: inline-block;
  position: relative;
  left:-450px;
  top:180px;
  font-weight: bolder;
  color: #333333;
  font-size: 35px;
  padding: 2px;
  opacity: 0;
  animation-delay: 1.6s; /* 2초 후에 애니메이션 시작 */
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 0.9;
  }
}

body {
  font-family: 'Arial', sans-serif;
  margin: 0;
  padding: 20px;
  background-color: #f5f5f5;
}
#home {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  max-width: 1800px;
  margin: 0 auto;
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  position: relative;
  top:-150px;
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
  height: 1500px;
}

.right {
  width: 35%;
  padding: 10px;
  border-radius: 8px;
  position: relative;
  left:-100px;
  height: 1000px;
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
  top:50px;
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
  top:50px;
}
.task-list-message2 {
  margin-top: 20px;
  position: relative;
  top:50px;
}

.task-intro2 {
  font-size: 20px;
  color: #666666;
  line-height: 1.6;
  margin: 10px 0;
  position: relative;
  left:-16px;
  top:50px;
}

.task-benefit2 {
  font-size: 18px;
  color: #666666;
  line-height: 1.6;
  margin: 10px 0;
  position: relative;
  left:-128px;
  top:50px;
}
.third-title{
  font-weight: bolder;
  color: green;
  font-size: 16px;
  position: relative;
  left: -182px;
  top:100px;
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
  top:100px;
}
.task-list-message3 {
  margin-top: 20px;
  position: relative;
  top:100px;
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
.modern-image {
  width: 100%;
  max-width: 550px;
  height: auto;
  border-radius: 10px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease;
}

.modern-image:hover {
  transform: scale(1.05);
}
.slide-down {
  animation: slideDown 0.5s forwards;
}

@keyframes slideDown {
  from {
    transform: translateY(-20px);
    opacity: 0;
  }
  to {
    transform: translateY(0);
    opacity: 1;
  }
}
.intro-text {
  font-size: 16px;
  color: #555;
  line-height: 1.6;
  margin: 20px 0;
  text-align: center;
}

.intro-text:hover{
  transform: scale(1.03);
}

.main-contents{
  position: relative;
  left:-395px;
  top:-400px;
}
.first-image{
  position: relative;
  left:-40px;
  top:-5px;
  border-radius: 10px;

}
.second-image
{
  position: relative;
  left:-42px;
  top:95px;
}
.third-image{
  position: relative;
  left:-42px;
  top:60px;
}
</style>

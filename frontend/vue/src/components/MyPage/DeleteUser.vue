<template>
  <main class="content">
    <h6 class="content-title">계정 삭제</h6>
    <span style="font-size: 13px; position: relative; left:-390px; top:-25px; color: lightslategrey">To-Do-List를 이용해 주셔서 감사합니다!</span>
    <div class="content-line"></div>

    <span class="content-for-delete-ismain">
        <span style="position: fixed; left:576px;"><span style="font-weight: bolder; font-size: 18px;">{{userNickname}}</span>님 <span class="bounce">계정을 삭제하시겠습니까 ?</span></span>
      <br>
      계정 삭제 시 모든 데이터는 즉시 삭제되며 복구할 수 없습니다.
      <br>
      <span style="position: relative; left:99px;">이로 인해 이전에 저장한 작업, 프로젝트, 설정 등이 모두 사라지니 신중하게 결정하시기 바랍니다.</span>
      <br>
      <span style="position: relative; left:-104.5px;">필요시 백업을 고려하세요.</span>
      <br>
      <span style="position: relative; left:-30px; top:60px; color: green; font-weight: bolder; font-size: 13px;">위의 사항을 확인하였으며, 이에 동의합니다.</span>
      </span>
    <div class="form-check" style="position: relative; top:46px; left:-3px;">
      <input class="form-check-input" type="checkbox" value="" id="defaultCheck1"  v-model="isChecked">
      <label class="form-check-label" for="defaultCheck1">
      </label>
    </div>
    <button class="btn btn-outline-danger" id="delete-user-button"  :disabled="!isChecked" @click="deleteUser">계정 삭제</button>
  </main>
</template>

<script>
import axios from 'axios';
import {mapGetters} from "vuex";
export default {
  name : 'DeleteUser',
  data() {
    return {
      isChecked: false,
    };
  },
  computed: {
    ...mapGetters(['userNickname']),
    nickname(){
      return this.userNickname;
    }
  },
  methods:{
    async deleteUser() {
      if (!this.isChecked) {
        alert('계정 삭제를 위해 동의해야 합니다.');
        return;
      }

      try {
        const userId = this.$store.getters.getUserId; // Vuex에서 사용자 ID 가져오기
        const res = await axios.delete(`http://localhost:8081/user/delete`, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`
          },
          params: {
            userId: userId // userId를 쿼리 파라미터로 추가
          }
        });
        console.log("res",res);

        this.$store.commit('clearUserData');
        localStorage.removeItem('token');

        alert('계정이 삭제되었습니다.');
        this.$router.push('/');
      } catch (error) {
        console.error('계정 삭제 실패:', error);
        alert('계정 삭제에 실패했습니다. 다시 시도해 주세요.');
      }
    }
  }
}
</script>

<style scoped>
.content {
  width: 80%;
  position: relative;
  top:20px;
}

.content-title{
  margin-bottom: 10px;
  font-weight: bolder;
  color: black;
  font-size: 15px;
  position: relative;
  left:-474px;
  top:-20px;
}

.content-line{
  border-top: 1px solid lightgrey;
  width: 100%;
}

.content-for-delete-ismain{
  position: relative;
  top:10px;
  left:-325px;
  font-size: 13px;
  color: #333333;
}

#delete-user-button{
  position: relative;
  top:270px;
  left:460px;
}

.bounce {
  display: inline-block;
  animation: bounce 0.5s infinite;
}

.bounce {
  display: inline-block;
  animation: bounce 0.5s 4;
}

@keyframes bounce {
  0%, 100% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(-10px);
  }
}
</style>
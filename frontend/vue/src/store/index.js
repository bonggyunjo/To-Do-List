// store/index.js
import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

export default new Vuex.Store({
    plugins: [
        createPersistedState({
            key: 'my-app-state', // localStorage에 저장될 키 이름
            paths: ['userId', 'nickname'] // 저장할 state 속성 지정
        })
    ],
    state: {
        userId: '',
        nickname: '',
    },
    getters: {
        isLogin(state) {
            return state.userId !== '';
        },
        userNickname(state) { // 닉네임을 가져오는 getter 추가
            return state.nickname;
        },
    },
    mutations: {
        setUserId(state, userId) {
            state.userId = userId;
        },
        setUserNickname(state, nickname) {
            state.nickname = nickname;
        },
        clearUsername(state) {
            state.nickname = '';
            state.userId = '';
        }
    }
});

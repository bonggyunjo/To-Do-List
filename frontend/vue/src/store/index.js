import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import axios from "axios";

Vue.use(Vuex);

export default new Vuex.Store({
    plugins: [
        createPersistedState({
            key: 'my-app-state',
            paths: ['userId', 'nickname', 'isLogin','intro']
        })
    ],
    state: {
        userId: '',
        nickname: '',
        isLogin: false,
        intro: ''
    },
    getters: {
        isLogin(state) {
            return state.isLogin;
        },
        userNickname(state) {
            return state.nickname;
        },
        getUserId(state) {
            return state.userId;
        },
        getIntro(state){
            return state.intro;
        }
    },
    mutations: {
        setUserId(state, userId) {
            state.userId = userId;
            state.isLogin = true;
        },
        setUserNickname(state, nickname) {
            state.nickname = nickname;
        },
        clearUsername(state) {
            state.nickname = '';
            state.userId = '';
            state.isLogin = false;
        },
        clearUserData(state) {
            state.userId = '';
            state.nickname = '';
            state.isLogin = false; // 로그인 상태 초기화
            state.intro = '';
        },
        updateNickname(state, newNickname) {
            state.nickname = newNickname; // 닉네임 업데이트
        },
        setIntro(state, intro) { // intro mutation 추가
            state.intro = intro;
        },
    },
    actions: {
        async login({ commit }, { userId, password }) {
            try {

                const res = await axios.post('http://localhost:8081/login', { userId, password });
                if (res.data && res.data.token) {
                    localStorage.setItem('token', res.data.token); // JWT 토큰 저장
                    commit('setUserId', res.data.userId);
                    commit('setUserNickname', res.data.nickname);
                    commit('setIntro',res.data.intro);
                } else {
                    throw new Error('로그인 실패: 토큰이 없습니다.');
                }
            } catch (error) {
                console.error(error);

            }
        },
        logout({ commit }) {
            commit('clearUsername');
            localStorage.removeItem('token'); // JWT 토큰 삭제
        }
    }
});

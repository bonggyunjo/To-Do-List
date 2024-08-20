import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import axios from "axios";

Vue.use(Vuex);

export default new Vuex.Store({
    plugins: [
        createPersistedState({
            key: 'my-app-state',
            paths: ['userId', 'nickname', 'isLogin'] // 로그인 상태도 저장
        })
    ],
    state: {
        userId: '',
        nickname: '',
        isLogin: false,
    },
    getters: {
        isLogin(state) {
            return state.isLogin; // 직접 isLogin 상태 반환
        },
        userNickname(state) {
            return state.nickname;
        },
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
        }
    },
    actions: {
        async login({ commit }, { userId, password }) {
            try {
                // API 호출 예시 (axios 등을 사용하여)
                const res = await axios.post('http://localhost:8081/login', { userId, password });
                if (res.data && res.data.token) {
                    localStorage.setItem('token', res.data.token); // JWT 토큰 저장
                    commit('setUserId', res.data.userId);
                    commit('setUserNickname', res.data.nickname); // 응답에 nickname이 포함되어 있다고 가정
                } else {
                    throw new Error('로그인 실패: 토큰이 없습니다.');
                }
            } catch (error) {
                console.error(error);
                // 실패 시 추가적인 처리 필요 (예: 사용자에게 알림)
            }
        },
        logout({ commit }) {
            commit('clearUsername');
            localStorage.removeItem('token'); // JWT 토큰 삭제
        }
    }
});

import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import axios from "axios";

Vue.use(Vuex);

export default new Vuex.Store({
    plugins: [
        createPersistedState({
            key: 'my-app-state',
            paths: ['userId', 'nickname', 'isLogin']
        })
    ],
    state: {
        userId: '',
        nickname: '',
        isLogin: false,
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
        }
    },
    actions: {
        async login({ commit }, { userId, password }) {
            try {

                const res = await axios.post('http://localhost:8081/login', { userId, password });
                if (res.data && res.data.token) {
                    localStorage.setItem('token', res.data.token); // JWT 토큰 저장
                    commit('setUserId', res.data.userId);
                    commit('setUserNickname', res.data.nickname);
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

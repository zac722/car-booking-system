import { defineStore } from 'pinia'

export const useAuthStore = defineStore('auth', {
  state: () => ({
    user: null
  }),
  actions: {
    login(userData) {
      if (userData) {
        this.user = userData
        localStorage.setItem('user', JSON.stringify(this.user))
      }
    },
    logout() {
      this.user = null
      localStorage.removeItem('user')
    },
    checkAuth() {
      const user = localStorage.getItem('user')
      if (user) {
        this.user = JSON.parse(user)
      }
    }
  },
  getters: {
    isAuthenticated: (state) => !!state.user
  }
})

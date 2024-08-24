<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <v-layout>
    <v-app-bar app>
      <v-app-bar-title class="app-title">Car Rental App</v-app-bar-title>
      <v-spacer></v-spacer>
      <v-btn to="/" variant="outlined" color="primary">Home</v-btn>
      <!-- <v-btn to="/book">Book a Car</v-btn> -->
      <v-btn to="/manage-bookings">Manage Bookings</v-btn>
      <v-btn @click="logout" v-if="isAuthenticated">Logout</v-btn>
    </v-app-bar>

    <v-main class="d-flex align-center justify-center">
      <router-view></router-view>
    </v-main>
  </v-layout>
</template>

<script>
import { useAuthStore } from '@/stores/auth'

export default {
  name: 'App',
  computed: {
    isAuthenticated() {
      const authStore = useAuthStore()
      return authStore.isAuthenticated
    }
  },
  methods: {
    logout() {
      const authStore = useAuthStore()
      authStore.logout()
      this.$router.push('/login')
    }
  },
  created() {
    const authStore = useAuthStore()
    authStore.checkAuth()
  }
}
</script>

<style scoped>
@media (max-width: 600px) {
  .app-title {
    display: none;
  }
}
</style>

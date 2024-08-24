<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12" sm="8" md="6">
        <v-card>
          <v-card-title>Login</v-card-title>
          <v-card-text>
            <v-form @submit.prevent="login">
              <v-text-field v-model="username" label="Username" required></v-text-field>
              <v-text-field
                v-model="password"
                label="Password"
                type="password"
                required
              ></v-text-field>
              <v-btn type="submit" color="primary" block>Login</v-btn>
            </v-form>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
    <v-row justify="center" class="mt-4">
      <v-col cols="12" sm="8" md="6">
        <p class="text-center">
          Don't have an account?
          <router-link to="/signup">Sign up here</router-link>
        </p>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import apiService from '@/services/apiService'
import { useAuthStore } from '@/stores/auth'

export default {
  name: 'LoginView',
  data() {
    return {
      username: '',
      password: ''
    }
  },
  methods: {
    async login() {
      const authStore = useAuthStore()
      try {
        const login = { username: this.username, password: this.password }
        const response = await apiService.post('/signin', login)
        authStore.login(response.data)
        this.$router.push('/')
      } catch (error) {
        console.error('Login failed:', error)
        alert('Login failed!')
      }
    }
  }
}
</script>

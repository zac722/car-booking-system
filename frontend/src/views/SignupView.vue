<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12" sm="8" md="6">
        <v-card>
          <v-card-title>Sign Up</v-card-title>
          <v-card-text>
            <v-form @submit.prevent="signup">
              <v-text-field v-model="username" label="Username" required></v-text-field>
              <v-text-field v-model="email" label="Email" type="email" required></v-text-field>
              <v-text-field
                v-model="password"
                label="Password"
                type="password"
                required
              ></v-text-field>
              <v-text-field
                v-model="confirmPassword"
                label="Confirm Password"
                type="password"
                required
              ></v-text-field>
              <v-btn type="submit" color="primary" block>Sign Up</v-btn>
            </v-form>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
    <v-row justify="center" class="mt-4">
      <v-col cols="12" sm="8" md="6">
        <p class="text-center">
          Have an account already?
          <router-link to="/login">Login here</router-link>
        </p>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import apiService from '@/services/apiService'
import { useAuthStore } from '@/stores/auth'

export default {
  name: 'SignupView',
  data() {
    return {
      username: '',
      email: '',
      password: '',
      confirmPassword: ''
    }
  },
  methods: {
    async signup() {
      if (this.password !== this.confirmPassword) {
        alert("Passwords don't match")
        return
      }
      const authStore = useAuthStore()
      try {
        const user = { username: this.username, email: this.email, password: this.password }
        const response = await apiService.post('/signup', user)
        if (response.status === 200) {
          alert('Sign up successful! Please login.')
          this.$router.push('/login')
        } else {
          alert('An error occurred.')
          console.log(response)
        }
      } catch (error) {
        alert(error.response.data)
        console.error('Signup failed:', error)
      }
    }
  }
}
</script>

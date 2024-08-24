import axios from 'axios'
import { useAuthStore } from '@/stores/auth'

const apiClient = axios.create({
  baseURL: 'http://localhost:8080/',
  headers: {
    'Content-Type': 'application/json'
  },
  timeout: 10000
})

apiClient.interceptors.request.use((config) => {
  const authStore = useAuthStore()
  const token = authStore.user?.token
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

export default {
  get(resource) {
    return apiClient.get(resource)
  },

  post(resource, data) {
    return apiClient.post(resource, data)
  },

  put(resource, data) {
    return apiClient.put(resource, data)
  },

  delete(resource) {
    return apiClient.delete(resource)
  }
}

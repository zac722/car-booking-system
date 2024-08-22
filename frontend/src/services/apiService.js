import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://localhost:8080/api', // Replace with your API's base URL
  headers: {
    'Content-Type': 'application/json',
    // Add any additional headers here, like Authorization if needed
  },
  timeout: 10000, // Optional: Set a timeout for requests (in milliseconds)
});

export default {
  get(resource) {
    return apiClient.get(resource);
  },

  post(resource, data) {
    return apiClient.post(resource, data);
  },

  put(resource, data) {
    return apiClient.put(resource, data);
  },

  delete(resource) {
    return apiClient.delete(resource);
  },

};

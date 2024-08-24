<template>
  <v-container>
    <h1>Manage Bookings</h1>
    <v-data-table :headers="headers" :items="bookings" class="elevation-1">
      <template v-slot:item.carModel="{ item }">
        {{ item.car.model.brand }} {{ item.car.model.model }}
      </template>
      <template v-slot:item.actions="{ item }">
        <v-btn small color="primary" @click="viewBooking(item.id)"> View </v-btn>
      </template>
    </v-data-table>
  </v-container>
</template>

<script>
import { useAuthStore } from '@/stores/auth'
import apiService from '@/services/apiService'

export default {
  name: 'BookingManageView',
  data() {
    return {
      headers: [
        { title: 'Booking No', key: 'id' },
        { title: 'Car', key: 'carModel' },
        { title: 'Start Date', key: 'startDate' },
        { title: 'End Date', key: 'endDate' },
        { title: 'Status', key: 'status' },
        { title: '', key: 'actions', sortable: false, align: 'end' }
      ],
      bookings: []
    }
  },
  methods: {
    async fetchData() {
      const authStore = useAuthStore()
      try {
        const response = await apiService.get(`/api/users/${authStore.user.userid}/bookings`)
        this.bookings = response.data
      } catch (error) {
        console.error('Error fetching data:', error)
        alert(error.response.data)
      }
    },
    viewBooking(bookingId) {
      this.$router.push({ name: 'bookingDetails', params: { id: bookingId } })
    }
  },
  mounted() {
    this.fetchData()
  }
}
</script>

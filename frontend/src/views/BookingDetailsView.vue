<template>
  <v-container>
    <h1>Booking Details</h1>
    <booking-details :booking="booking" :showActions="showActions" @cancel="cancelBooking" />
    <v-snackbar v-model="snackbar" :timeout="timeout" color="success">
      {{ text }}
    </v-snackbar>
  </v-container>
</template>

<script>
import { useBookingStore } from '@/stores/booking'
import BookingDetails from '@/components/BookingDetails.vue'
import apiService from '@/services/apiService'

export default {
  name: 'BookingDetailsView',
  components: {
    BookingDetails
  },
  data() {
    return {
      bookingId: null,
      booking: null,
      snackbar: false,
      text: 'Booking Cancelled!',
      timeout: 2000
    }
  },
  computed: {
    showActions() {
      return this.booking != null && this.booking.status != 'Cancelled'
    }
  },
  created() {
    this.bookingId = parseInt(this.$route.params.id)
  },
  methods: {
    async fetchData() {
      try {
        const response = await apiService.get(`/api/bookings/${this.bookingId}`)
        this.booking = response.data
      } catch (error) {
        console.error('Error fetching data:', error)
        alert(error.response.data)
      }
    },
    async cancelBooking() {
      try {
        const response = await apiService.put(`/api/bookings/${this.bookingId}/cancel`)
        this.booking.status = 'Cancelled'
        this.snackbar = true
      } catch (error) {
        console.error('Error on put data:', error)
        alert(error.response.data)
      }
      //   this.$router.push({ name: 'manageBookings' })
    }
  },
  mounted() {
    this.fetchData()
  }
}
</script>

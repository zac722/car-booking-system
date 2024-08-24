<template>
  <v-container>
    <h1>Booking Confirmation</h1>
    <booking-details :booking="booking" />
    <v-btn class="my-3" block disabled>Payment</v-btn>
    <v-btn class="my-3" block @click="comfirm">Comfirm</v-btn>
  </v-container>
</template>

<script setup>
import { useDate } from 'vuetify'

const date = useDate()
</script>

<script>
import { useBookingStore } from '@/stores/booking'
import BookingDetails from '@/components/BookingDetails.vue'

export default {
  name: 'BookingConfirmationView',
  components: {
    BookingDetails
  },
  data() {
    return {
      booking: null
    }
  },
  created() {
    const bookingStore = useBookingStore()
    this.booking = bookingStore.currentBooking
  },
  methods: {
    comfirm() {
      const bookingStore = useBookingStore()
      bookingStore.clearCurrentBooking()
      this.$router.push('/')
    }
  }
}
</script>

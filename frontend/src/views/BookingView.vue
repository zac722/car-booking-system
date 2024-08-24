<template>
  <v-container>
    <h1>Book a Car</h1>
    <v-form @submit.prevent="submitBooking">
      <v-select
        v-model="selectedCar"
        :items="cars"
        :item-title="carTitle"
        item-value="id"
        label="Select a car"
        required
        @update:model-value="onCarSelect"
      ></v-select>
      <v-date-input
        v-model="dateRange"
        label="Select range"
        multiple="range"
        prepend-icon=""
        prepend-inner-icon="$calendar"
        :min="minDate"
        :max="maxDate"
        :allowed-dates="allowedDates"
        :disabled="!selectedCar"
      ></v-date-input>
      <v-btn type="submit" color="primary" class="mt-4">Book Now</v-btn>
    </v-form>
  </v-container>
</template>

<script>
import { useRoute } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import { useBookingStore } from '@/stores/booking'
import apiService from '@/services/apiService'
export default {
  data() {
    return {
      cars: [],
      selectedCar: null,
      dateRange: [],
      availableDates: {}
    }
  },
  computed: {
    minDate() {
      const tomorrow = new Date()
      tomorrow.setDate(tomorrow.getDate() + 1)
      return tomorrow.toISOString().split('T')[0]
    },
    maxDate() {
      const threeMonthsLater = new Date()
      threeMonthsLater.setMonth(threeMonthsLater.getMonth() + 3)
      return threeMonthsLater.toISOString().split('T')[0]
    },
    allowedDates() {
      if (!this.selectedCar) {
        return []
      } else {
        return this.availableDates[this.selectedCar]
      }
    }
  },
  methods: {
    async fetchCars() {
      try {
        const response = await apiService.get('/api/cars')
        this.cars = response.data
      } catch (error) {
        console.error('Error fetching cars:', error)
      }
    },
    async fetchAvailableDates() {
      try {
        const response = await apiService.get(
          `/api/car-models/available-dates?startDate=${this.minDate}&endDate=${this.maxDate}`
        )
        this.availableDates = response.data
      } catch (error) {
        console.error('Error fetching available dates:', error)
      }
    },
    carTitle(item) {
      return `${item.brand} ${item.model}`
    },
    onCarSelect() {
      this.dateRange = []
    },
    async submitBooking() {
      const authStore = useAuthStore()
      const bookingStore = useBookingStore()

      const startDate = this.dateRange[0]
      const endDate = this.dateRange[this.dateRange.length - 1]

      const booking = {
        userId: authStore.user.userid,
        carModelId: this.selectedCar,
        startDate,
        endDate
      }

      try {
        const response = await apiService.post('/api/bookings', booking)
        bookingStore.setBooking(response.data)
        this.$router.push({ name: 'confirmation' })
      } catch (error) {
        console.error('Error booking cars:', error)
        alert(error.response.data)
      }
    }
  },
  mounted() {
    const carId = parseInt(this.$route.params.id)
    if (carId) {
      this.selectedCar = carId
    }
    this.fetchCars()
    this.fetchAvailableDates()
  }
}
</script>

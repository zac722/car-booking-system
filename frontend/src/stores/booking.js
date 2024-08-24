import { defineStore } from 'pinia'

export const useBookingStore = defineStore('booking', {
  state: () => ({
    currentBooking: null
  }),
  actions: {
    setBooking(booking) {
      this.currentBooking = booking
    },
    clearCurrentBooking() {
      this.currentBooking = null
    }
  }
})

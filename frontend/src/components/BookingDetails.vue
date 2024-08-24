<template>
  <v-card v-if="booking">
    <v-card-title>Booking Details</v-card-title>
    <v-card-text>
      <v-list>
        <v-list-item>
          <v-list-item-title>Booking No:</v-list-item-title>
          <v-list-item-subtitle>{{ booking.id }}</v-list-item-subtitle>
        </v-list-item>
        <v-list-item>
          <v-list-item-title>Car Model:</v-list-item-title>
          <v-list-item-subtitle
            >{{ booking.car.model.brand }} {{ booking.car.model.model }}</v-list-item-subtitle
          >
        </v-list-item>
        <v-list-item>
          <v-list-item-title>Vehicle No:</v-list-item-title>
          <v-list-item-subtitle>{{ booking.car.licensePlate }}</v-list-item-subtitle>
        </v-list-item>
        <v-list-item>
          <v-list-item-title>Rental Rate:</v-list-item-title>
          <v-list-item-subtitle>${{ booking.car.model.dailyRate }} per day</v-list-item-subtitle>
        </v-list-item>
        <v-list-item>
          <v-list-item-title>Rental Period:</v-list-item-title>
          <v-list-item-subtitle>
            {{ date.format(booking.startDate, 'keyboardDate') }} to
            {{ date.format(booking.endDate, 'keyboardDate') }}
          </v-list-item-subtitle>
        </v-list-item>
        <v-list-item>
          <v-list-item-title>Total Price:</v-list-item-title>
          <v-list-item-subtitle>${{ booking.totalPrice }}</v-list-item-subtitle>
        </v-list-item>
        <v-list-item>
          <v-list-item-title>Status:</v-list-item-title>
          <v-list-item-subtitle>{{ booking.status }}</v-list-item-subtitle>
        </v-list-item>
      </v-list>
    </v-card-text>
    <v-card-actions v-if="showActions">
      <!-- <v-btn color="primary" @click="$emit('edit')">Edit</v-btn> -->
      <v-btn color="error" @click="$emit('cancel')">Cancel</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script setup>
import { useDate } from 'vuetify'

const date = useDate()
</script>

<script>
export default {
  name: 'BookingDetails',
  props: {
    booking: {
      type: Object,
      required: true
    },
    showActions: {
      type: Boolean,
      default: false
    }
  },
  emits: ['edit', 'cancel']
}
</script>

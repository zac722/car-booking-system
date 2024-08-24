<template>
  <v-container>
    <h1>Available Cars</h1>
    <!-- Filters -->
    <v-row class="mb-4">
      <v-col cols="12" sm="6" md="3">
        <v-select v-model="selectedCarType" :items="carTypes" label="Car Type" clearable></v-select>
      </v-col>
      <v-col cols="12" sm="6" md="6">
        <v-range-slider
          v-model="priceRange"
          :min="minPrice"
          :max="maxPrice"
          :step="5"
          label="Price Range"
          class="align-center"
        >
          <template v-slot:prepend>
            <v-text-field
              :value="priceRange[0]"
              class="mt-0 pt-0"
              hide-details
              single-line
              type="number"
              style="width: 70px"
              @change="$set(priceRange, 0, $event)"
            ></v-text-field>
          </template>
          <template v-slot:append>
            <v-text-field
              :value="priceRange[1]"
              class="mt-0 pt-0"
              hide-details
              single-line
              type="number"
              style="width: 70px"
              @change="$set(priceRange, 1, $event)"
            ></v-text-field>
          </template>
        </v-range-slider>
      </v-col>
    </v-row>

    <!-- Car list -->
    <v-row>
      <v-col v-for="car in filteredCars" :key="car.id" cols="12" sm="6" md="4" lg="3">
        <v-card height="100%">
          <v-img :src="car.imageUrl" height="200" cover></v-img>
          <v-card-title>{{ car.brand }} {{ car.model }}</v-card-title>
          <v-card-text>
            <p class="text-h6">Price per day: ${{ car.dailyRate }}</p>
          </v-card-text>
          <v-card-actions>
            <v-btn color="primary" :to="{ name: 'book', params: { id: car.id } }" block>
              Book Now
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import apiService from '@/services/apiService'
export default {
  name: 'Home',
  data() {
    return {
      cars: [],
      selectedCarType: null,
      priceRange: [0, 100]
    }
  },
  computed: {
    carTypes() {
      return [...new Set(this.cars.map((car) => car.type))]
    },
    minPrice() {
      return Math.min(...this.cars.map((car) => car.dailyRate))
    },
    maxPrice() {
      return Math.max(...this.cars.map((car) => car.dailyRate))
    },
    filteredCars() {
      return this.cars.filter((car) => {
        const matchesType = !this.selectedCarType || car.type === this.selectedCarType
        const matchesPrice =
          car.dailyRate >= this.priceRange[0] && car.dailyRate <= this.priceRange[1]
        return matchesType && matchesPrice
      })
    }
  },
  mounted() {
    apiService
      .get('/api/cars')
      .then((response) => {
        console.log(response)
        this.cars = response.data
        this.priceRange = [this.minPrice, this.maxPrice]
        console.log(this.cars)
      })
      .catch((error) => {
        console.error('Error fetching data:', error)
      })
  }
}
</script>

<template>
  <v-container>
    <h1>Car Inventory Management</h1>
    <div class="mb-4">
      <CarModelsTable />
    </div>
    <div>
      <CarsTable />
    </div>
  </v-container>
</template>

<script>
import CarModelsTable from '@/components/CarModelsTable.vue'
import CarsTable from '@/components/CarsTable.vue'

export default {
  name: 'AdminView',
  components: {
    CarModelsTable,
    CarsTable
  }
}
</script>

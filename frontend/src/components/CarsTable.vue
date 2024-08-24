<template>
  <v-card>
    <v-data-table :headers="carHeaders" :items="cars" class="elevation-1">
      <template v-slot:top>
        <v-toolbar flat>
          <v-toolbar-title>Cars</v-toolbar-title>
          <v-divider class="mx-4" inset vertical></v-divider>
          <v-spacer></v-spacer>
          <v-dialog v-model="carDialog" max-width="500px">
            <template v-slot:activator="{ props }">
              <v-btn color="primary" dark class="mb-2" v-bind="props"> Add Car </v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="text-h5">{{ editingCar ? 'Edit' : 'Add' }} Car</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12">
                      <v-select
                        v-model="editedCar.model"
                        :items="carModels"
                        :item-title="carModelTitle"
                        item-value="id"
                        label="Car Model"
                      ></v-select>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        v-model="editedCar.licensePlate"
                        label="License Plate"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field v-model="editedCar.color" label="Color"></v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue-darken-1" variant="text" @click="closeCarDialog">Cancel</v-btn>
                <v-btn color="blue-darken-1" variant="text" @click="saveCar">Save</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-toolbar>
      </template>
      <template v-slot:item.model="{ item }">
        {{ getCarModelName(item.model.id) }}
      </template>
      <template v-slot:item.actions="{ item }">
        <v-icon size="small" class="me-2" @click="editCar(item)">mdi-pencil</v-icon>
        <v-icon size="small" @click="deleteCar(item)">mdi-delete</v-icon>
      </template>
    </v-data-table>
  </v-card>
  <v-snackbar v-model="snackbar.show" :color="snackbar.color" :timeout="3000">
    {{ snackbar.message }}
  </v-snackbar>
</template>
<script>
import apiService from '@/services/apiService'
export default {
  data() {
    return {
      carModels: [],
      cars: [],
      editedCar: {
        model: null,
        licensePlate: '',
        color: ''
      },
      editingCar: false,
      carDialog: false,
      carHeaders: [
        { title: 'Model', key: 'model' },
        { title: 'License Plate', key: 'licensePlate' },
        { title: 'Color', key: 'color' },
        { title: 'Actions', key: 'actions', sortable: false }
      ],
      snackbar: {
        show: false,
        message: '',
        color: 'success'
      }
    }
  },
  methods: {
    showSnackbar(message, color = 'success') {
      this.snackbar.message = message
      this.snackbar.color = color
      this.snackbar.show = true
    },
    async fetchCarModels() {
      try {
        const response = await apiService.get('/api/inventory/models')
        this.carModels = response.data
      } catch (error) {
        console.error('Error fetching car models:', error)
        this.showSnackbar('Failed to fetch car models', 'error')
      }
    },
    async fetchCars() {
      try {
        const response = await apiService.get('/api/inventory/cars')
        this.cars = response.data
      } catch (error) {
        console.error('Error fetching cars:', error)
        this.showSnackbar('Failed to fetch cars', 'error')
      }
    },
    async saveCar() {
      const request = {
        modelId:
          typeof this.editedCar.model === 'object' ? this.editedCar.model.id : this.editedCar.model,
        licensePlate: this.editedCar.licensePlate,
        color: this.editedCar.color
      }
      try {
        if (this.editingCar) {
          await apiService.put(`/api/inventory/cars/${this.editedCar.id}`, request)
          this.showSnackbar('Car updated successfully')
        } else {
          await apiService.post('/api/inventory/cars', request)
          this.showSnackbar('Car added successfully')
        }
        this.closeCarDialog()
        this.fetchCars()
      } catch (error) {
        console.error('Error saving car:', error)
        this.showSnackbar('Failed to save car', 'error')
      }
    },
    editCar(item) {
      this.editingCar = true
      this.editedCar = { ...item }
      this.carDialog = true
    },
    async deleteCar(item) {
      try {
        await apiService.delete(`/api/inventory/cars/${item.id}`)
        this.showSnackbar('Car deleted successfully')
        this.fetchCars()
      } catch (error) {
        console.error('Error deleting car:', error)
        this.showSnackbar('Failed to delete car', 'error')
      }
    },
    closeCarDialog() {
      this.carDialog = false
      this.$nextTick(() => {
        this.editedCar = {
          model: null,
          licensePlate: '',
          color: ''
        }
        this.editingCar = false
      })
    },
    carModelTitle(item) {
      return `${item.brand} ${item.model}`
    },
    getCarModelName(modelId) {
      const model = this.carModels.find((cm) => cm.id === modelId)
      return model ? `${model.brand} ${model.model}` : 'Unknown'
    }
  },
  mounted() {
    this.fetchCarModels()
    this.fetchCars()
  }
}
</script>

<template>
  <v-card>
    <v-data-table :headers="carModelHeaders" :items="carModels" class="elevation-1">
      <template v-slot:top>
        <v-toolbar flat>
          <v-toolbar-title>Car Models</v-toolbar-title>
          <v-divider class="mx-4" inset vertical></v-divider>
          <v-spacer></v-spacer>
          <v-dialog v-model="carModelDialog" max-width="500px">
            <template v-slot:activator="{ props }">
              <v-btn color="primary" dark class="mb-2" v-bind="props"> Add Car Model </v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="text-h5">{{ editingCarModel ? 'Edit' : 'Add' }} Car Model</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12">
                      <v-text-field v-model="editedCarModel.brand" label="Brand"></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field v-model="editedCarModel.model" label="Model"></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field v-model="editedCarModel.type" label="Type"></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        v-model="editedCarModel.seats"
                        label="Seats"
                        type="number"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        v-model="editedCarModel.dailyRate"
                        label="Daily Rate"
                        type="number"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        v-model="editedCarModel.imageUrl"
                        label="Image URL"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue-darken-1" variant="text" @click="closeCarModelDialog"
                  >Cancel</v-btn
                >
                <v-btn color="blue-darken-1" variant="text" @click="saveCarModel">Save</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-toolbar>
      </template>
      <template v-slot:item.actions="{ item }">
        <v-icon size="small" class="me-2" @click="editCarModel(item)">mdi-pencil</v-icon>
        <v-icon size="small" @click="deleteCarModel(item)">mdi-delete</v-icon>
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
      editedCarModel: {
        brand: '',
        model: '',
        type: '',
        seats: null,
        dailyRate: null,
        imageUrl: ''
      },
      editingCarModel: false,
      carModelDialog: false,
      carModelHeaders: [
        { title: 'Brand', key: 'brand' },
        { title: 'Model', key: 'model' },
        { title: 'Type', key: 'type' },
        { title: 'Seats', key: 'seats' },
        { title: 'Daily Rate', key: 'dailyRate' },
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
    async saveCarModel() {
      try {
        if (this.editingCarModel) {
          await apiService.put(
            `/api/inventory/models/${this.editedCarModel.id}`,
            this.editedCarModel
          )
          this.showSnackbar('Car model updated successfully')
        } else {
          await apiService.post('/api/inventory/models', this.editedCarModel)
          this.showSnackbar('Car model added successfully')
        }
        this.closeCarModelDialog()
        this.fetchCarModels()
      } catch (error) {
        console.error('Error saving car model:', error)
        this.showSnackbar('Failed to save car model', 'error')
      }
    },
    editCarModel(item) {
      this.editingCarModel = true
      this.editedCarModel = { ...item }
      this.carModelDialog = true
    },
    async deleteCarModel(item) {
      try {
        await apiService.delete(`/api/inventory/models/${item.id}`)
        this.showSnackbar('Car model deleted successfully')
        this.fetchCarModels()
      } catch (error) {
        console.error('Error deleting car model:', error)
        this.showSnackbar('Failed to delete car model', 'error')
      }
    },
    closeCarModelDialog() {
      this.carModelDialog = false
      this.$nextTick(() => {
        this.editedCarModel = {
          brand: '',
          model: '',
          type: '',
          seats: null,
          dailyRate: null,
          imageUrl: ''
        }
        this.editingCarModel = false
      })
    }
  },
  mounted() {
    this.fetchCarModels()
  }
}
</script>

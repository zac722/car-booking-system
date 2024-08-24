import 'vuetify/styles'
import '@mdi/font/css/materialdesignicons.css'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import { md3 } from 'vuetify/blueprints'
import { VDateInput } from 'vuetify/labs/VDateInput'

export default createVuetify({
  components: {
    ...components,
    VDateInput
  },
  directives,
  blueprint: md3,
  icons: {
    defaultSet: 'mdi'
  },
  defaults: {
    VTextField: { variant: 'solo' },
    VSelect: { variant: 'solo' },
    VDateInput: { variant: 'solo' }
  }
})

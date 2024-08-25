import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import SignupView from '../views/SignupView.vue'
import { useAuthStore } from '@/stores/auth'
import NotFoundView from '@/views/NotFoundView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/signup',
      name: 'signup',
      component: SignupView
    },
    {
      path: '/book/:id?',
      name: 'book',
      component: () => import('../views/BookingView.vue'),
      meta: { requiresAuth: true }
    },
    {
      path: '/confirmation',
      name: 'confirmation',
      component: () => import('../views/BookingConfirmationView.vue'),
      meta: { requiresAuth: true }
    },
    {
      path: '/manage-bookings',
      name: 'manageBookings',
      component: () => import('../views/BookingManageView.vue'),
      meta: { requiresAuth: true }
    },
    {
      path: '/booking/:id',
      name: 'bookingDetails',
      component: () => import('../views/BookingDetailsView.vue'),
      meta: { requiresAuth: true }
    },
    {
      path: '/admin',
      name: 'admin',
      component: () => import('../views/AdminView.vue'),
      meta: { requiresAuth: true, requiresAdmin: true }
    },
    {
      path: '/:pathMatch(.*)*',
      name: 'not-found',
      component: NotFoundView
    }
  ]
})

router.beforeEach((to, from, next) => {
  const authStore = useAuthStore()
  if (to.meta.requiresAdmin && !authStore.isAdmin) {
    to.matched[0].components.default = NotFoundView
    next()
  } else if (to.meta.requiresAuth && !authStore.isAuthenticated) {
    next('/login')
  } else {
    next()
  }
})

export default router

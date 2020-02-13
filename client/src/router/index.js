import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Artists from '../views/Artists.vue'
import Stages from '../views/Stages.vue'

Vue.use(VueRouter)

const routes = [
  { 
    path: '/', 
    redirect: '/home' 
  },
  {
    path: '/home',
    name: 'home',
    component: Home
  },
  {
    path: '/artists',
    name: 'artists',
    component: Artists
  },
  {
    path: '/stages',
    name: 'stages',
    component: Stages
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

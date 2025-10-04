import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import Timeline from '../views/Timeline.vue'
import Photoalbum from '../views/Photoalbum.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '/timeline',
    name: 'Timeline',
    component: Timeline
  },
  {
    path: '/photoalbum',
    name: 'Photoalbum',
    component: Photoalbum
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
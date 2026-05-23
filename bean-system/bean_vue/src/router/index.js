import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Layout from '../views/Layout.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', redirect: '/login' },
    { path: '/login', component: Login },
    {
      path: '/home',
      component: Layout,
      redirect: '/home/dashboard',
      children: [
        { path: 'dashboard', component: () => import('../views/Home.vue') }, // 首页
        { path: 'profile', component: () => import('../views/Profile.vue') }, // 个人中心
        { path: 'disease', component: () => import('../views/Disease.vue') }, // 患病信息管理
        { path: 'treatment', component: () => import('../views/Treatment.vue') } // 治疗信息管理
      ]
    }
  ]
})

export default router

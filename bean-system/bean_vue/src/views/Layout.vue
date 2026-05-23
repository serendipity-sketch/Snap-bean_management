<template>
  <el-container class="layout-container">
    <el-aside width="220px" class="aside">
      <div class="logo">🌱 油豆角管理系统</div>
      <el-menu
        router
        :default-active="$route.path"
        background-color="#304156"
        text-color="#bfcbd9"
        active-text-color="#409EFF"
        style="border: none"
      >
        <el-menu-item index="/home/dashboard">
          <el-icon><HomeFilled /></el-icon> <span>首页</span>
        </el-menu-item>
        <el-menu-item index="/home/disease">
          <el-icon><FirstAidKit /></el-icon> <span>患病信息管理</span>
        </el-menu-item>
        <el-menu-item index="/home/treatment">
          <el-icon><Medal /></el-icon> <span>治疗信息管理</span>
        </el-menu-item>
        <el-menu-item index="/home/profile">
          <el-icon><User /></el-icon> <span>个人中心</span>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header class="header">
        <div class="breadcrumb">欢迎使用病害管理系统</div>
        <div class="user-info">
          <span>{{ user.nickname || user.username }}</span>
          <el-button link type="danger" @click="logout" style="margin-left: 15px">退出</el-button>
        </div>
      </el-header>
      <el-main class="main-content">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter()
const user = JSON.parse(localStorage.getItem('user') || '{}')

const logout = () => {
  localStorage.clear()
  router.push('/login')
}
</script>

<style scoped>
.layout-container { height: 100vh; }
.aside { background-color: #304156; color: white; }
.logo { height: 60px; line-height: 60px; text-align: center; font-size: 18px; font-weight: bold; background: #2b2f3a; }
.header { background: #fff; border-bottom: 1px solid #ddd; display: flex; align-items: center; justify-content: space-between; padding: 0 20px; }
.main-content { background: #f0f2f5; padding: 20px; }
</style>

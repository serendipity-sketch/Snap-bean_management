<template>
  <div class="login-wrapper">
    <div class="login-box">
      <h2>油豆角病害管理系统</h2>
      <el-tabs v-model="activeTab" stretch>
        <el-tab-pane label="登录" name="login">
          <el-form :model="loginForm" ref="loginRef" label-width="0">
            <el-form-item>
              <el-input v-model="loginForm.username" placeholder="用户名" prefix-icon="User" />
            </el-form-item>
            <el-form-item>
              <el-input v-model="loginForm.password" type="password" placeholder="密码" prefix-icon="Lock" />
            </el-form-item>
            <el-button type="primary" class="w-100" @click="handleLogin">登录</el-button>
          </el-form>
        </el-tab-pane>

        <el-tab-pane label="注册" name="register">
          <el-form :model="regForm" label-width="0">
            <el-form-item>
              <el-input v-model="regForm.username" placeholder="设置用户名" prefix-icon="User" />
            </el-form-item>
            <el-form-item>
              <el-input v-model="regForm.nickname" placeholder="设置昵称" prefix-icon="Edit" />
            </el-form-item>
            <el-form-item>
              <el-input v-model="regForm.password" type="password" placeholder="设置密码" prefix-icon="Lock" />
            </el-form-item>
            <el-button type="success" class="w-100" @click="handleRegister">注册</el-button>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'

const router = useRouter()
const activeTab = ref('login')
const loginForm = ref({ username: '', password: '' })
const regForm = ref({ username: '', password: '', nickname: '' })

// 统一请求前缀
const API_URL = 'http://localhost:9090'

const handleLogin = async () => {
  if(!loginForm.value.username || !loginForm.value.password) return ElMessage.warning('请输入完整')
  try {
    const res = await axios.post(`${API_URL}/user/login`, loginForm.value)
    if(res.data.code === 200) {
      ElMessage.success('登录成功')
      localStorage.setItem('user', JSON.stringify(res.data.data))
      router.push('/home')
    } else {
      ElMessage.error(res.data.msg)
    }
  } catch(e) { ElMessage.error('服务器连接失败') }
}

const handleRegister = async () => {
  if(!regForm.value.username || !regForm.value.password) return ElMessage.warning('请输入完整')
  try {
    const res = await axios.post(`${API_URL}/user/register`, regForm.value)
    if(res.data.code === 200) {
      ElMessage.success('注册成功，请登录')
      activeTab.value = 'login'
    } else {
      ElMessage.error(res.data.msg)
    }
  } catch(e) { ElMessage.error('服务器连接失败') }
}
</script>

<style scoped>
.login-wrapper { height: 100vh; display: flex; justify-content: center; align-items: center; background-color: #f0f2f5; background-image: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%); }
.login-box { width: 400px; padding: 40px; background: white; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); }
h2 { text-align: center; margin-bottom: 30px; color: #333; }
.w-100 { width: 100%; margin-top: 20px; }
</style>

<template>
  <el-card style="max-width: 500px; margin: 0 auto;">
    <template #header>
      <div class="clearfix">
        <span>个人信息设置</span>
      </div>
    </template>
    <el-form :model="userForm" label-width="80px">
      <el-form-item label="用户名">
        <el-input v-model="userForm.username" disabled></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="userForm.nickname"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="userForm.password" type="password" show-password placeholder="不修改请留空"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updateProfile">保存修改</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'

const userForm = ref(JSON.parse(localStorage.getItem('user') || '{}'))

const updateProfile = async () => {
  const res = await axios.put('http://localhost:9090/user/update', userForm.value)
  if(res.data.code === 200){
    ElMessage.success('修改成功')
    localStorage.setItem('user', JSON.stringify(userForm.value))
  } else {
    ElMessage.error('修改失败')
  }
}
</script>

<template>
  <div>
    <el-card>
      <div class="search-bar">
        <el-input v-model="searchName" placeholder="搜索病害名称" style="width: 200px; margin-right: 10px" clearable @clear="loadData" />
        <el-button type="primary" @click="loadData">查询</el-button>
        <el-button type="success" @click="openDialog({})">新增病害</el-button>
      </div>

      <el-table :data="tableData" border style="width: 100%; margin-top: 20px">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="name" label="病害名称" width="150" />
        <el-table-column prop="symptoms" label="病症描述" />
        <el-table-column prop="createTime" label="录入时间" width="180" />
        <el-table-column label="操作" width="180">
          <template #default="scope">
            <el-button size="small" @click="openDialog(scope.row)">编辑</el-button>
            <el-button size="small" type="danger" @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 弹窗 -->
    <el-dialog v-model="showDialog" :title="form.id ? '编辑' : '新增'">
      <el-form :model="form" label-width="80px">
        <el-form-item label="病害名称">
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="症状描述">
          <el-input v-model="form.symptoms" type="textarea" :rows="3" />
        </el-form-item>
        <el-form-item label="图片链接">
          <el-input v-model="form.imageUrl" placeholder="http://..." />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="showDialog = false">取消</el-button>
        <el-button type="primary" @click="save">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { ElMessage, ElMessageBox } from 'element-plus'

const API = 'http://localhost:9090/api/disease'
const tableData = ref([])
const searchName = ref('')
const showDialog = ref(false)
const form = ref({})

const loadData = async () => {
  const res = await axios.get(`${API}/list`, { params: { name: searchName.value } })
  tableData.value = res.data.data
}

const openDialog = (row) => {
  form.value = { ...row }
  showDialog.value = true
}

const save = async () => {
  await axios.post(`${API}/save`, form.value)
  ElMessage.success('操作成功')
  showDialog.value = false
  loadData()
}

const del = (id) => {
  ElMessageBox.confirm('确认删除吗?', '提示').then(async () => {
    await axios.delete(`${API}/${id}`)
    ElMessage.success('已删除')
    loadData()
  })
}

onMounted(loadData)
</script>

<style scoped>
.search-bar { display: flex; }
</style>

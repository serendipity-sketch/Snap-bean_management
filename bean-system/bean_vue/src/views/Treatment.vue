<template>
  <div>
    <el-card>
      <div class="search-bar">
        <el-input v-model="searchName" placeholder="搜索关联病害" style="width: 200px; margin-right: 10px" clearable @clear="loadData" />
        <el-button type="primary" @click="loadData">查询</el-button>
        <el-button type="success" @click="openDialog({})">新增治疗方案</el-button>
      </div>

      <el-table :data="tableData" border style="width: 100%; margin-top: 20px">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="diseaseName" label="针对病害" width="150" />
        <el-table-column prop="method" label="治疗方案" />
        <el-table-column prop="pesticide" label="推荐农药" width="150" />
        <el-table-column label="操作" width="180">
          <template #default="scope">
            <el-button size="small" @click="openDialog(scope.row)">编辑</el-button>
            <el-button size="small" type="danger" @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="showDialog" :title="form.id ? '编辑' : '新增'">
      <el-form :model="form" label-width="80px">
        <el-form-item label="针对病害">
          <el-input v-model="form.diseaseName" placeholder="请输入病害名称" />
        </el-form-item>
        <el-form-item label="治疗方案">
          <el-input v-model="form.method" type="textarea" :rows="3" />
        </el-form-item>
        <el-form-item label="推荐农药">
          <el-input v-model="form.pesticide" />
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

const API = 'http://localhost:9090/api/treatment'
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
  ElMessage.success('保存成功')
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

<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :model="queryParams" size="small" :inline="true" label-width="68px">
      <el-form-item label="部门" prop="deptName">
        <el-select v-model="queryParams.deptName" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            @keyup.enter.native="handleQuery"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="职员姓名" prop="empName">
        <el-input
          v-model="queryParams.empName"
          placeholder="请输入职员姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学历" prop="empDegreeName">
        <el-select v-model="queryParams.empDegreeName" placeholder="请选择">
          <el-option
            v-for="item in education"
            :key="item.value"
            :label="item.label"
            @keyup.enter.native="handleQuery"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
        >导出</el-button>
      </el-col>
<!--      <right-toolbar :show-search.sync="showSearch" @queryTable="getList" />-->
    </el-row>

    <el-table v-loading="loading" :data="employeeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="职工ID" align="center" prop="id" />
      <el-table-column label="职员姓名" align="center" prop="empName" />
      <el-table-column label="性别" align="center" prop="sex" />
      <el-table-column label="年龄" align="center" prop="age" />
      <el-table-column label="部门名称" align="center" prop="deptName" />
      <el-table-column label="学历" align="center" prop="empDegreeName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改职工列表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="职员姓名" prop="empName">
          <el-input v-model="form.empName" placeholder="请输入职员姓名" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="form.sex" placeholder="请选择">
            <el-option
              v-for="item in sex"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="form.age" placeholder="请输入年龄" />
        </el-form-item>
        <el-form-item label="部门名称" prop="deptName">
          <el-select v-model="form.deptName" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学历" prop="empDegreeName">
          <el-select v-model="form.empDegreeName" placeholder="请选择">
            <el-option
              v-for="item in education"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listEmployee, getEmployee, delEmployee, addEmployee, updateEmployee } from '@/api/system/employee'

import Pagination from '@/components/Pagination'

export default {
  name: 'Employee',
  comments: { Pagination },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 职工列表表格数据
      employeeList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        empName: null,
        deptName: null,
        empDegreeName: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      options: [{
        value: '业务部',
        label: '业务部'
      }, {
        value: '后勤部',
        label: '后勤部'
      }, {
        value: '人事部',
        label: '人事部'
      }],
      sex: [{
        value: '男',
        label: '男'
      }, {
        value: '女',
        label: '女'
      }],
      education: [{
        value: '大专',
        label: '大专'
      }, {
        value: '本科',
        label: '本科'
      }, {
        value: '研究生',
        label: '研究生'
      }],
      value: ''
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询职工列表列表 */
    getList() {
      this.loading = true
      listEmployee(this.queryParams).then(response => {
        this.employeeList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        empName: null,
        sex: null,
        age: null,
        deptName: null,
        empDegreeName: null
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加职工列表'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getEmployee(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改职工列表'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateEmployee(this.form).then(response => {
              this.$message({
                message: '修改成功',
                type: 'success'
              })
              this.open = false
              this.getList()
            })
          } else {
            addEmployee(this.form).then(response => {
              this.$message({
                message: '新增成功',
                type: 'success'
              })
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$confirm('是否确认删除职工列表编号为"' + ids + '"的数据项？').then(function() {
        return delEmployee(ids)
      }).then(() => {
        this.getList()
        this.$message({
          message: '删除成功',
          type: 'success'
        })
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/employee/export', {
        ...this.queryParams
      }, `employee_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>

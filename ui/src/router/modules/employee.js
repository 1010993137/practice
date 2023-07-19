import Layout from '@/layout'

const employeeRouter = {
  path: '/workers',
  component: Layout,
  redirect: '/table/complex-table',
  name: 'Workers',
  meta: {
    title: '职工管理',
    icon: 'table'
  },
  children: [
    {
      path: 'count',
      component: () => import('@/views/system/employee/index'),
      name: 'DynamicTable',
      meta: { title: '职工统计' }
    },
    {
      path: 'list',
      component: () => import('@/views/system/employee/index'),
      name: 'EmployeeList',
      meta: { title: '职工列表' }
    }
  ]
}
export default employeeRouter

import { createRouter, createWebHashHistory, type RouteRecordRaw } from 'vue-router'

export const menuRoutes: RouteRecordRaw[] = [
  {
    path: '/p/gen',
    meta: {
      title: '代码生成器',
      icon: 'Collection'
    },
    children: [
      {
        path: '/gen/generator',
        name: 'Generator',
        component: () => import('@/views/generator/index.vue'),
        meta: {
          title: '代码生成',
          icon: 'Tickets'
        }
      },
      {
        path: '/gen/datasource',
        name: 'Datasource',
        component: () => import('@/views/datasource/index.vue'),
        meta: {
          title: '数据源管理',
          icon: 'Refrigerator'
        }
      },
      {
        path: '/gen/field-type',
        name: 'FieldType',
        component: () => import('@/views/field-type/index.vue'),
        meta: {
          title: '字段类型映射',
          icon: 'Operation'
        }
      },
      {
        path: '/gen/base-class',
        name: 'BaseClass',
        component: () => import('@/views/base-class/index.vue'),
        meta: {
          title: '基类管理',
          icon: 'OfficeBuilding'
        }
      },
      {
        path: '/gen/project-modify',
        name: 'Project',
        component: () => import('@/views/project-modify/index.vue'),
        meta: {
          title: '项目名修改',
          icon: 'Edit'
        }
      }
    ]
  }
]


export const constantRoutes: RouteRecordRaw[] = [
    {
        path: '/',
        component: () => import('@/components/layout/index.vue'),
        redirect: '/gen/generator',
        children: [...menuRoutes]
    },
    {
        path: '/404',
        component: () => import('@/views/404.vue')
    },
    {
        path: '/:pathMatch(.*)',
        redirect: '/404'
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes: constantRoutes
})

export default router

import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

/* Layout */
import Layout from "@/layout";

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: "/login",
    component: () => import("@/views/login/index"),
    hidden: true
  },

  {
    path: "/404",
    component: () => import("@/views/404"),
    hidden: true
  },

  {
    path: "/",
    component: Layout,
    redirect: "/dashboard",
    children: [
      {
        path: "dashboard",
        name: "Dashboard",
        component: () => import("@/views/dashboard/index"),
        meta: { title: "Dashboard", icon: "dashboard" }
      }
    ]
  },

  {
    path: "/teacher",
    component: Layout,
    redirect: "/teacher/list",
    name: "讲师管理",
    meta: { title: "讲师管理", icon: "el-icon-s-help" },
    children: [
      {
        path: "list",
        name: "讲师列表",
        component: () => import("@/views/teacher/list"),
        meta: { title: "讲师列表", icon: "table" }
      },
      {
        path: "add",
        name: "添加讲师",
        component: () => import("@/views/teacher/add"),
        meta: { title: "添加讲师", icon: "tree" }
      },
      {
        path: "edit/:id",
        name: "讲师修改",
        component: () => import("@/views/teacher/add"),
        meta: { title: "讲师修改", icon: "tree" },
        hidden: true
      }
    ]
  },

  {
    path: "/subject",
    component: Layout,
    redirect: "/subject/list",
    name: "课程分类管理管理",
    meta: { title: "课程分类管理", icon: "example" },
    children: [
      {
        path: "list",
        name: "课程分类列表",
        component: () => import("@/views/subject/list"),
        meta: { title: "课程分类列表", icon: "table" }
      },
      {
        path: "import",
        name: "课程分类导入",
        component: () => import("@/views/subject/import"),
        meta: { title: "课程分类导入", icon: "tree" }
      },
    ]
  },

  {
    path: '/course',
    component: Layout,
    redirect: '/course/list',
    name: 'Course',
    meta: { title: '课程管理', icon: 'form' },
    children: [
      {
        path: 'list',
        name: 'EduCourseList',
        component: () => import('@/views/course/list'),
        meta: { title: '课程列表' }
      },
      {
        path: 'info',
        name: 'EduCourseInfo',
        component: () => import('@/views/course/info'),
        meta: { title: '发布课程' }
      },
      {
        path: 'info/:id',
        name: 'EduCourseInfoEdit',
        component: () => import('@/views/course/info'),
        meta: { title: '编辑课程基本信息', noCache: true },
        hidden: true
      },
      {
        path: 'chapter/:id',
        name: 'EduCourseChapterEdit',
        component: () => import('@/views/course/chapter'),
        meta: { title: '编辑课程大纲', noCache: true },
        hidden: true
      },
      {
        path: 'publish/:id',
        name: 'EduCoursePublishEdit',
        component: () => import('@/views/course/publish'),
        meta: { title: '发布课程', noCache: true },
        hidden: true
      }
    ]
  },
  {
    path: '/sta',
    component: Layout,
    redirect: '/sta/show',
    name: 'sta',
    meta: { title: '统计分析', icon: 'example' },
    children: [
      {
        path: 'create',
        name: '生成数据',
        component: () => import('@/views/sta/create'),
        meta: { title: '生成数据' ,icon: 'table'}
      },
      {
        path: 'show',
        name: '图表显示',
        component: () => import('@/views/sta/show'),
        meta: { title: '图表显示' ,icon: 'table'}
      },
    ]
  },



  {
    path: "external-link",
    component: Layout,
    children: [
      {
        path: "https://panjiachen.github.io/vue-element-admin-site/#/",
        meta: { title: "External Link", icon: "link" }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: "*", redirect: "/404", hidden: true }
];

const createRouter = () =>
  new Router({
    // mode: 'history', // require service support
    scrollBehavior: () => ({ y: 0 }),
    routes: constantRoutes
  });

const router = createRouter();

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter();
  router.matcher = newRouter.matcher; // reset router
}

export default router;

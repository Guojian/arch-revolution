import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter);

// 1. 定义 (路由) 组件。
// 可以从其他文件 import 进来
import Help from './components/Help.vue'
import ListUser from './components/ListUser.vue'
import AddUser from './components/AddUser.vue'
import EditUser from './components/EditUser.vue'
import Index from "./components/Index";
import ShowUser from "./components/ShowUser";
import ListBlog from "./components/ListBlog";
import AddBlog from "./components/AddBlog";
import ShowBlog from "./components/ShowBlog";
import EditBlog from "./components/EditBlog";

// 2. 定义路由
// 每个路由应该映射一个组件。 其中"component" 可以是
// 通过 Vue.extend() 创建的组件构造器，
// 或者，只是一个组件配置对象。
// 我们晚点再讨论嵌套路由。
const routes = [
  {path: '/', component: Index, meta: {title: 'Demo 首页'}},
  {path: '/user/list', component: ListUser, meta: {title: '用户管理'}},
  {path: '/user/show/:id', component: ShowUser, meta: {title: '用户详情'}},
  {path: '/user/add', component: AddUser, meta: {title: "添加用户"}},
  {path: '/user/edit/:id', component: EditUser, meta: {title: '更新用户'}},
  {path: '/blog/list', component: ListBlog, meta: {title: '博客管理'}},
  {path: '/blog/add', component: AddBlog, meta: {title: '添加博客'}},
  {path: '/blog/show/:id', component: ShowBlog, meta: {title: '博客详情'}},
  {path: '/blog/edit/:id', component: EditBlog, meta: {title: '更新博客'}},
  {path: '/help', component: Help}
];

// 3. 创建 router 实例，然后传 `routes` 配置
// 你还可以传别的配置参数, 不过先这么简单着吧。
const router = new VueRouter({
  routes // (缩写) 相当于 routes: routes
});

const DEFAULT_TITLE = 'Demo';
// eslint-disable-next-line
router.afterEach((to, from) => {
  document.title = to.meta.title || DEFAULT_TITLE;
});

export default router
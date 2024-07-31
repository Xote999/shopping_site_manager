import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'

//ルーティングの設定
const constRouter = [
  {
    //リダイレクト（デフォルトはlogin画面）
    path:'',
    redirect:"/login"
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  }
]

//ルーティングの作成
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: constRouter
})
//ルーティングのエクスポート
export default router

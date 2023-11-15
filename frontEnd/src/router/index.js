import Home from "@/views/Home/Home.vue";
import SearchKakao from "@/views/SearchKakao/SearchKakao.vue";
import SearchSubway from "@/views/SearchSubway/SearchSubway.vue";
import SignUp from "@/views/SignUp/SignUp.vue";
import Login from "@/views/Login/Login.vue";
import { createRouter, createWebHistory } from "vue-router";
const routes = [
  { path: "/", name: "home", component: Home },
  { path: "/searchkakao", name: "searchKakao", component: SearchKakao },
  { path: "/searchsubway", name: "searchSubway", component: SearchSubway },
  { path: "/signup", name: "signup", component: SignUp },
  { path: "/login", name: "login", component: Login },
];

const router = createRouter({
  history: createWebHistory("/"),
  routes,
});

export default router;

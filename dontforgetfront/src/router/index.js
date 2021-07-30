import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/Home";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/actividades",
    name: "actividades",
    component: () => import("../views/Actividades.vue"),
  },
  {
    path: "/carpetas",
    name: "carpetas",
    component: () =>
      import(/* webpackChunkName: "carpetas" */ "../views/Carpetas.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

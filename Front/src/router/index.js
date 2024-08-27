// src/router/index.js
import { createRouter, createWebHistory } from "vue-router";
import StudentListView from "@/components/StudentListView.vue";

const routes = [
  {
    path: "/students",
    name: "StudentListView",
    component: StudentListView,
  },
  // Puedes añadir más rutas aquí
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;

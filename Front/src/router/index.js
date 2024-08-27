// src/router/index.js
import { createRouter, createWebHistory } from "vue-router";
import StudentListView from "@/components/StudentListView.vue";
import CreateStudent from "@/components/CreateStudent.vue";

const routes = [
  {
    path: "/students",
    name: "StudentListView",
    component: StudentListView,
  },
  {
    path: "/create-student/:id?",
    name: "CreateStudent",
    component: CreateStudent,
  },
  // Puedes añadir más rutas aquí
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;

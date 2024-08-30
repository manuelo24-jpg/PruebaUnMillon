// src/router/index.js
import { createRouter, createWebHistory } from "vue-router";
import StudentListView from "@/components/StudentListView.vue";
import CreateStudent from "@/components/CreateStudent.vue";
import StudentMarks from "@/components/StudentMarks.vue";

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
  {
    path: "/students/:studentId/marks",
    name: "StudentMarks",
    component: StudentMarks,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;

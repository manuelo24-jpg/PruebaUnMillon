<template>
  <div>
    <h1>Student List</h1>
    <ul>
      <li v-for="student in students" :key="student.id">
        {{ student.firstName }} {{ student.lastName }} - {{ student.email }}
      </li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "StudentListView",
  data() {
    return {
      students: [],
    };
  },
  created() {
    axios
      .get("http://localhost:8080/api/students")
      .then((response) => {
        this.students = response.data;
      })
      .catch((error) => {
        console.error("There was an error fetching the students!", error);
      });
  },
};
</script>

<style scoped>
/* Añade tus estilos aquí */
</style>

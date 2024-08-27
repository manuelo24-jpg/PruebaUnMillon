<template>
  <div class="container">
    <h1>Lista de Estudiantes</h1>
    <div class="table-container">
      <table>
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Email</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="student in students" :key="student.id">
            <td>{{ student.firstName }}</td>
            <td>{{ student.lastName }}</td>
            <td>{{ student.email }}</td>
            <td>
              <button @click="editStudent(student)">Editar</button>
              <button @click="deleteStudent(student.id)">Eliminar</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";

const students = ref([]);

const fetchStudents = async () => {
  try {
    const response = await axios.get("http://localhost:8080/api/students");
    students.value = response.data;
  } catch (error) {
    console.error("There was an error fetching the students!", error);
  }
};

const editStudent = (student) => {
  // Lógica para editar el estudiante
  console.log("Edit student:", student);
};

const deleteStudent = (studentId) => {
  // Lógica para eliminar el estudiante
  console.log("Delete student with ID:", studentId);
};

onMounted(fetchStudents);
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  background-color: #f5f5f5;
}

.table-container {
  overflow-x: auto;
  margin-top: 20px;
}

table {
  width: 100%;
  max-width: 800px;
  border-collapse: collapse;
  background-color: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

th,
td {
  padding: 12px 15px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

th {
  background-color: #f4f4f4;
}

tr:hover {
  background-color: #f1f1f1;
}

button {
  margin-right: 5px;
  padding: 5px 10px;
  border: none;
  background-color: #007bff;
  color: white;
  cursor: pointer;
  border-radius: 3px;
}

button:hover {
  background-color: #0056b3;
}
</style>

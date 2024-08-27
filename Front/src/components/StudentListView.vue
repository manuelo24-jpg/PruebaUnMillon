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
              <button @click="showDetails(student)">Detalles</button>
              <button @click="editStudent(student.id)">Editar</button>
              <button @click="confirmDelete(student.id)">Eliminar</button>
              <input type="hidden" :value="student.id" />
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-if="selectedStudent" class="modal">
      <div class="modal-content">
        <span class="close" @click="closeModal">&times;</span>
        <h2>Detalles del Estudiante</h2>
        <p><strong>Nombre:</strong> {{ selectedStudent.firstName }}</p>
        <p><strong>Apellido:</strong> {{ selectedStudent.lastName }}</p>
        <p><strong>Email:</strong> {{ selectedStudent.email }}</p>
      </div>
    </div>
    <div v-if="showDeleteConfirm" class="modal">
      <div class="modal-content">
        <span class="close" @click="closeDeleteConfirm">&times;</span>
        <h2>Confirmar Eliminación</h2>
        <p>¿Estás seguro de que deseas eliminar este estudiante?</p>
        <button @click="deleteStudent">Confirmar</button>
        <button @click="closeDeleteConfirm">Cancelar</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
import { useToast } from "vue-toast-notification";
import router from "@/router";

const students = ref([]);
const selectedStudent = ref(null);
const showDeleteConfirm = ref(false);
const studentToDelete = ref(null);
const toast = useToast();

const fetchStudents = async () => {
  try {
    const response = await axios.get("http://localhost:8080/api/students");
    students.value = response.data;
    console.log("Students:", students.value);
  } catch (error) {
    console.error("There was an error fetching the students!", error);
  }
};

const showDetails = (student) => {
  selectedStudent.value = student;
};

const closeModal = () => {
  selectedStudent.value = null;
};

const confirmDelete = (studentId) => {
  studentToDelete.value = studentId;
  showDeleteConfirm.value = true;
};

const closeDeleteConfirm = () => {
  showDeleteConfirm.value = false;
  studentToDelete.value = null;
};

const deleteStudent = async () => {
  try {
    await axios.delete(
      `http://localhost:8080/api/students/${studentToDelete.value}`
    );
    students.value = students.value.filter(
      (student) => student.id !== studentToDelete.value
    );
    showDeleteConfirm.value = false;
    studentToDelete.value = null;
    toast.success("Estudiante eliminado correctamente");
  } catch (error) {
    toast.error("There was an error deleting the student!", error);
  }
};

const editStudent = (id) => {
  router.push(`/create-student/${id}`);
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

.modal {
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
}

.modal-content {
  background-color: #fff;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  width: 80%;
  max-width: 500px;
  position: relative;
}

.close {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 24px;
  cursor: pointer;
}
</style>

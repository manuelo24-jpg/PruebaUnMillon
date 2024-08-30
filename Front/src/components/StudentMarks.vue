<template>
  <div>
    <h2>Notas de {{ student.firstName }} {{ student.lastName }}</h2>
    <p v-if="marks.length === 0">Sin evaluar</p>
    <table v-else>
      <thead>
        <tr>
          <th>Asignatura</th>
          <th>Nota</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="mark in marks" :key="mark.id">
          <td>{{ mark.subject }}</td>
          <td>{{ mark.grade }}</td>
          <td>
            <button @click="editMark(mark.id)">Editar</button>
            <button @click="deleteMark(mark.id)">Eliminar</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/router";

export default {
  data() {
    return {
      student: {},
      marks: [],
    };
  },
  created() {
    this.fetchStudentAndMarks();
  },
  methods: {
    async fetchStudentAndMarks() {
      const studentId = this.$route.params.studentId;
      try {
        const studentResponse = await axios.get(
          `http://localhost:8080/api/students/${studentId}`
        );
        this.student = studentResponse.data;
        const marksResponse = await axios.get(
          `http://localhost:8080/api/students/${studentId}/marks`
        );
        this.marks = marksResponse.data;
      } catch (error) {
        console.error("Error fetching student or marks:", error);
      }
    },
    editMark(markId) {
      router.push(`/edit-mark/${markId}`);
    },
    async deleteMark(markId) {
      try {
        await axios.delete(`http://localhost:8080/api/marks/${markId}`);
        this.marks = this.marks.filter((mark) => mark.id !== markId);
      } catch (error) {
        console.error("Error deleting mark:", error);
      }
    },
  },
};
</script>

<style scoped>
h2 {
  color: #333;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th,
td {
  padding: 12px;
  border: 1px solid #ddd;
  text-align: left;
}

th {
  background-color: #f4f4f4;
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

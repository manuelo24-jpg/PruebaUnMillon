<template>
  <div class="form-container">
    <h2>{{ isEditing ? "Edit Student" : "Create New Student" }}</h2>
    <form @submit.prevent="submitForm" class="student-form">
      <div class="form-group">
        <label for="firstName">First Name:</label>
        <input type="text" v-model="student.firstName" required />
      </div>
      <div class="form-group">
        <label for="lastName">Last Name:</label>
        <input type="text" v-model="student.lastName" required />
      </div>
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" v-model="student.email" required />
      </div>
      <button type="submit" class="submit-button">
        {{ isEditing ? "Update Student" : "Create Student" }}
      </button>
      <button
        type="button"
        class="refresh-button"
        @click="resetForm"
        v-if="isEditing"
      >
        Reset
      </button>
    </form>
  </div>
</template>

<script setup>
import { ref, watch } from "vue";
import axios from "axios";
import { useRouter, useRoute } from "vue-router";
import { useToast } from "vue-toast-notification";

const student = ref({
  firstName: "",
  lastName: "",
  email: "",
});

const isEditing = ref(false);
const router = useRouter();
const route = useRoute();
const toast = useToast();

const fetchStudent = async (id) => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/students/${id}`
    );
    student.value = response.data;
    isEditing.value = true;
  } catch (error) {
    toast.error("There was an error fetching the student:", error);
  }
};

const submitForm = async () => {
  try {
    if (isEditing.value) {
      await axios.put(
        `http://localhost:8080/api/students/${student.value.id}`,
        student.value
      );
      toast.success("Student updated successfully");
    } else {
      await axios.post("http://localhost:8080/api/students", student.value);
      toast.success("New student created successfully");
    }
    router.push("/students");
  } catch (error) {
    toast.error("There was an error submitting the form:", error);
  }
};

const resetForm = () => {
  student.value = {
    firstName: "",
    lastName: "",
    email: "",
  };
  isEditing.value = false;
};

watch(
  () => route.params.id,
  (newId) => {
    if (newId) {
      fetchStudent(newId);
    } else {
      resetForm();
    }
  },
  { immediate: true }
);
</script>

<style scoped>
.form-container {
  max-width: 600px;
  margin: 2rem auto;
  padding: 2rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #f9f9f9;
}

.student-form {
  display: flex;
  flex-direction: column;
}

.form-group {
  margin-bottom: 1rem;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: bold;
}

.form-group input {
  width: 100%;
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.submit-button,
.refresh-button {
  padding: 0.75rem;
  border: none;
  border-radius: 4px;
  background-color: #007bff;
  color: white;
  font-size: 1rem;
  cursor: pointer;
  margin-top: 1rem;
}

.submit-button:hover,
.refresh-button:hover {
  background-color: #0056b3;
}

.refresh-button {
  background-color: #6c757d;
}

.refresh-button:hover {
  background-color: #5a6268;
}
</style>

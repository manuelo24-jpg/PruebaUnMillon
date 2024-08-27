<script setup>
import { RouterLink, RouterView } from "vue-router";
</script>

<template>
  <div>
    <nav class="navbar">
      <RouterLink to="/students">Listado</RouterLink> |
      <RouterLink to="/create-student">Crear</RouterLink>
    </nav>
    <router-view></router-view>
  </div>
</template>

<style>
.navbar {
  background-color: #e1d1d1;
  padding: 1rem;
  display: flex;
  gap: 5px;
}

.nav-link {
  color: white;
  font-weight: bold;
  text-decoration: none;
  font-size: 1.2rem;
}

.nav-link:hover {
  text-decoration: underline;
}
</style>

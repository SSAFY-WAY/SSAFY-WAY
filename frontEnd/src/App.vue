<script setup>
import TheHeader from "./components/layout/TheHeader/TheHeader.vue";
</script>

<template>
  <v-app>
    <TheHeader />
    <main class="main"><router-view></router-view></main>
  </v-app>
</template>

<style scoped>
.main {
  background-color: #f3f3f3;
}
</style>

<script setup>
import TheHeader from "./components/layout/TheHeader/TheHeader.vue";
</script>

<template>
  <v-app class="v-app">
    <TheHeader />
    <router-view></router-view>
    <!-- <main class="main"></main> -->
  </v-app>
</template>

<style scoped>
.main {
  background-color: #f3f3f3;
}
</style>

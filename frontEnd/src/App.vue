<script setup>
import TheHeader from "./components/layout/TheHeader/TheHeader.vue";
</script>

<template>
  <v-app>
    <TheHeader />

    <router-view></router-view>
  </v-app>
</template>

<style scoped></style>

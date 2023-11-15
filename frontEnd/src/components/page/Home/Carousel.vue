<script setup>
import { ref } from "vue";
import getImageUrl from "@/util/getUrl";
const items = ref([
  {
    src: "apart1.png",
  },
  {
    src: "apart2.jpg",
  },
  {
    src: "apart3.png",
  },
  {
    src: "apart4.jpg",
  },
]);
</script>
<template>
  <v-carousel height="400" class="carousel">
    <v-carousel-item
      v-for="(item, i) in items"
      :key="i"
      :src="getImageUrl(item.src)"
      cover
    ></v-carousel-item>
  </v-carousel>
</template>

<style scoped>
.carousel {
  margin-top: 3px;
  /* border: 1px solid black; */
}
</style>

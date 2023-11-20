import { defineStore } from "pinia";
import { ref } from "vue";
export const useStationStore = defineStore("selectStation", () => {
  const selectedStations = ref([]);
  const addStation = (data) => {
    selectedStations.value.push(data);
  };
  const deleteStation = (name) => {
    selectedStations.value = selectedStations.value.filter(
      (e) => e.name !== name
    );
  };
  return { selectedStations, addStation, deleteStation };
});

import { defineStore } from "pinia";
import { ref } from "vue";
export const useUserStore = defineStore("user", () => {
  const isLogin = ref(false);
  const userName = ref("");
  function permitAuth() {
    isLogin.value = true;
  }
  function unPermitAuth() {
    isLogin.value = false;
  }

  return { isLogin, userName, permitAuth, unPermitAuth };
});

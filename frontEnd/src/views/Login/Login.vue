<script setup>
import BaseInput from "../../components/common/Baseinput.vue";
import { useUserStore } from "../../store/userStore";
import router from "@/router";
import { ref } from "vue";

const userStore = useUserStore();
const userData = ref({
  email: "",
  password: "",
});
const request = (e) => {
  e.preventDefault();
  // 유효성 검사
  if (!userData.value.email || !userData.value.password) {
    alert("");
    return;
  }
  userStore.permitAuth();
  router.push({ name: "home" });
  // requestLogin(userData)
  //   .then(() => {
  //     userStore.permitAuth();
  //     // 이전 페이지로 되돌아가기
  //     router.push({ name: "home" });
  //   })
  //   .catch(() => {
  //     alert("로그인 오류입니다.");
  //   });
};

const updateUserdata = (key, content) => {
  userData.value[key] = content;
};
</script>

<template>
  <section class="container">
    <div class="login-form-container">
      <form class="login-form">
        <strong>로그인</strong>
        <BaseInput
          :label="['이메일', 'email']"
          placeholder="예) example@ssafy.com"
          type="text"
          :content="userData.email"
          @update="updateUserdata"
        />
        <BaseInput
          :label="['비밀번호', 'password']"
          placeholder="비밀번호를 입력해주세요"
          type="password"
          :content="userData.password"
          @update="updateUserdata"
        />

        <button class="login-button" @click="request">로그인</button>
      </form>
    </div>
  </section>
</template>
<style scoped>
.container {
  width: 100vw;
  height: 100vh;
}
.login-form-container {
  width: 400px;
  margin: 0px auto;
  margin-top: 90px;
}
.login-form {
  padding: 20px;
  border: 1px solid var(--primary);
  border-radius: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: white;
}
.login-form strong {
  font-size: 30px;
  color: var(--accent3);
}
.login-button {
  margin-top: 15px;
  background-color: var(--accent3);
  color: white;
  font-size: 20px;
  font-weight: 30px;
  width: 100%;
  height: 50px;
  border-radius: 20px;
}
</style>

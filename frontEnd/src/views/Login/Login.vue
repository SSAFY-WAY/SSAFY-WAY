<script setup>
import BaseInput from "../../components/common/Baseinput.vue";
import router from "../../router";
import { useUserStore } from "../../store/userStore";
const userStore = useUserStore();
const onClickLoginHandler = (e) => {
  e.preventDefault();
  userStore.permitAuth();
  // 이전 페이지로 되돌아가기
};
</script>
<template>
  <section class="container">
    <div class="login-form-container">
      <form class="login-form">
        <strong>로그인</strong>
        <BaseInput
          label="이메일"
          placeholder="예) example@ssafy.com"
          type="text"
        />
        <BaseInput
          label="비밀번호"
          placeholder="비밀번호를 입력해주세요"
          type="password"
        />

        <button class="login-button" @click="onClickLoginHandler">
          로그인
        </button>
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

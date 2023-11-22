//ANCHOR - script
<script setup>
import BaseInput from "@/components/common/Baseinput.vue";
import { ref } from "vue";
import { requestSignUp } from "@/apis/request/requestSignUp.js";
import router from "@/router";

const userData = ref({
  name: "",
  email: "",
  password: "",
  validPassword: "",
  phoneNumber: "",
});
const updateUserdata = (key, content) => {
  userData.value[key] = content;
};
const request = (e) => {
  e.preventDefault();
  // userData 유효성 검사
  if (userData.value.password !== userData.value.validPassword) {
    alert("비밀번호가 일치하지 않습니다.");
    return;
  }
  requestSignUp(userData.value)
    .then((data) => {
      alert("회원가입 성공");
      router.push({ name: "login" });
    })
    .catch((err) => console.log(err));
};
</script>

//ANCHOR - template
<template>
  <main class="container">
    <div class="signup-form-container">
      <form class="signup-form">
        <strong>회원 가입</strong>
        <BaseInput
          :label="['이름', 'name']"
          placeholder="예) 김싸피"
          type="text"
          :content="userData.name"
          @update="updateUserdata"
        />
        <BaseInput
          :label="['이메일', 'email']"
          placeholder="예) example@ssafy.com"
          type="text"
          :content="userData.email"
          @update="updateUserdata"
        />
        <BaseInput
          :label="['비밀번호', 'password']"
          placeholder="6~12자 영문,숫자를 조합해주세요"
          type="password"
          :content="userData.password"
          @update="updateUserdata"
        />
        <BaseInput
          :label="['비밀번호 확인', 'validPassword']"
          placeholder="6~12자 영문,숫자를 조합해주세요"
          type="password"
          :content="userData.validPassword"
          @update="updateUserdata"
        />
        <BaseInput
          :label="['핸드폰 번호', 'phoneNumber']"
          placeholder="예) 010-1234-5678"
          type="text"
          :content="userData.phoneNumber"
          @update="updateUserdata"
        />

        <button class="signup-button" @click="request">회원가입</button>
      </form>
    </div>
  </main>
</template>

//ANCHOR - style
<style scoped>
.container {
  width: 100vw;
  height: 100vh;
  background-color: #f3f3f3;
}
.signup-form-container {
  width: 400px;
  margin: 0px auto;
  margin-top: 80px;
}
.signup-form {
  padding: 20px;
  border: 1px solid var(--primary);
  border-radius: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: white;
}
.signup-form strong {
  font-size: 30px;
  color: var(--accent3);
}
.signup-button {
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

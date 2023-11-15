<script setup>
import BaseInput from "../../components/common/Baseinput.vue";
</script>

<template>
  <main class="container">
    <div class="signup-form-container">
      <form class="signup-form">
        <strong>회원 가입</strong>
        <BaseInput label="이름" placeholder="이름을 입력해주세요" type="text" />
        <BaseInput
          label="이메일"
          placeholder="예) example@ssafy.com"
          type="text"
        />
        <BaseInput
          label="비밀번호"
          placeholder="6~12자 영문,숫자를 조합해주세요"
          type="password"
        />
        <BaseInput
          label="핸드폰 번호"
          placeholder="예) 010-1234-5678"
          type="text"
        />

        <button class="signup-button">회원가입</button>
      </form>
    </div>
  </main>
</template>
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

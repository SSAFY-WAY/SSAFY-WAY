<script setup>
import { useUserStore } from "../../../store/userStore";
const userStore = useUserStore();
const onClickLogoutHandler = (e) => {
  e.preventDefault();
  userStore.unPermitAuth();
};
</script>
<template>
  <header class="headerContainer">
    <section class="headerContent">
      <div class="headerContentRight">
        <router-link to="/" class="homeRoute route text-primary">
          <img src="@/assets/logo.jpeg" alt="logo" />
        </router-link>
        <router-link class="subway route" to="/searchSubway"
          >지하철 찾기</router-link
        >
        <router-link class="kakao route" to="/searchKakao"
          >지도로 찾기</router-link
        >
      </div>

      <div class="userWidgetBox">
        <template v-if="userStore.isLogin === true">
          <div>마이페이지</div>
          <button @click="onClickLogoutHandler">로그아웃</button>
        </template>
        <template v-else>
          <router-link to="signup" class="logout-widget">회원가입</router-link>
          <router-link to="login" class="logout-widget">로그인</router-link>
        </template>
      </div>
    </section>
  </header>
</template>

<style scoped>
/* Container */
.headerContainer {
  /* position: fixed; */
  top: 0;
  width: 100vw;
  height: 90px;
  /* box-shadow: 0px 5px 5px -2px lightgray; */
  border-bottom: 1px solid lightgray;
  z-index: 999;
  background-color: white;
}
/* Contents */
.headerContent {
  margin: 0 auto;
  width: 95%;
  height: 100%;
  padding: 15px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.headerContentRight {
  display: flex;
  align-items: center;
}
.headerContentRight .route {
  font-size: 15px;
  font-weight: bold;
  color: black;
  margin-right: 20px;
}
.homeRoute.route {
  font-size: 30px;
  font-weight: bold;
  display: flex;
  align-items: center;
  text-decoration: none;
  margin-right: 40px;
  justify-content: center;
}
.homeRoute img {
  width: 80px;
  height: 50px;
}

/* .subwayRoute {
  font-size: 15px;
  font-weight: bold;
  color: black;
} */
.userWidgetBox {
  display: flex;
  width: 200px;
  justify-content: space-around;
}
</style>

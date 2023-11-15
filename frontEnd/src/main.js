import { createApp } from "vue";
import App from "./App.vue";

// Vuetify
import "vuetify/styles";
import createVuetify from "@/plugins/vuetify";

// Pinia
import { createPinia } from "pinia";

// style
import "./assets/main.css";

//Router
import router from "./router";

const pinia = createPinia();
const app = createApp(App);

app.use(createVuetify);
app.use(router);
app.use(pinia);
app.mount("#app");

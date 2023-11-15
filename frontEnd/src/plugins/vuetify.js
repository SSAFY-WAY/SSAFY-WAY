import { createVuetify } from "vuetify";
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";
import "@mdi/font/css/materialdesignicons.css"; // Ensure you are using css-loader

export default createVuetify({
  icons: {
    defaultSet: "mdi", // This is already the default value - only for display purposes
  },
  components,
  directives,
  theme: {
    themes: {
      light: {
        dark: false,
        colors: {
          primary: "#2196F3",
          light5: "#E3F2FD",
          light4: "#BBDEFB",
          light3: "#90CAF9",
          light2: "#64B5F6",
          light1: "#42A5F5",
          dark1: "#1E88E5",
          dark2: "#1976D2",
          dark3: "#1565C0",
          dark4: "#0D47A1",
          accent1: "#82B1FF",
          accent2: "#448AFF",
          accent3: "#2979FF",
          accent4: "#2962FF",
        },
      },
    },
  },
});

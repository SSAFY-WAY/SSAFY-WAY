import { rest } from "msw";
import { mockLogin } from "./resolver";
export const handlers = [
  /* user id를 이용해서 User 정보를 가져오는 API */
  rest.get("https://dev.api.me/user/:userId", mockLogin),
];

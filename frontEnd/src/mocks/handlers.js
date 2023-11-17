import { rest } from "msw";

export const handlers = [
  /* user id를 이용해서 User 정보를 가져오는 API */
  rest.get("https://dev.api.me/user/:userId", (req, res, ctx) => {
    const { userId } = req.params;
    return res(
      ctx.json({
        id: userId,
        firstName: "John",
        age: 38,
      })
    );
  }),
];

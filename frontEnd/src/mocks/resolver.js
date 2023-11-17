export const mockLogin = (req, res, ctx) => {
  const { userId } = req.params;
  return res(
    ctx.json({
      id: userId,
      firstName: "John",
      age: 38,
    })
  );
};

import axiosInstance from "../axios";
import { API_PATH } from "@/constants/path";

const requestLogin = (userData) => {
  return axiosInstance.post(API_PATH.USER_LOGIN, userData);
};

export { requestLogin };

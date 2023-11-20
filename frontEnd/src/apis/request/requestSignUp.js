import axiosInstance from "../axios";
import { API_PATH } from "@/constants/path";

const requestSignUp = (userData) => {
  console.log(API_PATH.USER_SIGNUP);
  return axiosInstance.post(API_PATH.USER_SIGNUP, userData);
};

export { requestSignUp };

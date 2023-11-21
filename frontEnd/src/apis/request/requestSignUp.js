import axiosInstance from "../axios";
import { API_PATH } from "@/constants/path";

const requestSignUp = (userData) => {
  return axiosInstance.post(API_PATH.USER_SIGNUP, userData);
};

export { requestSignUp };

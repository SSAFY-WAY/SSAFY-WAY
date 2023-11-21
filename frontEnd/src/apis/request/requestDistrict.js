import axiosInstance from "../axios";
import { API_PATH } from "@/constants/path";

const requestDistrict = () => {
  return axiosInstance.get(API_PATH.DISTRICT);
};

export { requestDistrict };

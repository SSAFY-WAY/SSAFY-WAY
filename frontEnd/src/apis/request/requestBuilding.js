import axiosInstance from "../axios";
import { API_PATH } from "@/constants/path";

const requestBuilding = (data) => {
  return axiosInstance.post(API_PATH.BUILDING, data);
};

export { requestBuilding };

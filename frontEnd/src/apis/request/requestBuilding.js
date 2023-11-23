import axiosInstance from "../axios";
import { API_PATH } from "@/constants/path";

const requestBuilding = (data) => {
  console.log(data);
  return axiosInstance.post(API_PATH.BUILDING, data);
};

const requestBuildingBySubway = (data) => {
  return axiosInstance.post(API_PATH.SUBWAY, data);
};

export { requestBuildingBySubway, requestBuilding };

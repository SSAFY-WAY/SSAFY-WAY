import axiosInstance from "../axios";
import { API_PATH } from "@/constants/path";

const requestLegalDong = (dongCode) => {
  return axiosInstance.get(API_PATH.LEGAL, {
    params: {
      districtCode: dongCode,
    },
  });
};

export { requestLegalDong };

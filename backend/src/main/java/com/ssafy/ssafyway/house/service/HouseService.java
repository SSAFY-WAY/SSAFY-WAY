package com.ssafy.ssafyway.house.service;

import com.ssafy.ssafyway.api.seoulopendata.data.cond.ExistByHouseCond;
import com.ssafy.ssafyway.api.seoulopendata.data.vo.RentRow;
import com.ssafy.ssafyway.building.domain.Building;
import com.ssafy.ssafyway.house.domain.House;
import com.ssafy.ssafyway.house.domain.HouseRepository;
import com.ssafy.ssafyway.house.exception.HouseErrorCode;
import com.ssafy.ssafyway.house.exception.HouseException;
import com.ssafy.ssafyway.house.mapper.HouseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HouseService {
    private final HouseRepository houseDetailRepository;
    private final HouseMapper houseMapper;

    /**
     * HouseDetail 데이터를 저장한다.
     *
     * @param row      row 참조
     * @param building houseGeo 참조
     */
    public void saveHouseDetail(RentRow row, Building building) {
        houseDetailRepository.save(houseMapper.toEntity(row, building));
    }

    public House findById(int id) {
        return houseDetailRepository.findById(id)
                .orElseThrow(() -> new HouseException(
                        HouseErrorCode.ERROR_CLIENT_WITH_HOUSE_DETAIL_IS_NOT_EXISTED));
    }

    public Optional<House> findByDataCond(ExistByHouseCond cond) {
        return houseDetailRepository.findByDataCond(cond);
    }
}

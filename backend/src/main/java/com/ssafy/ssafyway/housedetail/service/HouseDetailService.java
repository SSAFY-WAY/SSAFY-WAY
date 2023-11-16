package com.ssafy.ssafyway.housedetail.service;

import com.ssafy.ssafyway.api.seoulopendata.data.cond.ExistByDetailCond;
import com.ssafy.ssafyway.api.seoulopendata.data.vo.RentRow;
import com.ssafy.ssafyway.auth.vo.AuthMember;
import com.ssafy.ssafyway.housedetail.data.cond.HouseDetailRecentViewCond;
import com.ssafy.ssafyway.housedetail.data.dto.request.HouseDetailRecentViewRequest;
import com.ssafy.ssafyway.housedetail.data.dto.response.HouseDetailRecentViewResponse;
import com.ssafy.ssafyway.housedetail.data.vo.HouseDetailRecentViewVO;
import com.ssafy.ssafyway.housedetail.domain.HouseDetail;
import com.ssafy.ssafyway.housedetail.domain.HouseDetailRepository;
import com.ssafy.ssafyway.housedetail.exception.HouseDetailErrorCode;
import com.ssafy.ssafyway.housedetail.exception.HouseDetailException;
import com.ssafy.ssafyway.housedetail.mapper.HouseDetailMapper;
import com.ssafy.ssafyway.housegeo.domain.HouseGeo;
import com.ssafy.ssafyway.member.domain.Member;
import com.ssafy.ssafyway.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HouseDetailService {
    private final HouseDetailRepository houseDetailRepository;
    private final HouseDetailMapper houseDetailMapper;
    private final MemberService memberService;

    /**
     * HouseDetail 데이터를 저장한다.
     *
     * @param row      row 참조
     * @param houseGeo houseGeo 참조
     */
    public void saveHouseDetail(RentRow row, HouseGeo houseGeo) {
        houseDetailRepository.save(houseDetailMapper.toEntity(row, houseGeo));
    }

    public HouseDetail findById(int id) {
        return houseDetailRepository.findById(id)
                .orElseThrow(() -> new HouseDetailException(
                        HouseDetailErrorCode.ERROR_CLIENT_WITH_HOUSE_DETAIL_IS_NOT_EXISTED));
    }

    public HouseDetailRecentViewResponse findByRecentView(AuthMember authMember,
                                                          HouseDetailRecentViewRequest request) {
        Member findMember = memberService.findById(authMember.getMemberId());
        List<HouseDetailRecentViewVO> list = houseDetailRepository.findByRecentViewCond(
                        HouseDetailRecentViewCond.toCond(request))
                .stream()
                .map(HouseDetailRecentViewVO::from)
                .collect(Collectors.toList());
        return HouseDetailRecentViewResponse.toResponse(findMember, list);
    }

    public Optional<HouseDetail> findByDataCond(ExistByDetailCond cond) {
        return houseDetailRepository.findByDataCond(cond);
    }
}

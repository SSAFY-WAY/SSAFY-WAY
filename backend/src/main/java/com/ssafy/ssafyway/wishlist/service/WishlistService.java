package com.ssafy.ssafyway.wishlist.service;

import com.ssafy.ssafyway.house.domain.House;
import com.ssafy.ssafyway.house.service.HouseService;
import com.ssafy.ssafyway.member.domain.Member;
import com.ssafy.ssafyway.member.service.MemberService;
import com.ssafy.ssafyway.wishlist.domain.Wishlist;
import com.ssafy.ssafyway.wishlist.domain.WishlistRepository;
import com.ssafy.ssafyway.wishlist.dto.response.WishlistCheckResponse;
import com.ssafy.ssafyway.wishlist.dto.response.WishlistCreateResponse;
import com.ssafy.ssafyway.wishlist.dto.response.WishlistViewResponse;
import com.ssafy.ssafyway.wishlist.exception.WishlistErrorCode;
import com.ssafy.ssafyway.wishlist.exception.WishlistException;
import com.ssafy.ssafyway.wishlist.vo.WishlistVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
@Slf4j
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final MemberService memberService;
    private final HouseService houseService;

    private Member verifyLimit(int memberID) {
        Member findMember = memberService.findById(memberID);
        if (findMember.getWishlists().size() >= 5)
            throw new WishlistException(WishlistErrorCode.ERROR_CLIENT_WITH_WISHLIST_IS_FULL);
        return findMember;
    }

    @Transactional
    public WishlistCreateResponse create(int memberID, int houseId) {
        Member findMember = verifyLimit(memberID);
        House findHouse = houseService.findById(houseId);

        wishlistRepository.save(Wishlist.builder()
                .member(findMember)
                .house(findHouse)
                .build()
        );
        return WishlistCreateResponse.of(memberID, houseId);
    }

    public void remove(int wishlistId, int memberId) {
        Wishlist wishlist = wishlistRepository
                .findByIdAndMemberId(wishlistId, memberId)
                .orElseThrow(
                        () -> new WishlistException(WishlistErrorCode.ERROR_CLIENT_WITH_WISHLIST_IS_NOT_EXISTED));
        wishlist.removeRelated();
        wishlistRepository.delete(wishlist);
    }

    public WishlistViewResponse view(int memberId) {
        List<WishlistVO> wishlistVOList = wishlistRepository.findByMemberId(memberId);
        return WishlistViewResponse.from(wishlistVOList);
    }

    public WishlistCheckResponse check(int memberId, int houseId) {
        boolean isWishlist = wishlistRepository.existsByMemberIdAndHouseId(memberId, houseId);
        return WishlistCheckResponse.from(isWishlist);
    }
}

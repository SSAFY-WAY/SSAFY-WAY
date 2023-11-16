package com.ssafy.ssafyway.wishlist.domain.query;

import com.ssafy.ssafyway.wishlist.vo.WishlistVO;

import java.util.List;

public interface WishlistRepositoryCustom {
    List<WishlistVO> findByMemberId(int memberId);
}

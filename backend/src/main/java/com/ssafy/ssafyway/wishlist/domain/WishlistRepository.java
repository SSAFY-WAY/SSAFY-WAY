package com.ssafy.ssafyway.wishlist.domain;

import com.ssafy.ssafyway.wishlist.domain.query.WishlistRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer>, WishlistRepositoryCustom {
    Optional<Wishlist> findByIdAndMemberId(int wishlistId, int memberId);
    boolean existsByMemberIdAndHouseId(int memberId, int houseId);
}

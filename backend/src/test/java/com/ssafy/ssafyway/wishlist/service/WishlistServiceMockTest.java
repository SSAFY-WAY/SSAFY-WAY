package com.ssafy.ssafyway.wishlist.service;


import com.ssafy.ssafyway.global.config.MockitoTest;
import com.ssafy.ssafyway.wishlist.domain.WishlistRepository;
import com.ssafy.ssafyway.wishlist.dto.response.WishlistCheckResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

public class WishlistServiceMockTest extends MockitoTest {

    @InjectMocks
    private WishlistService wishlistService;

    @Mock
    private WishlistRepository wishlistRepository;

    @DisplayName("check wishlsit 테스트")
    @Test
    public void checkWishlistTest(){
        /* Given */
        given(wishlistRepository.existsByMemberIdAndHouseId(anyInt(),anyInt())).willReturn(true);

        /* When */
        WishlistCheckResponse response = wishlistService.check(1,1);

        /* Then */
        assertTrue(response.isWishlist());
    }
}

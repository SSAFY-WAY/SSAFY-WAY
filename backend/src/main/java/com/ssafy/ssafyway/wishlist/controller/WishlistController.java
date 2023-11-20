package com.ssafy.ssafyway.wishlist.controller;

import com.ssafy.ssafyway.wishlist.dto.response.WishlistCheckResponse;
import com.ssafy.ssafyway.wishlist.dto.response.WishlistCreateResponse;
import com.ssafy.ssafyway.wishlist.dto.response.WishlistViewResponse;
import com.ssafy.ssafyway.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
@RestController
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/auth/create")
    public ResponseEntity<WishlistCreateResponse> create(
            int memberId,
            @RequestParam("houseDetailId") int houseDetailId) {
        WishlistCreateResponse response = wishlistService.create(memberId, houseDetailId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/auth/remove")
    public ResponseEntity<Void> remove(
            int memberId,
            @RequestParam("wishlistId") int wishlistId) {
        wishlistService.remove(wishlistId, memberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/auth/view")
    public ResponseEntity<WishlistViewResponse> view(int memberId) {
        WishlistViewResponse response = wishlistService.view(memberId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/auth/check")
    public ResponseEntity<WishlistCheckResponse> check(int memberId, @RequestParam("houseDetailId") int houseDetailId){
        WishlistCheckResponse response = wishlistService.check(memberId, houseDetailId);
        return ResponseEntity.ok(response);
    }
}

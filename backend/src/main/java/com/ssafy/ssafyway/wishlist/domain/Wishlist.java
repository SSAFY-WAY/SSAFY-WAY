package com.ssafy.ssafyway.wishlist.domain;

import com.ssafy.ssafyway.house.domain.House;
import com.ssafy.ssafyway.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;

    @Builder
    public Wishlist(Member member, House house) {
        addRelatedMember(member);
        addRelatedByHouse(house);
    }

    private void addRelatedMember(Member member) {
        this.member = member;
        member.getWishlists().add(this);
    }

    private void addRelatedByHouse(House house) {
        this.house = house;
        house.getWishlists().add(this);
    }

    public void removeRelated() {
        member.getWishlists().remove(this);
        house.getWishlists().remove(this);
    }
}

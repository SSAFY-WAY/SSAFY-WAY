package com.ssafy.ssafyway.house.domain;

import com.ssafy.ssafyway.building.domain.Building;
import com.ssafy.ssafyway.global.domain.BaseEntity;
import com.ssafy.ssafyway.wishlist.domain.Wishlist;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "house")
public class House extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private int id;
    private int price; // 만원 단위
    private double area;
    private int floor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;

    @OneToMany(mappedBy = "house", fetch = FetchType.LAZY)
    private List<Wishlist> wishlists = new ArrayList<>();

    @Builder
    public House(int price, double area, int floor, Building building) {
        this.price = price;
        this.area = area;
        this.floor = floor;
        addRelatedByGeo(building);
    }

    private void addRelatedByGeo(Building building) {
        this.building = building;
        building.getHouseList().add(this);
    }
}

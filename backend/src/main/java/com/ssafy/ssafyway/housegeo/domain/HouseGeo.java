package com.ssafy.ssafyway.housegeo.domain;

import com.ssafy.ssafyway.global.domain.BaseEntity;
import com.ssafy.ssafyway.housedetail.domain.HouseDetail;
import com.ssafy.ssafyway.region.domain.Region;
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
@Table(
        name = "geo",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "house_geo_identifier",
                        columnNames = {"bobn", "bubn"})
        })
public class HouseGeo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_geo_id")
    private int id;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private int bobn; // 본번

    @Column(nullable = false)
    private int bubn; // 부번

    @Column(nullable = false)
    private int constructionYear;

    @Column(length = 100, nullable = false)
    private String buildingName;

    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private BuildingType buildingType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(mappedBy = "houseGeo", fetch = FetchType.LAZY)
    private List<HouseDetail> houseDetailList = new ArrayList<>();

    @Builder
    public HouseGeo(
            Region region,
            double longitude,
            double latitude,
            int bobn,
            int bubn,
            String buildingName,
            int constructionYear,
            BuildingType buildingType) {
        addRelatedByRegion(region);
        this.longitude = longitude;
        this.latitude = latitude;
        this.bobn = bobn;
        this.bubn = bubn;
        this.buildingName = buildingName;
        this.buildingType = buildingType;
        this.constructionYear = constructionYear;
    }

    public void addRelatedByRegion(Region region) {
        this.region = region;
        region.getHouseGeoList().add(this);
    }
}

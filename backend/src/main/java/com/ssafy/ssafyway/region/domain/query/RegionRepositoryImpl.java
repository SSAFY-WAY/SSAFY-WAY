package com.ssafy.ssafyway.region.domain.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.ssafyway.region.data.cond.RegionFilterCond;
import com.ssafy.ssafyway.region.data.vo.DistrictVO;
import com.ssafy.ssafyway.region.data.vo.QDistrictVO;
import com.ssafy.ssafyway.region.domain.Region;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.ssafy.ssafyway.region.domain.QRegion.region;

public class RegionRepositoryImpl implements RegionRepositoryCustom {
    private final JPAQueryFactory query;

    public RegionRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Region> findByFilterCond(RegionFilterCond cond) {
        Region findRegion = query.selectFrom(region)
                .where(region.districtCode.eq(cond.getDistrictCode()))
                .where(region.legalDongCode.eq(cond.getLegalDongCode()))
                .fetchOne();
        return Optional.ofNullable(findRegion);
    }

    @Override
    public List<DistrictVO> findAllToDistrict() {
        return query.select(new QDistrictVO(
                        region.districtName,
                        region.districtCode
                ))
                .from(region)
                .groupBy(region.districtCode, region.districtName)
                .fetch();
    }

    @Override
    public List<Region> findAllToLegalDong(int districtCode) {
        return query.selectFrom(region)
                .where(region.districtCode.eq(districtCode))
                .fetch();
    }
}

package com.ssafy.ssafyway.region.data.vo;

import com.ssafy.ssafyway.region.domain.Region;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
public class LegalDongVO {
    private String name;
    private int code;

    public static LegalDongVO from(Region region) {
        return LegalDongVO.builder()
                .code(region.getLegalDongCode())
                .name(region.getLegalDongName())
                .build();
    }
}

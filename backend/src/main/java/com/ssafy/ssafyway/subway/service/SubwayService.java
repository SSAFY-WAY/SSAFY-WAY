package com.ssafy.ssafyway.subway.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.ssafyway.subway.data.SubwayVO;
import com.ssafy.ssafyway.subway.domain.Subway;
import com.ssafy.ssafyway.subway.domain.SubwayRepository;
import com.ssafy.ssafyway.subway.exception.SubwayErrorCode;
import com.ssafy.ssafyway.subway.exception.SubwayException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class SubwayService {

    private final SubwayRepository subwayRepository;

    @Transactional
    public void loadSubwayData() throws IOException {
        log.info("loadSubwayData method start");

        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("지하철역.json");
        List<SubwayVO> subwayVOs = objectMapper.readValue(resource.getInputStream(), new TypeReference<>(){});

        saveSubwayData(subwayVOs);
        log.info("loadSubwayData method end");
    }

    private void saveSubwayData(List<SubwayVO> subwayVOs) {
        subwayVOs.stream()
                .filter(subwayVO -> !subwayRepository.existsByName(subwayVO.getName()))
                .map(subwayVO -> Subway.of(
                    subwayVO.getName(),
                    Double.parseDouble(subwayVO.getY()),
                    Double.parseDouble(subwayVO.getX())))
                .forEach(subwayRepository::save);
    }

    public List<Subway> getSubwayList(List<String> subwayNameList) {
        return subwayNameList.stream()
                .map(this::findByName)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Subway findByName(String name) {
       return subwayRepository.findSubwayByName(name)
               .orElseThrow(() -> new SubwayException(SubwayErrorCode.ERROR_CLIENT_BY_SUBWAY_IS_NOT_EXIST));
    }
}

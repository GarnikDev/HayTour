package com.sevitours.demo.guide.services;

import com.sevitours.demo.Query;
import com.sevitours.demo.guide.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GetGuideService implements Query<Void, List<GuideDto>> {

    private final GuideRepository guideRepository;
    private final GuideMapper guideMapper;

    public GetGuideService(GuideRepository guideRepository,
                           GuideMapper guideMapper) {
        this.guideRepository = guideRepository;
        this.guideMapper = guideMapper;
    }

    @Override
    public ResponseEntity<List<GuideDto>> execute(Void input){
        List<GuideDto> guideDtos = guideRepository.findAll()
                .stream()
                .map(guideMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(guideDtos);
    }
}

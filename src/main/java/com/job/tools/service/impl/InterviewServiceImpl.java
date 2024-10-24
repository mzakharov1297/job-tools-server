package com.job.tools.service.impl;

import com.job.tools.dto.InterviewDto;
import com.job.tools.service.InterviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class InterviewServiceImpl implements InterviewService {

    @Override
    public InterviewDto findOneByUUID(UUID uuid) {
        return null;
    }

    @Override
    public UUID save(InterviewDto interviewDto) {
        return null;
    }

    @Override
    public void deleteByUUID(UUID uuid) {
        log.info("I'm gonna delete it!!!");
    }
}

package com.job.tools.service;

import com.job.tools.dto.InterviewDto;

import java.util.UUID;

public interface InterviewService {

    InterviewDto findOneByUUID(UUID uuid);

    UUID save(InterviewDto interviewDto);

    void deleteByUUID(UUID uuid);

    void update(UUID uuid, InterviewDto interviewDto);

    void partialUpdate(UUID uuid, InterviewDto interviewDto);
}

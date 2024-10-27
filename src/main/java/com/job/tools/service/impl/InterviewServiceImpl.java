package com.job.tools.service.impl;

import com.job.tools.dto.InterviewDto;
import com.job.tools.entity.InterviewEntity;
import com.job.tools.mapper.InterviewMapper;
import com.job.tools.repository.InterviewRepository;
import com.job.tools.service.InterviewService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;

    private final InterviewMapper interviewMapper;

    @Override
    public InterviewDto findOneByUUID(final UUID uuid) {
        return interviewRepository.findByUuid(uuid)
                .map(interviewMapper::entityToDto)
                .orElseThrow(() -> {
                            log.error("Interview by UUID {} not found.", uuid);
                            return new EntityNotFoundException("Interview by UUID %s not found.".formatted(uuid));
                        }
                );
    }

    @Override
    @Transactional
    public UUID save(final InterviewDto interviewDto) {
        final var interviewEntity = interviewMapper.dtoToEntity(interviewDto);
        return interviewRepository.save(interviewEntity).getUuid();
    }

    @Override
    @Transactional
    public void deleteByUUID(final UUID uuid) {
        try {
            interviewRepository.deleteById(uuid);
            log.info("Interview with UUID {} successfully deleted", uuid);
        } catch (EmptyResultDataAccessException e) {
            log.error("Failed to delete interview with UUID {} - UUID does not exist", uuid);
            throw new EntityNotFoundException("Interview with UUID " + uuid + " does not exist");
        }
    }

    @Override
    @Transactional
    public void update(final UUID uuid, final InterviewDto interviewDto) {
        findOneByUUID(uuid);
        interviewRepository.save(interviewMapper.dtoToEntity(interviewDto));
        log.info("Interview with UUID {} successfully updated", uuid);
    }

    @Override
    @Transactional
    public void partialUpdate(final UUID uuid, final InterviewDto interviewDto) {
        final var interview = findOneByUUID(uuid);
        InterviewEntity interviewEntity = interviewMapper.updateInterviewFromDto(interviewDto,
                interviewMapper.dtoToEntity(interview));
        interviewRepository.save(interviewEntity);
    }
}

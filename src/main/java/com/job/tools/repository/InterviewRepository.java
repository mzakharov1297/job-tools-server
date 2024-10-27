package com.job.tools.repository;

import com.job.tools.entity.InterviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InterviewRepository extends JpaRepository<InterviewEntity, UUID> {

    Optional<InterviewEntity> findByUuid(UUID uuid);
}

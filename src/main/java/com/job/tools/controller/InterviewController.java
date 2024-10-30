package com.job.tools.controller;

import com.job.tools.dto.InterviewDto;
import com.job.tools.service.InterviewService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
@RequestMapping("/v1/interview")
public class InterviewController {

    private final InterviewService interviewService;

    @GetMapping("/{uuid}")
    public ResponseEntity<InterviewDto> findOne(@NotNull(message = "UUID must not be null or empty") @PathVariable final UUID uuid) {
        log.info("Incoming request for getting an interview by uuid: {}", uuid);
        return ResponseEntity.ok(interviewService.findOneByUUID(uuid));
    }

    @PostMapping
    public ResponseEntity<UUID> create(@Valid @RequestBody @NotNull final InterviewDto interviewDto) {
        log.info("Create a new interview: {}", interviewDto);
        return ResponseEntity.ok(interviewService.save(interviewDto));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Void> update(@NotNull(message = "UUID must not be null or empty") @PathVariable final UUID uuid,
                                       @Valid @RequestBody @NotNull final InterviewDto interviewDto) {
        log.info("Incoming request for updating an interview by uuid: {}. New interview: {}", uuid, interviewDto);
        interviewService.update(uuid, interviewDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<Void> partialUpdate(@NotNull(message = "UUID must not be null or empty") @PathVariable final UUID uuid,
                                              @Valid @RequestBody @NotNull final InterviewDto interviewDto) {
        log.info("Incoming request for partial updating an interview by uuid: {}. New interview: {}", uuid, interviewDto);
        interviewService.partialUpdate(uuid, interviewDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@NotNull(message = "UUID must not be null or empty") @PathVariable final UUID uuid) {
        log.info("Incoming request for deleting an interview by uuid: {}", uuid);
        interviewService.deleteByUUID(uuid);
        return ResponseEntity.ok().build();
    }
}

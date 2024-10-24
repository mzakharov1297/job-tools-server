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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<InterviewDto> findOne(@Valid @NotNull @PathVariable UUID uuid) {
        log.info("Incoming request for getting an interview by uuid: {}", uuid);
        return ResponseEntity.ok(interviewService.findOneByUUID(uuid));
    }

    @PostMapping("/create")
    public ResponseEntity<UUID> create(@Valid @NotNull InterviewDto interviewDto) {
        log.info("Create a new interview: {}", interviewDto);
        return ResponseEntity.ok(interviewService.save(interviewDto));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@Valid @NotNull @PathVariable UUID uuid) {
        log.info("Incoming request for deleting an interview by uuid: {}", uuid);
        interviewService.deleteByUUID(uuid);
        return ResponseEntity.ok().build();
    }
}

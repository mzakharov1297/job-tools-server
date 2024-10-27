package com.job.tools.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public record StageDto(@NotBlank String key, @NotBlank String stageName, @NotNull OffsetDateTime stageDate,
                       @NotBlank String stageComment) {
}

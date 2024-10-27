package com.job.tools.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewDto {

    @NotNull
    private UUID uuid;

    @NotBlank
    private String company;

    @NotBlank
    private String vacancyLink;

    @NotBlank
    private String recruterName;

    @Nullable
    private String recruterTgUsername;

    @Nullable
    private String recruterWaNumber;

    @Nullable
    private String recruterPhoneNumber;

    @NotNull
    private SalaryDto salaryDto;

    @NotEmpty
    private List<@NotNull StageDto> stageDtos;

    @NotNull
    private Result result;
}

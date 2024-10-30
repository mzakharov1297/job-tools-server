package com.job.tools.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewDto {

    private UUID uuid;

    @NotBlank
    @Length(max = 255)
    private String company;

    @NotBlank
    @Length(min = 15, max = 2048)
    private String vacancyLink;

    @NotBlank
    @Length(max = 255)
    private String recruterName;

    @NotBlank
    @Length(max = 200)
    private String recruterTgUsername;

    @NotBlank
    @Length(min= 13, max = 15)
    private String recruterWaNumber;

    @NotBlank
    @Length(min= 13, max = 15)
    private String recruterPhoneNumber;

    @NotNull
    private SalaryDto salary;

    private StageDto stages;

    @NotNull
    private Result result;
}

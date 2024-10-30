package com.job.tools.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.job.tools.dto.InterviewDto;
import com.job.tools.dto.Result;
import com.job.tools.dto.SalaryDto;
import com.job.tools.dto.StageDto;
import com.job.tools.entity.InterviewEntity;
import com.job.tools.exception.MappingException;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface InterviewMapper {

    ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .registerModule(new JavaTimeModule());

    @Mapping(source = "salary", target = "salary", qualifiedByName = "salaryJsonToDto")
    @Mapping(source = "stages", target = "stages", qualifiedByName = "stagesJsonToDtoList")
    InterviewDto entityToDto(InterviewEntity interviewEntity);

    @Mapping(source = "salary", target = "salary", qualifiedByName = "salaryDtoToJson")
    @Mapping(source = "stages", target = "stages", qualifiedByName = "stagesDtoListToJson")
    @Mapping(source = "result", target = "result", qualifiedByName = "mapStringToResult")
    InterviewEntity dtoToEntity(InterviewDto interviewDto);

    @Mapping(target = "salary", source = "salary", qualifiedByName = "salaryDtoToJson")
    @Mapping(target = "stages", source = "stages", qualifiedByName = "stagesDtoListToJson")
    @Mapping(source = "result", target = "result", qualifiedByName = "mapStringToResult")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InterviewEntity updateInterviewFromDto(InterviewDto dto, @MappingTarget InterviewEntity entity);

    @Named("mapStringToResult")
    default Result mapResult(String stringResult) {
        try {
            return Result.valueOf(stringResult);
        } catch (IllegalArgumentException ex) {
            throw new MappingException("Error converting result JSON to the actual Result");
        }
    }

    @Named("salaryJsonToDto")
    default SalaryDto salaryJsonToDto(String salaryJson) {
        try {
            return OBJECT_MAPPER.readValue(salaryJson, SalaryDto.class);
        } catch (JsonProcessingException e) {
            throw new MappingException("Error converting salary JSON to SalaryDto");
        }
    }

    @Named("salaryDtoToJson")
    default String salaryDtoToJson(SalaryDto salaryDto) {
        try {
            return OBJECT_MAPPER.writeValueAsString(salaryDto);
        } catch (JsonProcessingException e) {
            throw new MappingException("Error converting SalaryDto to JSON");
        }
    }

    @Named("stagesJsonToDtoList")
    default StageDto stagesJsonToDto(String stageJson) {
        try {
            return OBJECT_MAPPER.readValue(stageJson, StageDto.class);
        } catch (JsonProcessingException e) {
            throw new MappingException("Error converting stage JSON to StageDto");
        }

    }

    @Named("stagesDtoListToJson")
    default String stageDtoToJson(StageDto stageDto) {
        try {
            return OBJECT_MAPPER.writeValueAsString(stageDto);
        } catch (JsonProcessingException e) {
            throw new MappingException("Error converting StageDto to JSON");
        }
    }
}

package com.job.tools.entity;

import com.job.tools.dto.Result;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "interview", schema = "jt")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InterviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "vacancy_link", nullable = false, length = 2048)
    private String vacancyLink;

    @Column(name = "recruter_name", nullable = false)
    private String recruterName;

    @Column(name = "recruter_tg_username", nullable = false, length = 200)
    private String recruterTgUsername;

    @Column(name = "recruter_wa_number", nullable = false, length = 15)
    private String recruterWaNumber;

    @Column(name = "recruter_phone_number", nullable = false, length = 15)
    private String recruterPhoneNumber;

    @Column(name = "salary", nullable = false, columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private String salary;

    @Column(name = "stage", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private String stages;

    @Enumerated(EnumType.STRING)
    @Column(name = "result", nullable = false)
    private Result result;
}

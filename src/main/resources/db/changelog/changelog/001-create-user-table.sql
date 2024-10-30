--liquibase formatted sql

--changeset Denis Ch.:0
--comment: Create schema jt and set search path
CREATE SCHEMA IF NOT EXISTS jt;
SET SEARCH_PATH TO jt;

--changeset Denis Ch.:1
--comment: Create interview table
CREATE TABLE IF NOT EXISTS jt.interview
(
    uuid                  UUID PRIMARY KEY,
    company               VARCHAR(255)  NOT NULL,
    vacancy_link          VARCHAR(2048) NOT NULL,
    recruter_name         VARCHAR(255)  NOT NULL,
    recruter_tg_username  VARCHAR(200)  NOT NULL,
    recruter_wa_number    VARCHAR(15)   NOT NULL,
    recruter_phone_number VARCHAR(15)   NOT NULL,
    salary                JSONB         NOT NULL,
    stage                 JSONB,
    result                VARCHAR(50)   NOT NULL
);

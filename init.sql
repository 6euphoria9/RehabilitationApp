--DROP DATABASE IF EXISTS application;
--CREATE DATABASE application;

CREATE TABLE IF NOT EXISTS doctor
(
    doctor_id  SERIAL       NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    PRIMARY KEY (doctor_id)
);

CREATE TABLE IF NOT EXISTS tasks
(
  task_id   SERIAL NOT NULL,
  task_description VARCHAR(255) NOT NULL ,
  frequency BIGINT NOT NULL ,
  effort    VARCHAR(255) NOT NULL ,
  duration BIGINT NOT NULL,
  PRIMARY KEY (task_id)
);

CREATE TABLE IF NOT EXISTS subject
(
  subject_id SERIAL NOT NULL ,
  subject_description VARCHAR(255) NOT NULL ,
  PRIMARY KEY (subject_id)
);

CREATE TABLE IF NOT EXISTS metrics
(
    metric_id SERIAL NOT NULL ,
    task_id BIGINT NOT NULL ,
    PRIMARY KEY (metric_id),
    FOREIGN KEY (task_id) REFERENCES tasks
);

CREATE TABLE IF NOT EXISTS patients
(
    patient_id SERIAL       NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    address    VARCHAR(255),
    city       VARCHAR(255),
    doctor_id  BIGINT       NOT NULL,
    PRIMARY KEY (patient_id),
    FOREIGN KEY (doctor_id) REFERENCES doctor
);

CREATE TABLE IF NOT EXISTS prescription
(
  prescription_id SERIAL NOT NULL,
  task_id BIGINT NOT NULL,
  metric_id BIGINT NOT NULL,
  description VARCHAR(255),
  PRIMARY KEY (prescription_id),
  FOREIGN KEY (task_id) REFERENCES tasks ,
  FOREIGN KEY (metric_id) REFERENCES metrics
);



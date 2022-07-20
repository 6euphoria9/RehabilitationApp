--DROP DATABASE IF EXISTS application;
--CREATE DATABASE application;

CREATE TABLE IF NOT EXISTS "user"
(
    user_id  int generated by default as identity primary key,
    username varchar(100) NOT NULL UNIQUE ,
    password varchar(100) NOT NULL,
    "role"   varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS doctor
(
    doctor_id  SERIAL       NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    user_id    int          NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user",
    PRIMARY KEY (doctor_id)

);


CREATE TABLE IF NOT EXISTS patient
(
    patient_id SERIAL       NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    address    VARCHAR(255),
    city       VARCHAR(255),
    doctor_id  BIGINT       NOT NULL,
    user_id    int          NOT NULL,
    PRIMARY KEY (patient_id),
    FOREIGN KEY (doctor_id) REFERENCES doctor,
    FOREIGN KEY (user_id) REFERENCES "user"
);


CREATE TABLE IF NOT EXISTS task
(
    task_id     SERIAL NOT NULL,
    description VARCHAR(255),
    PRIMARY KEY (task_id)
);

CREATE TABLE IF NOT EXISTS metric
(
    metric_id SERIAL NOT NULL,
    weekly    BIGINT,
    daily     BIGINT,
    sets      BIGINT,
    reps      BIGINT,
    duration  BIGINT,
    task_id   int    NOT NULL,
    PRIMARY KEY (metric_id),
    FOREIGN KEY (task_id) REFERENCES task (task_id)
);

CREATE TABLE IF NOT EXISTS prescription
(
    prescription_id SERIAL       NOT NULL,
    name            VARCHAR(255) NOT NULL,
    date            TIMESTAMP    NOT NULL,
    task_id         BIGINT       NOT NULL,
    metric_id       BIGINT       NOT NULL,
    patient_id      BIGINT       NOT NULL,
    PRIMARY KEY (prescription_id),
    FOREIGN KEY (task_id) REFERENCES task (task_id),
    FOREIGN KEY (metric_id) REFERENCES metric (metric_id),
    FOREIGN KEY (patient_id) REFERENCES patient (patient_id)
);

CREATE TABLE IF NOT EXISTS patient_mail
(
    prescription_id BIGINT NOT NULL,
    patient_id      BIGINT NOT NULL,
    FOREIGN KEY (prescription_id) REFERENCES prescription (prescription_id),
    FOREIGN KEY (patient_id) REFERENCES patient (patient_id)
);

CREATE TABLE IF NOT EXISTS feedback
(
    feedback_id SERIAL NOT NULL,
    patient_id  BIGINT NOT NULL,
    task_id     BIGINT NOT NULL,
    metric_id   BIGINT NOT NULL,
    PRIMARY KEY (feedback_id),
    FOREIGN KEY (patient_id) REFERENCES patient (patient_id),
    FOREIGN KEY (task_id) REFERENCES task (task_id),
    FOREIGN KEY (metric_id) REFERENCES metric (metric_id)
);

CREATE TABLE IF NOT EXISTS doctor_mail
(
    mail_id SERIAL NOT NULL,
    doctor_id   BIGINT NOT NULL,
    feedback_id BIGINT NOT NULL,
    FOREIGN KEY (doctor_id) REFERENCES doctor (doctor_id),
    FOREIGN KEY (feedback_id) REFERENCES feedback (feedback_id)
);




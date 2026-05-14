CREATE TABLE customers (
                           id            VARCHAR(36)  NOT NULL,
                           leads_id      VARCHAR(36),
                           name          VARCHAR(255),
                           first_name    VARCHAR(255),
                           middle_name   VARCHAR(255),
                           last_name     VARCHAR(255),
                           mobile        VARCHAR(255),
                           dob           DATE,
                           pan_number    VARCHAR(255),
                           father_name   VARCHAR(255),
                           email         VARCHAR(255),
                           gender        INT,
                           marital_status INT,
                           education     VARCHAR(255),
                           created_at    DATETIME,
                           updated_at    DATETIME,
                           PRIMARY KEY (id)
);
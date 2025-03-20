use calendar;

CREATE TABLE calendar
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    todo VARCHAR(100) NOT NULL,
    writer_name VARCHAR(50) NOT NULL,
    password BIGINT NOT NULL,
    reg_date TIMESTAMP,
    mod_date TIMESTAMP
);

ALTER TABLE calendar MODIFY COLUMN password VARCHAR(50) NOT NULL;
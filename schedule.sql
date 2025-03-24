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

ALTER TABLE calendar DROP COLUMN writer_name;

ALTER TABLE calendar DROP COLUMN password;

CREATE TABLE writer
(
    writer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    writer_name VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL
);

ALTER TABLE calendar ADD COLUMN writer_id BIGINT;

ALTER TABLE calendar ADD CONSTRAINT fk_writer FOREIGN KEY (writer_id) REFERENCES writer(writer_id);
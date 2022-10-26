CREATE SCHEMA test;
USE test;
CREATE TABLE transactions (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `transaction_date` DATE,
    `vendor` varchar(255) NOT NULL,
    `amount` DECIMAL NOT NULL,
    `category` varchar(255)
);
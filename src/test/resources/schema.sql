CREATE SCHEMA IF NOT EXISTS test;
USE test;
DROP TABLE IF EXISTS transactions;
CREATE TABLE IF NOT EXISTS transactions
(
    `id`               BIGINT AUTO_INCREMENT PRIMARY KEY,
    `transaction_date` DATE,
    `vendor`           varchar(255) NOT NULL,
    `amount`           DECIMAL      NOT NULL,
    `category`         varchar(255)
);
DROP TABLE IF EXISTS transactions CASCADE;
CREATE TABLE IF NOT EXISTS transactions
(
    `id`               BIGINT AUTO_INCREMENT PRIMARY KEY,
    `transaction_date` DATE,
    `vendor`           varchar(255)    NOT NULL,
    `amount`           DECIMAL(6,2) NOT NULL,
    `category`         varchar(255)
);
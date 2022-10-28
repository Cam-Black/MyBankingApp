INSERT INTO `transactions` (`transaction_date`, `vendor`, `amount`, `category`)
VALUES ('2022-03-15', 'WHSmiths', 10.50, 'Leisure'),
       ('2022-05-16', 'Co-Op', 10.50, 'Groceries'),
       ('2022-08-21', 'Tesco', 10.30, 'Groceries'),
       (CURRENT_DATE(), 'Car Finance', 200.00, 'Direct Debit'),
       ('2022-05-04', 'Car Finance', 212.43, 'Direct Debit'),
       (CURRENT_DATE(), 'Credit Card', 100.00, 'Direct Debit');

INSERT INTO `transactions` (`transaction_date`, `vendor`, `amount`)
VALUES ('2021-05-14', 'Waterstones', 15.99);
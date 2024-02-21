DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
    account_id SERIAL PRIMARY KEY,
    customer_id INT,
    balance DECIMAL(10, 2)
);

INSERT INTO accounts (customer_id, balance) VALUES (123, 2000.00);
INSERT INTO accounts (customer_id, balance) VALUES (123, 1500.50);
INSERT INTO accounts (customer_id, balance) VALUES (124, 1800.75);

--Print to Terminal
--psql -h localhost -U postgres -d bank -c "SELECT * FROM accounts;"

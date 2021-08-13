
DROP TABLE IF EXISTS raw_customers CASCADE;

CREATE TABLE raw_customers (
  id INT,
  first_name VARCHAR(255),
  last_name VARCHAR(255)
);

COPY raw_customers (id, first_name, last_name) 
FROM '/var/lib/postgresql/csv/raw_customers.csv' DELIMITER ',' CSV HEADER;


DROP TABLE IF EXISTS raw_orders CASCADE;

CREATE TABLE raw_orders (
  id INT,
  user_id INT,
  order_date DATE,
  status VARCHAR(255)
);

COPY raw_orders (id, user_id, order_date, status) 
FROM '/var/lib/postgresql/csv/raw_orders.csv' DELIMITER ',' CSV HEADER;


DROP TABLE IF EXISTS raw_payments CASCADE;

CREATE TABLE raw_payments (
  id INT,
  order_id INT,
  payment_method VARCHAR(255),
  amount INT
);

COPY raw_payments (id, order_id, payment_method, amount) 
FROM '/var/lib/postgresql/csv/raw_payments.csv' DELIMITER ',' CSV HEADER;

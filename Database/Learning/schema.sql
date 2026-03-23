CREATE TABLE categories (
    category_id SERIAL PRIMARY KEY,
    category_name VARCHAR(250) NOT NULL UNIQUE,
    description VARCHAR(250) NOT NULL
);

CREATE TABLE customers (
    customer_id SERIAL PRIMARY KEY,
    customer_name VARCHAR(250) NOT NULL UNIQUE,
    contact_name VARCHAR(250) NOT NULL,
    address VARCHAR(250) NOT NULL,
    city VARCHAR(250) NOT NULL,
    postal_code VARCHAR(250) NOT NULL,
    country VARCHAR(250) NOT NULL
);

CREATE TABLE products (
    product_id SERIAL PRIMARY KEY,
    product_name VARCHAR(250) NOT NULL,
    category_id INTEGER NOT NULL,
    unit VARCHAR(250) NOT NULL,
    price DECIMAL(10,2) NOT NULL
        
);

CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    customer_id INTEGER NOT NULL,
    order_date DATE NOT NULL 
);

CREATE TABLE order_details (
    order_detail_id SERIAL PRIMARY KEY,
    order_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL
);

CREATE TABLE testproducts (
    testproduct_id SERIAL PRIMARY KEY,
    product_name VARCHAR(255),
    category_id INTEGER

);
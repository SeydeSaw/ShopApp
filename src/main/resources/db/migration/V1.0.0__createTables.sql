CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                     username VARCHAR(45),
    password VARCHAR(64),
    first_name VARCHAR(64),
    last_name VARCHAR(64),
    email VARCHAR(64),
    role VARCHAR(64),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS carts (
                                     id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                     client_id BIGINT,
                                     status VARCHAR(64),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    Foreign Key (client_id) REFERENCES users(id)
    );

CREATE TABLE IF NOT EXISTS orders (
                                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                      cart_id BIGINT,
                                      total_price DECIMAL(15,2),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    Foreign Key (cart_id) REFERENCES carts(id)
    );

CREATE TABLE IF NOT EXISTS products (
                                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                        seller_id BIGINT,
                                        name VARCHAR(45) ,
    price DECIMAL(15,2),
    description VARCHAR (100),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    Foreign Key (seller_id) REFERENCES users(id)
    );

CREATE TABLE IF NOT EXISTS order_details(
                                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                            cart_id BIGINT,
                                            product_id BIGINT,
                                            quantity INT,
                                            Foreign Key (cart_id) REFERENCES carts(id),
    Foreign Key (product_id) REFERENCES products(id)
    );
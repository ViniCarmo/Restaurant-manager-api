CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       login VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       modified_date TIMESTAMP NOT NULL,
                       address VARCHAR(255) NOT NULL,
                       user_type VARCHAR(31) NOT NULL
);

CREATE TABLE customers (
                           id INTEGER PRIMARY KEY REFERENCES users(id),
                           cpf VARCHAR(14) NOT NULL
);

CREATE TABLE restaurant_owners (
                                   id INTEGER PRIMARY KEY REFERENCES users(id),
                                   restaurant_name VARCHAR(255) NOT NULL
);
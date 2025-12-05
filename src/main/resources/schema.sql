DROP TABLE IF EXISTS phones;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id BINARY(16) PRIMARY KEY,
    username VARCHAR(45) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL,
    created TIMESTAMP NOT NULL,
    last_login TIMESTAMP NOT NULL,
    name VARCHAR(45)
);

CREATE TABLE authorities (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id BINARY(16) NOT NULL,
    authority VARCHAR(45) NOT NULL UNIQUE,
    CONSTRAINT fk_authorities_users FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE phones (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id BINARY(16) NOT NULL,
    number BIGINT,
    city_code INTEGER,
    country_code VARCHAR(45),
    CONSTRAINT fk_phones_users FOREIGN KEY (user_id) REFERENCES users(id)
);

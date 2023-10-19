CREATE TABLE users (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        uuid VARCHAR(255),
                        name VARCHAR(255),
                        email VARCHAR(255),
                        password VARCHAR(255),
                        token VARCHAR(255),
                        is_active BIT,
                        last_login TIMESTAMP,
                        created_date TIMESTAMP,
                        updated_date TIMESTAMP
);

CREATE TABLE phone (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       number VARCHAR(255),
                       city_code VARCHAR(255) ,
                       country_code VARCHAR(255),
                       user_id BIGINT,
                       CONSTRAINT fk_phone_user FOREIGN KEY (user_id) REFERENCES users(id)
);

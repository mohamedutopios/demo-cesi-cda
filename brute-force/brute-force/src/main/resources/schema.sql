CREATE TABLE IF NOT EXISTS Person (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    account_non_locked BOOLEAN NOT NULL
    );

CREATE TABLE IF NOT EXISTS LOGIN_ATTEMPT (
                                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                             username VARCHAR(50) NOT NULL,
    timestamp TIMESTAMP NOT NULL
    );


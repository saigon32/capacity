--mysql
CREATE TABLE IF NOT EXISTS capacity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(90) NOT NULL,
    UNIQUE (name)
);
--postgres
CREATE TABLE IF NOT EXISTS capacity (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(90) NOT NULL,
    UNIQUE (name)
);
--postgres
CREATE TABLE IF NOT EXISTS capacity_bootcamp (
    id_capacity VARCHAR(50) NOT NULL,
    id_bootcamp VARCHAR(50) NOT NULL
);

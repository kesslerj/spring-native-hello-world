CREATE TABLE customers
(
    id         UUID         NOT NULL
        PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    age        INTEGER      NOT NULL
);

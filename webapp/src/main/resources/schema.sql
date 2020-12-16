CREATE TABLE persons (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
    last_name varchar(80) NOT NULL,
    first_name varchar(80) NOT NULL,
    middle_name varchar(80),
    position varchar(255) NOT NULL
);
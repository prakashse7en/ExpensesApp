CREATE TABLE user (
    user_id binary(16) not null primary key,
    user_name VARCHAR(255),
    user_phone_number VARCHAR(255),
    user_email VARCHAR(255),
    record_created_date_time TIMESTAMP,
    record_updated_date_time TIMESTAMP
);
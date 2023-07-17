-- try dropping table if table already exist with same name --
drop table "user";
drop table role;
drop table product;

-- Create table for User
CREATE TABLE "user" (
    id NUMBER(19,0) PRIMARY KEY,
    fullname VARCHAR2(255),
    username VARCHAR2(255),
    email VARCHAR2(255),
    password VARCHAR2(255)
);

-- Create table for Role
CREATE TABLE role (
    id NUMBER(19,0) PRIMARY KEY,
    name VARCHAR2(255)
);

-- Create join table for many-to-many relationship between User and Role
CREATE TABLE user_role (
    user_id NUMBER(19,0) NOT NULL,
    role_id NUMBER(19,0) NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES "user" (id),
    FOREIGN KEY (role_id) REFERENCES role (id)
);

-- Create table for Product
CREATE TABLE product (
    id NUMBER(19,0) PRIMARY KEY,
    userName VARCHAR2(255),
    PRODUCT_NAME VARCHAR2(400),
    PRODUCT_IMAGE_SRC VARCHAR2(255),
    description VARCHAR2(1000),
    PRODUCT_LINK VARCHAR2(1000)
);

-- try adding properties below to automatic load schema
-- spring.datasource.initialization-mode=always
-- put schema and data sql file into resources folder


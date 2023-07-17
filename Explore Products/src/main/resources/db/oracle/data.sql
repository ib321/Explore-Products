REM INSERTING into "user"
SET DEFINE OFF;
Insert into "user" (ID,PASSWORD,USERNAME,FULLNAME,EMAIL) values (1,'$2a$10$JR3bN1Wg3a1Yrto5JLOsmOZJfwDya9hKlmK6diFqnTiOjZdrqnJZ6','admin1','Indian Bittu','321abc@ib.com');
-- Credentials: admin1 - 12345678

REM INSERTING into PRODUCT
SET DEFINE OFF;
Insert into PRODUCT (ID,DESCRIPTION,PRODUCT_IMAGE_SRC,PRODUCT_LINK,PRODUCT_NAME,USER_NAME) values (15,'New Mi a3 mobile','https://m.media-amazon.com/images/I/81MP+KiwLlL._SY741_.jpg','https://amzn.eu/d/g4wZl6e','Mi A3 Mobile','admin1');
Insert into PRODUCT (ID,DESCRIPTION,PRODUCT_IMAGE_SRC,PRODUCT_LINK,PRODUCT_NAME,USER_NAME) values (23,'New Moto edge 30','https://m.media-amazon.com/images/I/41audMQ66qL._SX679_.jpg','https://amzn.eu/d/g4wZl6e','Moto Edge 30','admin1');

commit;

-- try adding properties below to automatic load schema
-- spring.datasource.initialization-mode=always
-- put schema and data sql file into resources folder
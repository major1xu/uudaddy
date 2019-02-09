# https://www.w3schools.com/php/php_mysql_create_table.asp
USE turtle;
CREATE TABLE AccessLog (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
startDate TIMESTAMP NOT NULL,
IP VARCHAR(30) NOT NULL,
method VARCHAR(30) NOT NULL,
statusCode VARCHAR(3) NOT NULL,
browser VARCHAR(100) NOT NULL
);
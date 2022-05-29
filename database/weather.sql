CREATE DATABASE IF NOT EXISTS weather;
ALTER DATABASE weather DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
CREATE USER 'root'@'localhost' IDENTIFIED BY 'Linh1234';
GRANT ALL PRIVILEGES ON weather.* TO 'root'@'localhost';
USE weather;
CREATE TABLE IF NOT EXISTS weather_report
(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
base VARCHAR(20),
visibility INT,
dt INT,
timezone INT,
city_id INT,
city_name VARCHAR(20),
cod INT,
coord json,
weather json,
main json,
wind json,
clouds json,
sys json
);
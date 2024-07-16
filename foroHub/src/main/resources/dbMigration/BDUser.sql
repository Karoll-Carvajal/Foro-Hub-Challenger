CREATE TABLE  User(

    id BIGIN NOT NULL AUTO_INCREMENT PRIMARY KEY,
    login varchar(100) NOT NULL UNIQUE,
    password varchar(300) NOT NULL,

);
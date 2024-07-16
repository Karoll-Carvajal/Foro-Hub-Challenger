CREATE TABLE Topic(

    id BIGIN NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL UNIQUE,
    message VARCHAR(500) NOT NULL UNIQUE,
    creationDate DATETIME NOT NULL,
    status VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    course VARCHAR(100) NOT NULL,

);
private Long id;



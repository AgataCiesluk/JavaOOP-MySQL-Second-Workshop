# Utwórz zapytanie tworzące tabelę users a następnie wywołaj je na bazie danych.
CREATE TABLE IF NOT EXISTS users(
                      id INT(11) NOT NULL AUTO_INCREMENT,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      username VARCHAR(255) NOT NULL,
                      password VARCHAR(60) NOT NULL,
                      PRIMARY KEY (id)
);

DESCRIBE users;

SELECT MAX(id) FROM users;
SELECT id FROM users WHERE id = 1;
CREATE DATABASE voting_system;
USE voting_system;

CREATE TABLE voters (
    id INT PRIMARY KEY,
    has_voted BOOLEAN DEFAULT FALSE
);

CREATE TABLE candidates (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    votes INT DEFAULT 0
);

INSERT INTO voters VALUES (101, FALSE), (102, FALSE);
INSERT INTO candidates (name) VALUES ('Candidate A'), ('Candidate B');

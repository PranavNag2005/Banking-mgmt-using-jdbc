create database Bankingsystem;
use Bankingsystem;
create table users(id int primary key auto_increment,
name varchar(50),password varchar(20));
alter table users add column accountnumber 
varchar(14) unique;
select *from users;
CREATE TABLE accounts (
    accountnumber VARCHAR(14) PRIMARY KEY,
    id INT,
    balance DECIMAL(10,2),
    FOREIGN KEY (id) REFERENCES users(id)
);
CREATE TABLE transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    accountnumber VARCHAR(14),
    transaction_type VARCHAR(20),
    amount DECIMAL(10,2),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (accountnumber) REFERENCES accounts(accountnumber)
);

select * from transactions;
select * from accounts;


select * from users;

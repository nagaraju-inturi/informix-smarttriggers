create database bank with log;
create table account (id int primary key, balance int) lock mode row;

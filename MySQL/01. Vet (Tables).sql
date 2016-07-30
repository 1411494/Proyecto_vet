
drop database vet;
create database vet;

use vet;

create table persons
(
	dni				char(8),
	first_name		varchar(50),
	last_name		varchar(50),	
	email			varchar(50),
	address			varchar(80),
	phone_number 	varchar(12),
	birth_date		date,
	status			bit
);

create table users
(
	dni				char(8),
    user_name		varchar(8),
    password		varchar(8),
    type			char(1),
    status			bit
);

create table pets
(
	pet_id			char(5),
	dni				char(8),
	pet_name		varchar(20),
	breed			varchar(20),  /* Raza */
	hair_color		varchar(20),  /* Color de pelo */
	birth_date		date,
    image 			varchar(250),
	status			bit

);

create table vaccines
(
	vaccine_id		int,
	pet_id			char(5),
	date			date,
	weight			varchar(10),
	disease			varchar(20),
	next_date		date

);


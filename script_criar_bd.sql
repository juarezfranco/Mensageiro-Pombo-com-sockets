create database pombo;
use pombo;

create table usuario(
	id int auto_increment primary key,
	username varchar(100) not null,
	password varchar(50) not null,
	last_login timestamp default '0000-00-00',
	date_create timestamp default now()
)

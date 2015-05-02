drop database pombo;
create database pombo;
use pombo;

create table usuarios(
	username varchar(100) primary key,
	password varchar(50) not null,
	image integer(10) not null,
	last_login timestamp default '0000-00-00',
	date_create timestamp default now()
);

create table mensagens_pendentes(
	id integer primary key auto_increment,
	remetente varchar(100),
	destinatario varchar(100),
	data timestamp default now(),
	mensagem text,
	foreign key (remetente) references usuarios(username),
	foreign key (destinatario) references usuarios(username)
);

CREATE TABLE categoria (
	id int not null auto_increment primary key,
	nome varchar(50) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria (nome) values ("Lazer");
INSERT INTO categoria (nome) values ("Alimentacao");
INSERT INTO categoria (nome) values ("Esporte");
INSERT INTO categoria (nome) values ("Supermercado");
INSERT INTO categoria (nome) values ("Farmacia");
INSERT INTO categoria (nome) values ("Outros");
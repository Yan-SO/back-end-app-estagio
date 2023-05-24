CREATE TABLE professores (
  id bigint not null auto_increment,
  nome VARCHAR(100) not null,
  email VARCHAR(100) not null UNIQUE ,
  senha VARCHAR(100) not null,

  primary key(id)
);
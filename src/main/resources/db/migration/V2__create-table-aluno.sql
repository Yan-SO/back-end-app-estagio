CREATE TABLE alunos (
  id bigint not null auto_increment,
  RA VARCHAR(100) not null UNIQUE,
  nome VARCHAR(100) not null,
  email VARCHAR(100) not null UNIQUE,
  senha VARCHAR(100) not null,
  professor bigint not null,

  primary key(id),
  FOREIGN KEY (professor) REFERENCES professores(id)
);
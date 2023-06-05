DROP TABLE atividade_respondida;

DROP TABLE atividades_alunos;

DROP TABLE alunos;

CREATE TABLE alunos (
  id bigint not null auto_increment,
  RA VARCHAR(100) not null UNIQUE,
  nome VARCHAR(100) not null,
  email VARCHAR(100) not null UNIQUE,
  senha VARCHAR(100) not null,

  primary key(id)
);

CREATE TABLE professores_alunos (
  id bigint AUTO_INCREMENT PRIMARY KEY,

  aluno bigint not null,
  professor bigint not null,

  FOREIGN KEY (aluno) REFERENCES alunos(id),
  FOREIGN KEY (professor) REFERENCES professores(id)
);


CREATE TABLE atividades_alunos (
  id bigint AUTO_INCREMENT PRIMARY KEY,

  quando DATETIME,
  aluno_resposta BOOLEAN,

  aluno bigint not null,
  atividade bigint not null,

  FOREIGN KEY (aluno) REFERENCES alunos(id),
  FOREIGN KEY (atividade) REFERENCES atividades(id)
);


CREATE TABLE atividades (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) not null,
    pergunta VARCHAR(512) not null,
    criado DATETIME,
    resposta VARCHAR(255) not null,
    alternativa1 VARCHAR(255) not null,
    alternativa2 VARCHAR(255) not null,
    alternativa3 VARCHAR(255) not null,
    professor bigint not null,

    FOREIGN KEY (professor) REFERENCES professores(id)
);

CREATE TABLE atividade_respondida (
  id bigint AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(255),
  aluno_resposta BOOLEAN,
  quando DATETIME,
  aluno bigint,
  professor bigint,
  atividade bigint,
  FOREIGN KEY (aluno) REFERENCES alunos(id),
  FOREIGN KEY (professor) REFERENCES professores(id),
  FOREIGN KEY (atividade) REFERENCES atividades(id)
);

CREATE TABLE atividades_alunos (
  id bigint AUTO_INCREMENT PRIMARY KEY,

  aluno bigint not null,
  atividade bigint not null,

  FOREIGN KEY (aluno) REFERENCES alunos(id),
  FOREIGN KEY (atividade) REFERENCES atividades(id)
);
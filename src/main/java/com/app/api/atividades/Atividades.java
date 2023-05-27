package com.app.api.atividades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Table(name = "atividades")
@Entity(name = "Atividade")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Atividades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String atividade;
    private String resposta;
    private String alternatira1;
    private String alternatira2;
    private String alternatira3;

    private Date primeiraAtividade;
    private Long aluno;
    private Long professor;

}
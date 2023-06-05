package com.app.api.aluno;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "alunos")
@Entity(name = "Aluno")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String RA;
    private String nome;
    private String senha;
    private String email;


    public Aluno(DadosCadastroAluno dados) {
        this.RA = dados.RA();
        this.nome = dados.nome();
        this.senha = dados.senha();
        this.email = dados.email();
    }
}

package com.app.api.atividades.atividade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "atividades")
@Entity(name = "atividade")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String pergunta;
    private Date criado;

    private String resposta;
    private String alternativa1;
    private String alternativa2;
    private String alternativa3;

    private Long professor;

    public Atividade(DadosCadastroAtividades dados, Date data) {
        this.titulo = dados.titulo();
        this.pergunta = dados.pergunta();
        this.criado = data;
        this.resposta = dados.resposta();
        this.alternativa1 = dados.alternativa1();
        this.alternativa2 = dados.alternativa2();
        this.alternativa3 = dados.alternativa3();
        this.professor = dados.professor();
    }

    public void atualizar(DadosAtualizarAtividades dados) {
        if (dados.titulo() != null) this.titulo = dados.titulo();
        if (dados.pergunta() != null) this.pergunta = dados.pergunta();
        if (dados.resposta() != null) this.resposta = dados.resposta();
        if (dados.alternativa1() != null) this.alternativa1 = dados.alternativa1();
        if (dados.alternativa2() != null) this.alternativa2 = dados.alternativa2();
        if (dados.alternativa3() != null) this.alternativa3 = dados.alternativa3();
    }
}

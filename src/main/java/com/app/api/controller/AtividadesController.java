package com.app.api.controller;

import com.app.api.atividade.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/atividades")
public class AtividadesController {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @GetMapping
    public Page<DadosRetornoAtividade> listarTodas(Pageable page){
        return atividadeRepository.findAll(page).map(DadosRetornoAtividade::new);
    }

    @GetMapping("/professor={id}")
    public Page<DadosRetornoAtividade> listarPorProfessor(Pageable page, @PathVariable Long id){
        return atividadeRepository.findByProfessor(page, id).map(DadosRetornoAtividade::new);
    }


    @PostMapping
    @Transactional
    public DadosRetornoAtividade cadastrarAtividade(@RequestBody @Valid DadosCadastroAtividades dados){
        Date data = new Date();
        Atividade atividade =new Atividade(dados, data);
        atividadeRepository.save(atividade);
        return new DadosRetornoAtividade(atividade);
    }

    @PutMapping
    @Transactional
    public DadosRetornoAtividade atualizarAtividades(@RequestBody @Valid DadosAtualizarAtividades dados){
        var atividade = atividadeRepository.getReferenceById(dados.id());
        atividade.atualizar(dados);
        return new DadosRetornoAtividade(atividade);
    }

    @DeleteMapping("/id={id}")
    @Transactional
    public void deletarAtividade(@PathVariable Long id){
        atividadeRepository.deleteById(id);
    }


}

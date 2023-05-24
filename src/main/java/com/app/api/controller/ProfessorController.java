package com.app.api.controller;

import com.app.api.professor.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorRepository repository;

    @GetMapping()
    public Page<DadosRetornoProfessor> listarTodosProfessores(Pageable page){
        return repository.findAll(page).map(DadosRetornoProfessor::new);
    }

    @GetMapping("/login")
    public DadosProfessor loginProfessor(@RequestBody DadosLoginProfessor dados){
        DadosProfessor login;
        List<Professor> retorno =repository.findByEmail(dados.email());
        if(retorno.size() == 0){
            return login = new DadosLoginProfessor("err", null);
        }else {
            if(retorno.get(0).getSenha().equals(dados.senha())){
                return login = new DadosRetornoProfessor(retorno.get(0));
            } else {
                return login = new DadosLoginProfessor(dados.email(), "err");
            }
        }
    }
    @PostMapping
    @Transactional
    public DadosRetornoProfessor cadastroProfessor(@RequestBody DadosCadastroProfessor dados){
        Professor professor = new Professor(dados);
        repository.save(professor);
        return new DadosRetornoProfessor(professor);
    }


    @DeleteMapping("/id={id}")
    @Transactional
    public void deletar(@PathVariable Long id){
        repository.deleteById(id);
    }
}

package com.app.api.controller;

import com.app.api.aluno.Aluno;
import com.app.api.aluno.AlunoRepository;
import com.app.api.aluno.DadosRetornoAluno;
import com.app.api.professor.*;
import com.app.api.relacionamentos.AlunoProfessorRepository;
import com.app.api.relacionamentos.AlunosProfessores;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;



    @GetMapping()
    public Page<DadosRetornoProfessor> listarTodosProfessores(Pageable page){
        return professorRepository.findAll(page).map(DadosRetornoProfessor::new);
    }

    @PutMapping("/login")
    public DadosProfessor loginProfessor(@RequestBody DadosLoginProfessor dados){

        var retorno = professorRepository.findByEmail(dados.email());
        if(retorno.size() == 0){
            return new DadosLoginProfessor("err", null);
        }else {
            if(retorno.get(0).getSenha().equals(dados.senha())){
                return new DadosRetornoProfessor(retorno.get(0));
            } else {
                return new DadosLoginProfessor(dados.email(), "err");
            }
        }
    }



    @PostMapping
    @Transactional
    public DadosRetornoProfessor cadastroProfessor(@RequestBody DadosCadastroProfessor dados){
        Professor professor = new Professor(dados);
        professorRepository.save(professor);
        return new DadosRetornoProfessor(professor);
    }


    @DeleteMapping("/id={id}")
    @Transactional
    public void deletar(@PathVariable Long id){
        professorRepository.deleteById(id);
    }
}

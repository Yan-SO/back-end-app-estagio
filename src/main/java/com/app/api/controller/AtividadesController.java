package com.app.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.GregorianCalendar;

@RestController
@RequestMapping("/atividades")
public class AtividadesController {
    /**
     * é o dia da semana que vai ser criada uma nova semana de atividades
     */
    private Integer newWeek=2;


    /**
     * vai verificar se é uma nova semana de atividades
     * e se é a primeira vez usando app.
     * deve ser usado sempre que iniciar o app.
     */
    @PostMapping("/nova-semana")
    public void novaSemana(){
        var diaAtual = new GregorianCalendar().get(Calendar.DAY_OF_WEEK);
        if(newWeek.equals(diaAtual)){

        }
    }
}

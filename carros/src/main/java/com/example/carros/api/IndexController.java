package com.example.carros.api;

import org.springframework.web.bind.annotation.*;
/* *** RequestParam *** e *** PathVariable *** fazem a mesma coisa só que de uma forma diferente.
 Ambos recebem informações pela URL no entanto de formas diferentes.
 Muito parecido com o conceito de Bind que vimos em Angular.       */

@RestController //transforma a classe em web service rest
@RequestMapping("/") //Vai mapear esse web service. ex: esse web service esta mapeado pelo barra ( / )
public class IndexController {

    @GetMapping() //serve para mapear este metodo. Ele é um atalho para o RequestMapping
    public String get(){
        return "Api dos Carros";
    }


}

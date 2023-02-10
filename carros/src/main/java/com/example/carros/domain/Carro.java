package com.example.carros.domain;

import lombok.*;

import javax.persistence.*;

@Entity(name = "carro") //Mapeia a nossa classe carro com a tabela carro do banco de dados.
@Data
public class Carro {
    @Id //Indica que este é o campo de chave primaria da nossa tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Faz com que o atributo id, toda vez que salvarmos um carro a JPA vai fazer o auto incremento do ID.
    private Long id;
    @Column(name = "nome") //Indica qual o campo da tabela do banco é este atributo. (Não precisa usar porque os dois tem o mesmo nome)
    private String nome;
    private String tipo;

}

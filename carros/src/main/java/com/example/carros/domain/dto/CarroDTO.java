package com.example.carros.domain.dto;

import com.example.carros.domain.Carro;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
public class CarroDTO {
    private Long id;
    private String nome;
    private String tipo;

    public static CarroDTO create(Carro carro){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(carro, CarroDTO.class);
    }
    public static CarroDTO creates(Iterable<Carro> carros){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(carros, CarroDTO.class);
    }

}

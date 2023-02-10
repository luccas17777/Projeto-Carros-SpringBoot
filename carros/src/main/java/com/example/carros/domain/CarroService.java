package com.example.carros.domain;

import com.example.carros.domain.dto.CarroDTO;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // para indicar que a classe está mantendo a lógica de negócios.
public class CarroService{
    @Autowired //Pelo que entendi isso faz a injeção de dependencia. Excluindo então a necessidade do NEW.
    private CarroRepository rep;

    public List<CarroDTO> getCarros(Pageable pegeable){
       return rep.findAll(pegeable).stream().map(carro -> CarroDTO.create(carro)).collect(Collectors.toList());
    }

    public CarroDTO getCarroById(Long id){
        Optional<Carro> carro = rep.findById(id);
        return carro.map(CarroDTO::create).orElseThrow(()-> new ObjectNotFoundException(id,"Carro não encontrado"));
    }

    public List<CarroDTO> getCarroByTipo(String tipo, Pageable pageable){
        return rep.findByTipo(tipo, pageable).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO insert(Carro carro){
        Assert.isNull(carro.getId(), "Não foi possivel inserir o registro");

        return CarroDTO.create(rep.save(carro));
    }

    public CarroDTO update(Carro carro, Long id){
        Assert.notNull(id,"Não foi possível atualizar o registro!!");

        //Buscando carro no banco de dados
        Optional<Carro> optional = rep.findById(id);
        if (optional.isPresent()){
            Carro db = optional.get();
            //copiar as propriedades
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id: "+ db.getId());

            //Atualizar carro
            rep.save(db);

            return CarroDTO.create(db);
        }else {
            throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }
    public CarroDTO salvarCarros(Iterable<Carro> carros){
        return CarroDTO.creates(rep.saveAll(carros));
    }

    public void delete(Long id){
        //Optional<Carro> carro = getCarroById(id);
         rep.deleteById(id);

    }
    public List<Carro> getCarrosFake() {
        List<Carro> carros = new ArrayList<>();

    return carros;
    }

}

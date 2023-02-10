package com.example.carros.api;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/* *** RequestParam *** e *** PathVariable *** fazem a mesma coisa só que de uma forma diferente.
 Ambos recebem informações pela URL no entanto de formas diferentes.
 Muito parecido com o conceito de Bind que vimos em Angular.
            RequestParam(ex: api/v1/carros?page=0&size=1)
            PathVariable(ex: api/v1/carros/0/1)
       */

@RestController //transforma a classe em web service rest
@RequestMapping("/api/v1/carros") //Vai mapear esse web service. ex: esse web service esta mapeado pelo caminho: /api/v1/carros. Ou seja, toda vez que digitarmos esse caminho na url, encotraremos este metodo porque ele esta mapeado com essa rota.
public class CarrosController {
    @Autowired //Pelo que entendi isso faz a injeção de dependencia. Excluindo então a necessidade do NEW.
    private CarroService service;


    @GetMapping() //serve para mapear este metodo. Ele é um atalho para o RequestMapping
    public ResponseEntity get(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size){
        return ResponseEntity.ok(service.getCarros(PageRequest.of(page, size)));
        //return new ResponseEntity<>(service.getCarros(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getId(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.getCarroById(id));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity getTipo(@PathVariable("tipo") String tipo, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size){
        List<CarroDTO> carroList = service.getCarroByTipo(tipo, PageRequest.of(page,size));

        return carroList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carroList);

    }

    @PostMapping("salvar1")
    public ResponseEntity post(@RequestBody Carro carro){ //O RequestBody converte o JSON do Carro para o obejto carro.
        CarroDTO c = service.insert(carro);
        URI location = getUri(c.getId());
        return ResponseEntity.created(location).build();
    }

    @PostMapping
    public ResponseEntity salvarCarros(@RequestBody Iterable<Carro> carros){ //O RequestBody converte o JSON do Carro para o obejto carro.
        return ResponseEntity.ok(service.salvarCarros(carros));
    }


    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/id")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Carro carro){ //O RequestBody converte o JSON do Carro para o obejto carro.
        CarroDTO c = service.update(carro, id);
        return c != null ? ResponseEntity.ok(carro) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}

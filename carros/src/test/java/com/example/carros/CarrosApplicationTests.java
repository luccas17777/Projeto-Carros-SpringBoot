package com.example.carros;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.domain.dto.CarroDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarrosApplicationTests {
	@Autowired
	CarroService service;

	@Test
	public void test1() {
		/*Carro carro = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("esportivo");

		CarroDTO c = service.insert(carro);

		assertNotNull(c);

		Long id = c.getId();
		assertNotNull(id);

		//Buscar o objeto
		Optional<CarroDTO> op = service.getCarroById(id);
		assertTrue(op.isPresent());

		c = op.get();

		assertEquals("Ferrari", c.getNome());
		assertEquals("esportivos", c.getTipo());

		//delete
		service.delete(id);

		//verifica se deletou
		assertFalse(service.getCarroById(id).isPresent());*/
	}

}

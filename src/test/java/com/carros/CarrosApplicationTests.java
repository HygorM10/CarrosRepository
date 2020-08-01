package com.carros;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.carros.domian.Carro;
import com.carros.domian.CarrosServices;
import com.carros.domian.dto.CarroDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
class CarrosApplicationTests {

	@Autowired
	private CarrosServices service;
	
	@Test
	void contextLoads() {
		
		Carro carro = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("esportivos");
		
		CarroDTO c = service.insert(carro);
		
		assertNotNull(c);
		
		Long id = c.getId();
		assertNotNull(id);
		
		//Buscar objeto
		Optional<CarroDTO> op = service.getCarrosById(id);
		assertTrue(op.isPresent());
		
		c = op.get();
		assertEquals("Ferrari", c.getNome());
		assertEquals("esportivos", c.getTipo());
		
		service.delete(id);
		
		assertFalse(service.getCarrosById(id).isPresent());
	}
	
	@Test
	public void testLista() {
		
		List<CarroDTO> carros = service.getCarros();
		
		assertEquals(30, carros.size());
	}
	
	@Test
	public void testGet() {
		
		Optional<CarroDTO> op = service.getCarrosById(11L);
		
		assertTrue(op.isPresent());
		
		CarroDTO c = op.get();
		
		assertEquals("Ferrari FF", c.getNome());
	}


}

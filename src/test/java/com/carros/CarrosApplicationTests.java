package com.carros;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.carros.api.exception.ObjectNotFoundException;
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
		c = service.getCarrosById(id);
		assertNotNull(c);
		
		assertEquals("Ferrari", c.getNome());
		assertEquals("esportivos", c.getTipo());
		
		service.delete(id);
		
		try {
			assertNull(service.getCarrosById(id));
			fail("O carro não foi excluido.");
		}catch (ObjectNotFoundException e) {
			// OK
		}
		
	}
	
	@Test
	public void testLista() {
		
		List<CarroDTO> carros = service.getCarros();
		
		assertEquals(30, carros.size());
	}
	
	@Test
	public void testGet() {
		
		CarroDTO c = service.getCarrosById(11L);
		
		assertNotNull(c);
		
		assertEquals("Ferrari FF", c.getNome());
	}


}

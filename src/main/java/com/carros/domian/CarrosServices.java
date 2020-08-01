package com.carros.domian;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.carros.domian.dto.CarroDTO;

@Service
public class CarrosServices {

	@Autowired
	private ICarros iCarros;

	public List<CarroDTO> getCarros() {

		return iCarros.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
		
	}

	public CarroDTO getCarrosById(Long id) {
		
		return iCarros.findById(id).map(CarroDTO::create).orElseThrow(() -> new com.carros.api.exception.ObjectNotFoundException("Carro não encontrado!"));
	}

	public List<CarroDTO> getCarrosByTipo(String tipo) {
		return iCarros.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
	}

	public CarroDTO insert(Carro carro) {
		Assert.isNull(carro.getId(), "Não foi possivel salvar o registro!!!");
		
		return CarroDTO.create(iCarros.save(carro));
	}

	public CarroDTO update(Carro carro, Long id) {
		Assert.notNull(id, "Não foi possivel atualizar o registro!!!");

		Optional<Carro> optional = iCarros.findById(id);
		if (optional.isPresent()) {
			Carro db = optional.get();
			// Copiar as propriedades
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id " + db.getId());

			iCarros.save(db);

			return CarroDTO.create(db);
		}else {
			return null;
//			throw new RuntimeException("Não foi possivel atualizar o registro");
		}
		
//		getCarrosById(id).map(db -> {
//			db.setNome(carro.getNome());
//			db.setTipo(carro.getTipo());
//			System.out.println("Carro id " + db.getId());
//			
//			iCarros.save(db);
//			
//			return db;
//		}).orElseThrow(() -> new RuntimeException("Não foi possivel atualizar o registro"));
	}
	
	public void delete(Long id) {
		iCarros.deleteById(id);
	}

//	public List<Carro> getCarrosFake() {
//		List<Carro> carros = new ArrayList<>();
//
//		carros.add(new Carro(1L, "Fusca"));
//		carros.add(new Carro(2L, "Brasilia"));
//		carros.add(new Carro(3L, "Passat"));
//		carros.add(new Carro(4L, "Combi"));
//
//		return carros;
//	}

}

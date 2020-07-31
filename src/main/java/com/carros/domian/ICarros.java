package com.carros.domian;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carros.domian.dto.CarroDTO;

public interface ICarros extends JpaRepository<Carro, Long> {

	List<Carro> findByTipo(String tipo);

	void save(CarroDTO db);

}

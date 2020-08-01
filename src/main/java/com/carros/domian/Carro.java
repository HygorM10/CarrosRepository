package com.carros.domian;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Carro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String tipo;
	private String descricao;
	private String url_foto;
	private String url_video;
	private String latitude;
	private String longitude;
	
}

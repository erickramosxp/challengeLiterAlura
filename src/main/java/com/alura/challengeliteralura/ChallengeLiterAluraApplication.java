package com.alura.challengeliteralura;

import com.alura.challengeliteralura.model.Dados;
import com.alura.challengeliteralura.model.DadosAutor;
import com.alura.challengeliteralura.service.ConsumoAPI;
import com.alura.challengeliteralura.service.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;

import java.util.Optional;

@SpringBootApplication
public class ChallengeLiterAluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello");
		ConverteDados conversor = new ConverteDados();
		ConsumoAPI api = new ConsumoAPI();
		var resultado = api.obterDados("https://gutendex.com/books?search=dom+casmurro");
		Dados resul = conversor.obterDados(resultado, Dados.class);
		//		DadosAutor resul = conversor.obterDados(resultado, DadosAutor.class);
//			ObjectMapper mapper = new ObjectMapper();
//		DadosAutor resul;
//		try {
//			resul = mapper.readValue(resultado, DadosAutor.class);
//		} catch (JsonProcessingException e) {
//			throw new RuntimeException(e);
//		}
		System.out.println(resul);
	}
}

package com.alura.challengeliteralura;

import com.alura.challengeliteralura.model.Dados;
import com.alura.challengeliteralura.model.DadosAutor;
import com.alura.challengeliteralura.principal.Principal;
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
		Principal principal = new Principal();
		principal.exibirMenu();
	}
}

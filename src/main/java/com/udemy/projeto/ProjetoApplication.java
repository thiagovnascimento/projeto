package com.udemy.projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.udemy.projeto.model.entities.Cliente;
import com.udemy.projeto.model.repositories.ClienteRepository;

@SpringBootApplication
public class ProjetoApplication implements CommandLineRunner {
	@Autowired
	ClienteRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cliente cliente = new Cliente();
		cliente.setNome("Thiago Vieira");
		cliente.setCpf("01816564184");
		repository.save(cliente);
		
	}

}

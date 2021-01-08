package com.udemy.projeto.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.udemy.projeto.model.entities.Cliente;
import com.udemy.projeto.model.repositories.ClienteRepository;

@RestController // Ser reconhecida como controlador REST (Vai receber requisições e enviar respostas HTTP)
@RequestMapping("/api/clientes") // Mapear a url base
@CrossOrigin("http://localhost:4200") //Permite que controle o Cors para cada controller => comunicar web com api
public class ClienteController {
	
	private final ClienteRepository clienteRepository;
	
	@Autowired // Injeção de dependência
	public ClienteController(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	@PostMapping // Criar um recurso no servidor
	@ResponseStatus(HttpStatus.CREATED) // Definir o código de status que foi criado com sucesso
	public Cliente salvarCliente(@RequestBody @Valid Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@GetMapping
	public List<Cliente> encontrarTodos(){
		return clienteRepository.findAll();
	}
	
	@GetMapping("{id}") // Encontra um recurso no servidor
	public Cliente encontrarPeloId(@PathVariable Integer id) {
		return clienteRepository.findById(id)
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarCliente(@PathVariable Integer id) {
		clienteRepository.findById(id)
		.map(cliente -> {
			clienteRepository.delete(cliente);
			return Void.TYPE;
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );	
	}
	
	@PutMapping("{id}") // Utilizado para atualizar um recurso
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarCliente(@PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado) {
		clienteRepository.findById(id)
		.map(cliente -> {
			clienteAtualizado.setId(cliente.getId());
			return clienteRepository.save(clienteAtualizado);
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
	}

}

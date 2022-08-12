package com.example.crud.h2.jdbc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.h2.jdbc.dto.ResponseDTO;
import com.example.crud.h2.jdbc.entities.Person;
import com.example.crud.h2.jdbc.repositories.PersonRepository;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Bean
	private void init() {
		/* Insere dados iniciais, apenas para testes. Como estamos usando H2 Database,
		 * os dados são perdidos ao finalizar o sistema.
		 */
		personRepository.insert(new Person(0L, "Fulano de Tal", "123-789", null, null));
		personRepository.insert(new Person(0L, "Ciclano de Silva", "147-258", null, null));
		personRepository.insert(new Person(0L, "Beltrano de Souza", "963-852", null, null));
	}
	
	@GetMapping
	public ResponseEntity<ResponseDTO> getAll() {
		try {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(personRepository.selectAll(), null), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO> getById(@PathVariable Long id) {
		try {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(personRepository.selectById(id), null), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<ResponseDTO> post(@RequestBody Person person) {
		try {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(personRepository.insert(person), null), HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	@PutMapping
	public ResponseEntity<ResponseDTO> put(@RequestBody Person person) {
		try {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(personRepository.update(person), null), HttpStatus.OK); 
		} catch(Exception e) {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable Long id) {
		try {
			personRepository.delete(id);
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

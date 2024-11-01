package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.data.vo.v1.PersonVO;
import com.example.demo.models.Person;
import com.example.demo.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository repository;

	public List<PersonVO> findAll() {
		return repository.findAll();
	}

	public PersonVO findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for these id"));
	}

	public PersonVO create(PersonVO PersonVO) {
		return repository.save(person);
	}

	public PersonVO update(PersonVO person) {
		PersonVO entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for these id"));
		entity.setAdress(person.getAdress());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		return repository.save(person);
	}

	public void delete(Long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for these id"));
		repository.delete(entity);
	}
}

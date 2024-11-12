package com.example.demo.services;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.RequiredObjectIsNullException;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.controllers.BookController;
import com.example.demo.data.vo.v1.BookVO;
import com.example.demo.mapper.DozerMapper;
import com.example.demo.models.Book;
import com.example.demo.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository repository;

	public List<BookVO> findAll() {
		var books = DozerMapper.parseListObjects(repository.findAll(),BookVO.class );
		books.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		return books;
	}

	public BookVO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for these id"));
		BookVO vo =  DozerMapper.parseObject(entity,BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return vo;
	}

	public BookVO create(BookVO book) {
		if (book == null) throw new RequiredObjectIsNullException();
		var entity = DozerMapper.parseObject(book, Book.class);
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class); 
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	

	public BookVO update(BookVO book) {
		if (book == null) throw new RequiredObjectIsNullException();
		var entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for these id"));
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		
		var vo =  DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public void delete(Long id) {
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for these id"));
		repository.delete(entity);
	}
}

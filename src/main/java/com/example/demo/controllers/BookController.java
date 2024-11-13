package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.vo.v1.BookVO;
import com.example.demo.services.BookService;
import com.example.demo.util.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Book", description = "Endpoints for managing Book")
public class BookController {
	
	
	@Autowired
	private BookService service;

	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
	@Operation(summary = "Finds all people", description = "Finds all people", tags = { "Book" }, responses = {
			@ApiResponse(description = "Success", 
					responseCode = "200", 
					content = {
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = BookVO.class))) }),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content(mediaType = "application/json")}),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content(mediaType = "application/json")}),
			@ApiResponse(description = "Not Found", responseCode = "404", content = {@Content(mediaType = "application/json")}),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content(mediaType = "application/json")}),
	})
	public List<BookVO> findAll() {
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}",
			produces = {MediaType.APPLICATION_JSON ,MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
	@Operation(summary = "Finds a Book", description = "Finds a Book", tags = { "Book" }, responses = {
			@ApiResponse(description = "Success", 
					responseCode = "200", 
					content = @Content(schema = @Schema(implementation = BookVO.class)) ),
			@ApiResponse(description = "No Content", responseCode = "204", content = {@Content(mediaType = "application/json")}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content(mediaType = "application/json")}),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content(mediaType = "application/json")}),
			@ApiResponse(description = "Not Found", responseCode = "404", content = {@Content(mediaType = "application/json")}),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content(mediaType = "application/json")}),
	})
	public BookVO findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PostMapping(consumes =  {MediaType.APPLICATION_JSON ,MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML},
			produces =  {MediaType.APPLICATION_JSON ,MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
	@Operation(summary = "Create a Book", description = "Create a Book", tags = { "Book" }, responses = {
			@ApiResponse(description = "Success", 
					responseCode = "200", 
					content = @Content(schema = @Schema(implementation = BookVO.class)) ),
			@ApiResponse(description = "No Content", responseCode = "204", content = {@Content(mediaType = "application/json")}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content(mediaType = "application/json")}),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content(mediaType = "application/json")}),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content(mediaType = "application/json")}),
	})
	public BookVO create(@RequestBody BookVO book) {
		return service.create(book);
	}
	
	@PutMapping(consumes = {MediaType.APPLICATION_JSON ,MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML},
			produces = {MediaType.APPLICATION_JSON ,MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
	@Operation(summary = "Updates a Book", description = "Updates a Book", tags = { "Book" }, responses = {
			@ApiResponse(description = "Updated", 
					responseCode = "200", 
					content = @Content(schema = @Schema(implementation = BookVO.class)) ),
			@ApiResponse(description = "No Content", responseCode = "204", content = {@Content(mediaType = "application/json")}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content(mediaType = "application/json")}),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content(mediaType = "application/json")}),
			@ApiResponse(description = "Not Found", responseCode = "404", content = {@Content(mediaType = "application/json")}),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content(mediaType = "application/json")}),
	})
	public BookVO update(@RequestBody BookVO book) {
		return service.update(book);
	}
	
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deletes a Book", description = "Deletes a Book", tags = { "Book" }, responses = {
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "No Content", responseCode = "204", content = {@Content(mediaType = "application/json")}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content(mediaType = "application/json")}),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content(mediaType = "application/json")}),
			@ApiResponse(description = "Not Found", responseCode = "404", content = {@Content(mediaType = "application/json")}),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content(mediaType = "application/json")}),
	})
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
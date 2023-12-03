package com.Sistematizacao.controleDeFuncionarios.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import com.Sistematizacao.controleDeFuncionarios.repositories.ControleRepository;
import com.Sistematizacao.controleDeFuncionarios.dtos.ControleDto;
import com.Sistematizacao.controleDeFuncionarios.models.ControleModel;

@RestController
public class ControleController {

	@Autowired
	ControleRepository controleRepository;
	
	@PostMapping("/controleDeFuncionarios")
	public ResponseEntity<ControleModel> salvar(@RequestBody @Valid ControleDto controleDto) {
		var controleModel = new ControleModel();
		BeanUtils.copyProperties(controleDto, controleModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(controleRepository.save(controleModel));
	}
	
	@GetMapping("/controleDeFuncionarios")
	public ResponseEntity<List<ControleModel>> listar(){
		List<ControleModel> controleList = controleRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(controleList);
	}
	
	@GetMapping("/controleDeFuncionarios/{id}")
	public ResponseEntity<Object> detalhar(@PathVariable(value="id") Integer id){
		Optional<ControleModel> controle = controleRepository.findById(id);
		if(controle.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado exibição.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(controle.get());
	}
	
	@DeleteMapping("/controleDeFuncionarios/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") Integer id) {
		Optional<ControleModel> controle = controleRepository.findById(id);
		if(controle.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado para exclusão.");
		}
		controleRepository.delete(controle.get());
		return ResponseEntity.status(HttpStatus.OK).body("O Filme foi excluído.");
	}
	
	@PutMapping("/controleDeFuncionarios/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable(value="id") Integer id,
													  @RequestBody @Valid ControleDto controleDto) {
		Optional<ControleModel> controle = controleRepository.findById(id);
		if(controle.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado para edição.");
		}
		var controleModel = controle.get();
		BeanUtils.copyProperties(controleDto, controleModel);
		return ResponseEntity.status(HttpStatus.OK).body(controleRepository.save(controleModel));
	}
}


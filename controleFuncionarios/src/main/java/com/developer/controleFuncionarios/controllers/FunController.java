package com.developer.controleFuncionarios.controllers;

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
import com.developer.controleFuncionarios.repositories.FunRepository;
import com.developer.controleFuncionarios.dtos.FunDto;
import com.developer.controleFuncionarios.models.FunModel;

@RestController
public class FunController {

	@Autowired
	FunRepository funRepository;
	
	@PostMapping("/funcionario")
	public ResponseEntity<FunModel> salvar(@RequestBody @Valid FunDto funDto) {
		var funModel = new FunModel();
		BeanUtils.copyProperties(funDto, funModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(funRepository.save(funModel));
	}
	
	@GetMapping("/funcionario")
	public ResponseEntity<List<FunModel>> listar(){
		List<FunModel> funList = funRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(funList);
	}
	
	@GetMapping("/funcionario/{id}")
	public ResponseEntity<Object> detalhar(@PathVariable(value="id") Integer id){
		Optional<FunModel> funcionario = funRepository.findById(id);
		if(funcionario.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("funcionário não encontrado exibição.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(funcionario.get());
	}
	
	@DeleteMapping("/funcionario/{id}")
	public ResponseEntity<Object> deletarFuncionario(@PathVariable(value="id") Integer id) {
		Optional<FunModel> funcionario = funRepository.findById(id);
		if(funcionario.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("funcionário não encontrado para exclusão.");
		}
		funRepository.delete(funcionario.get());
		return ResponseEntity.status(HttpStatus.OK).body("O funcionário foi excluído.");
	}
	
	@PutMapping("/funcionario/{id}")
	public ResponseEntity<Object> atualizarFuncionario(@PathVariable(value="id") Integer id,
													  @RequestBody @Valid FunDto funDto) {
		Optional<FunModel> funcionario = funRepository.findById(id);
		if(funcionario.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado para edição.");
		}
		var funModel = funcionario.get();
		BeanUtils.copyProperties(funDto, funModel);
		return ResponseEntity.status(HttpStatus.OK).body(funRepository.save(funModel));
	}
}

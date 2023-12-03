package com.developer.controleFuncionarios.dtos;

import jakarta.validation.constraints.NotBlank;

public record FunDto(	@NotBlank String nome, 
						@NotBlank String cpf, 
						@NotBlank String carga ) {
}

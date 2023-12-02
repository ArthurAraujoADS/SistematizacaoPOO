package com.Sistematizacao.controleDeFuncionarios.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ControleDto(	@NotBlank String nome, 
						@NotNull Integer cpf, 
						@NotNull Long capacidadeDeCarga) {
}
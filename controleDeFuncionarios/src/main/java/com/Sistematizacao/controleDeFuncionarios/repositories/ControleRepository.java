package com.Sistematizacao.controleDeFuncionarios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Sistematizacao.controleDeFuncionarios.models.ControleModel;

@Repository
public interface ControleRepository extends JpaRepository<ControleModel, Integer> {

}

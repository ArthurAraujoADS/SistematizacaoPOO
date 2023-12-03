package com.developer.controleFuncionarios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.developer.controleFuncionarios.models.FunModel;



@Repository

public interface FunRepository extends JpaRepository<FunModel, Integer> {

}

package com.backend.backendtcc.repository;

import com.backend.backendtcc.model.SubCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoriaRepository extends JpaRepository<SubCategoria, Integer> {

    List<SubCategoria> findAllByCategoria_IdCategoria(int idCategoria);

}

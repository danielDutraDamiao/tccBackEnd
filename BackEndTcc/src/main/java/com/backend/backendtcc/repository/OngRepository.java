package com.backend.backendtcc.repository;

import com.backend.backendtcc.model.Ong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OngRepository extends JpaRepository<Ong, Integer> {
}

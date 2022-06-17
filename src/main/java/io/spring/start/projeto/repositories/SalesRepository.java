package io.spring.start.projeto.repositories;

import io.spring.start.projeto.model.SalesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SalesRepository extends JpaRepository<SalesModel, UUID> {
}

package io.spring.start.projeto.repositories;

import io.spring.start.projeto.model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, UUID> {

    boolean existsByNameAndEmail(String name, String email);
}

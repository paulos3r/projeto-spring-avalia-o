package io.spring.start.projeto.services;

import io.spring.start.projeto.model.SalesModel;
import io.spring.start.projeto.repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SalesService {

    @Autowired
    SalesRepository salesRepository;

    public Object save(SalesModel salesModel) {
        return salesRepository.save(salesModel);
    }

    public List<SalesModel> findAll() {
        return salesRepository.findAll();
    }

    public Optional<SalesModel> findById(UUID id) {
        return salesRepository.findById(id);
    }

    public Object update(SalesModel salesModel) {
        return salesRepository.save(salesModel);
    }
}

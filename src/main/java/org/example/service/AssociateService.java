package org.example.service;

import org.example.entity.Associate;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.AssociateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssociateService {
    @Autowired
    private AssociateRepository associateRepository;

    public Associate save(Associate associate){
        return associateRepository.save(associate);
    }

    public List<Associate> findAll(){
        return associateRepository.findAll();
    }

    public Object saveAll(List<Associate> associates) {
        return  associateRepository.saveAll(associates);
    }

    public Associate findById(Long id) {
        return associateRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Associado não encontrado com a id  " + id));
    }
}

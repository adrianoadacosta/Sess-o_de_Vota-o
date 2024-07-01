package org.example.service;

import org.example.entity.Agenda;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    public Agenda saveAgenda(Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    public Agenda findAgendaById(Long id) {
        return agendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agenda não encontrada com id " + id));
    }

    public List<Agenda> findAllAgendas() {
        return agendaRepository.findAll();
    }
}

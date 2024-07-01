package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.entity.Agenda;
import org.example.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendas")
@Api(value = "Agenda Management System")
public class AgendaController {
    @Autowired
    private AgendaService agendaService;

    @ApiOperation(value = "Create a new agenda")
    @PostMapping
    public ResponseEntity<Agenda> create(@RequestBody Agenda agenda) {
        Agenda createdAgenda = agendaService.saveAgenda(agenda);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAgenda);
    }
    @ApiOperation(value = "Get agenda by id")
    @GetMapping("/{id}")
    public ResponseEntity<Agenda> getAgenda(@PathVariable Long id) {
        Agenda agenda = agendaService.findAgendaById(id);
        return new ResponseEntity<>(agenda, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all agendas")
    @GetMapping
    public ResponseEntity<List<Agenda>> getAllAgendas() {
        List<Agenda> agendas = agendaService.findAllAgendas();
        return new ResponseEntity<>(agendas, HttpStatus.OK);
    }
}

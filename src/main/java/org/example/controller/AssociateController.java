package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.entity.Associate;
import org.example.service.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/associates")
@Api(value = "Associate Management System")
public class AssociateController {
    @Autowired
    private AssociateService associateService;

    @ApiOperation(value = "Create a new associate")
    @PostMapping
    public ResponseEntity<Associate> create(@RequestBody Associate associate) {
        return ResponseEntity.ok(associateService.save(associate));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Associate>> createBatch(@RequestBody List<Associate> associates) {
        return ResponseEntity.ok((List<Associate>) associateService.saveAll(associates));
    }

    @ApiOperation(value = "Get all associate")
    @GetMapping
    public ResponseEntity<List<Associate>> getAll() {
        return ResponseEntity.ok(associateService.findAll());
    }

    @ApiOperation(value = "Get associate by id")
    @GetMapping("/{id}")
    public ResponseEntity<Associate> getById(@PathVariable Long id) {
        return ResponseEntity.ok(associateService.findById(id));
    }
}

package com.yuri.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.yuri.os.domain.OS;
import com.yuri.os.dtos.OSDTO;
import com.yuri.os.services.OSService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/os")
public class OSResource {

    @Autowired
    private OSService osService;

    // Listar um OS específico registrado
    @GetMapping(value = "/{id}")
    public ResponseEntity<OSDTO> findById(@PathVariable Integer id) {
        OSDTO osDTO = new OSDTO(osService.findById(id));

        return ResponseEntity.ok().body(osDTO);
    }

    // Listar todos os OSs registrados
    @GetMapping
    public ResponseEntity<List<OSDTO>> findAll() {
        List<OSDTO> listDTO = osService
                                    .findAll()
                                    .stream()
                                    .map(obj -> new OSDTO(obj))
                                    .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    // Criar novo OS
    @PostMapping
    public ResponseEntity<OSDTO> create(@Valid @RequestBody OSDTO osDTO) {
        OS os = osService.create(osDTO);
        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(os.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    // Atualiza OS existente
    @PutMapping
    public ResponseEntity<OSDTO> update(@Valid @RequestBody OSDTO osDTO) {

        osDTO = new OSDTO(osService.update(osDTO));
        return ResponseEntity.ok().body(osDTO);
    }
}

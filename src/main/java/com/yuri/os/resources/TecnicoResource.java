package com.yuri.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.yuri.os.domain.Tecnico;
import com.yuri.os.dtos.TecnicoDTO;
import com.yuri.os.services.TecnicoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;

    // Listar um técnico específico registrado
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        TecnicoDTO objDTO = new TecnicoDTO(tecnicoService.findById(id));
        return ResponseEntity.ok().body(objDTO);
    }

    // Listar todos os técnicos registrados
    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        List<TecnicoDTO> listDTO = tecnicoService
                                    .findAll()
                                    .stream()
                                    .map(obj -> new TecnicoDTO(obj))
                                    .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    // Criar novo técnico
    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO tecnicoDTO) {
        Tecnico tecnico = tecnicoService.create(tecnicoDTO);
        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    // Atualizar técnico existente
    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO tecnicoDTO) {

        TecnicoDTO newTecnicoDTO = new TecnicoDTO(tecnicoService.update(id, tecnicoDTO));
        return ResponseEntity.ok().body(newTecnicoDTO);
    }

    // Deletar técnico existente
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}

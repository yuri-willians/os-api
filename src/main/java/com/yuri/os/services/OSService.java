package com.yuri.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.yuri.os.domain.OS;
import com.yuri.os.domain.enums.Prioridade;
import com.yuri.os.domain.enums.Status;
import com.yuri.os.dtos.OSDTO;
import com.yuri.os.repositories.OSRepository;
import com.yuri.os.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OSService {

    @Autowired
    private OSRepository osRepository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public OS findById(Integer id) {
        Optional<OS> os = osRepository.findById(id);
        return os
        .orElseThrow(
            () -> new ObjectNotFoundException(
                "Ordem de serviço não entrada: " + id + ", Tipo: " + os
                .getClass()
                .getName()
            )
        );
    }

    public List<OS> findAll() {
        return osRepository.findAll();
    }

    public OS create(OSDTO osDTO) {
        return fromDTO(osDTO);
    }

    public OS update(@Valid OSDTO osDTO) {
        findById(osDTO.getId());
        return fromDTO(osDTO);
    }

    private OS fromDTO(OSDTO osDTO) {
        OS newOs = new OS();
        newOs.setId(osDTO.getId());
        newOs.setObservacoes(osDTO.getObservacoes());
        newOs.setPrioridade(Prioridade.toEnum(osDTO.getPrioridade()));
        newOs.setStatus(Status.toEnum(osDTO.getStatus()));

        newOs.setTecnico(tecnicoService.findById(osDTO.getIdTecnico()));
        newOs.setCliente(clienteService.findById(osDTO.getIdCliente()));
        
        if (newOs.getStatus().getCod().equals(2)) {
            newOs.setDataFechamento(LocalDateTime.now());
        }

        return osRepository.save(newOs);
    }

}

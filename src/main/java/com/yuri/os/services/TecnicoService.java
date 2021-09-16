package com.yuri.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.yuri.os.domain.Pessoa;
import com.yuri.os.domain.Tecnico;
import com.yuri.os.dtos.TecnicoDTO;
import com.yuri.os.repositories.PessoaRepository;
import com.yuri.os.repositories.TecnicoRepository;
import com.yuri.os.services.exceptions.DataIntegratyViolationException;
import com.yuri.os.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj =  tecnicoRepository.findById(id);
        return obj.orElseThrow(
            () -> new ObjectNotFoundException(
                "Objeto não encontrado: id: " + id + ", tipo: " + Tecnico.class.getName()));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO tecnicoDTO) {
        if (findByCpf(tecnicoDTO) != null) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados: " + tecnicoDTO.getCpf());
        }
        return tecnicoRepository.save(
                                    new Tecnico(null, 
                                        tecnicoDTO.getNome(), 
                                        tecnicoDTO.getCpf(), 
                                        tecnicoDTO.getTelefone()
                                    ));
    }

    public Tecnico update(Integer id, @Valid TecnicoDTO tecnicoDTO) {

        Tecnico oldTecnico = findById(id);

        if (findByCpf(tecnicoDTO) != null && findByCpf(tecnicoDTO).getId() != id) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados: " + tecnicoDTO.getCpf());
        }

        oldTecnico.setNome(tecnicoDTO.getNome());
        oldTecnico.setCpf(tecnicoDTO.getCpf());
        oldTecnico.setTelefone(tecnicoDTO.getTelefone());

        return tecnicoRepository.save(oldTecnico);
    }
    
    public void delete(Integer id) {
        Tecnico tecnico = findById(id);
        if (tecnico.getList().size() > 0) {
            throw new DataIntegratyViolationException("Técnico não pode ser excluido pois possui ordens de serviço atribuidas.");
        }
        tecnicoRepository.deleteById(id);     
    }

    private Pessoa findByCpf(TecnicoDTO tecnicoDTO) {
        Pessoa pessoa = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
        if (pessoa != null) {
            return pessoa;
        }
        return null;
    }


}

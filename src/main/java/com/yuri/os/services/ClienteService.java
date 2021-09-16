package com.yuri.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.yuri.os.domain.Pessoa;
import com.yuri.os.domain.Cliente;
import com.yuri.os.dtos.ClienteDTO;
import com.yuri.os.repositories.PessoaRepository;
import com.yuri.os.repositories.ClienteRepository;
import com.yuri.os.services.exceptions.DataIntegratyViolationException;
import com.yuri.os.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj =  clienteRepository.findById(id);
        return obj.orElseThrow(
            () -> new ObjectNotFoundException(
                "Objeto não encontrado: id: " + id + ", tipo: " + Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDTO clienteDTO) {
        if (findByCpf(clienteDTO) != null) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados: " + clienteDTO.getCpf());
        }
        return clienteRepository.save(
                                    new Cliente(null, 
                                        clienteDTO.getNome(), 
                                        clienteDTO.getCpf(), 
                                        clienteDTO.getTelefone()
                                    ));
    }

    public Cliente update(Integer id, @Valid ClienteDTO clienteDTO) {

        Cliente oldCliente = findById(id);

        if (findByCpf(clienteDTO) != null && findByCpf(clienteDTO).getId() != id) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados: " + clienteDTO.getCpf());
        }

        oldCliente.setNome(clienteDTO.getNome());
        oldCliente.setCpf(clienteDTO.getCpf());
        oldCliente.setTelefone(clienteDTO.getTelefone());

        return clienteRepository.save(oldCliente);
    }
    
    public void delete(Integer id) {
        Cliente tecnico = findById(id);
        if (tecnico.getList().size() > 0) {
            throw new DataIntegratyViolationException("Cliente não pode ser excluido pois possui ordens de serviço atribuidas.");
        }
        clienteRepository.deleteById(id);     
    }

    private Pessoa findByCpf(ClienteDTO clienteDTO) {
        Pessoa pessoa = pessoaRepository.findByCpf(clienteDTO.getCpf());
        if (pessoa != null) {
            return pessoa;
        }
        return null;
    }


}

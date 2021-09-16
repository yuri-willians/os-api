package com.yuri.os.services;

import org.springframework.stereotype.Service;
import java.util.Arrays;

import com.yuri.os.domain.Cliente;
import com.yuri.os.domain.OS;
import com.yuri.os.domain.Tecnico;
import com.yuri.os.domain.enums.Prioridade;
import com.yuri.os.domain.enums.Status;
import com.yuri.os.repositories.ClienteRepository;
import com.yuri.os.repositories.OSRepository;
import com.yuri.os.repositories.TecnicoRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private OSRepository osRepository;

    public void instanciaDB() {

      Tecnico t1 = new Tecnico(null, "Yuri Willians dos Santos", "366.976.119-52", "(11) 98888-8888");
      Cliente c1 = new Cliente(null, "Betina Campos", "677.737.084-60", "(11) 98888-8887");
      OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, c1);

      t1.getList().add(os1);
      c1.getList().add(os1);

      tecnicoRepository.saveAll(Arrays.asList(t1));
      clienteRepository.saveAll(Arrays.asList(c1));
      osRepository.saveAll(Arrays.asList(os1));
    }
}

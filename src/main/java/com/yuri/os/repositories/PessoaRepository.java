package com.yuri.os.repositories;

import com.yuri.os.domain.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    @Query("SELECT pessoa FROM Pessoa pessoa WHERE pessoa.cpf =:cpf")
    Pessoa findByCpf(@Param("cpf") String cpf);
    
}

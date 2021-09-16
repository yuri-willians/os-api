package com.yuri.os.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yuri.os.domain.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}

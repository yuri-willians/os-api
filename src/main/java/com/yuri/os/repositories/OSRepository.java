package com.yuri.os.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yuri.os.domain.OS;


@Repository
public interface OSRepository extends JpaRepository<OS, Integer> {
}

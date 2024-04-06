package com.miguelneto.demo2.repositorio;

import com.miguelneto.demo2.entidade.Camisa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CamisaRepository extends JpaRepository<Camisa, Long> {
}

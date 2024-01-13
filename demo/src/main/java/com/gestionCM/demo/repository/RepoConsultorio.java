package com.gestionCM.demo.repository;

import com.gestionCM.demo.models.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoConsultorio extends JpaRepository<Consultorio, Long> {
    Consultorio findByNumeroConsultorio(int numeroConsultorio);
}

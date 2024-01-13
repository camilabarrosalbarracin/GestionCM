package com.gestionCM.demo.repository;
import com.gestionCM.demo.models.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepoProfesional extends JpaRepository<Profesional, Long> {
    boolean existsByMatricula(String matricula);

    Optional<Profesional> findByMatricula(String matricula);
}

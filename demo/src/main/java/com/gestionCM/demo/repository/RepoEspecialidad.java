package com.gestionCM.demo.repository;

import com.gestionCM.demo.models.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoEspecialidad extends JpaRepository<Especialidad, Long> {

    Especialidad findByNombreEspecialidad(String nombreEspecialidad);
}

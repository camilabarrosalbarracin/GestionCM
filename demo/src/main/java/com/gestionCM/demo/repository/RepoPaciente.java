package com.gestionCM.demo.repository;
import com.gestionCM.demo.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoPaciente extends JpaRepository<Paciente, Long> {
    Paciente findByDniPaciente(String dniPaciente);
}

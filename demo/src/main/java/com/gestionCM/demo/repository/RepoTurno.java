package com.gestionCM.demo.repository;
import com.gestionCM.demo.models.Paciente;
import com.gestionCM.demo.models.Profesional;
import com.gestionCM.demo.models.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface RepoTurno extends JpaRepository<Turno, Long> {
    boolean existsByProfesionalAndFechayHoraTurno(Profesional profesional, LocalDateTime fechayHoraTurno);

    boolean existsByPacienteAndFechayHoraTurno(Paciente paciente, LocalDateTime fechayHoraTurno);

    List<Turno> findByPacienteId(Long numeroPaciente);

    List<Turno> findByProfesionalId(Long idTemporal);

    List<Turno> findByEspecialidadId(Long idSeleccionado);
    List<Turno> findByFechayHoraTurno(LocalDateTime fechaYHoraTurno);
}

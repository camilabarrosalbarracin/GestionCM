package com.gestionCM.demo.service;

import com.gestionCM.demo.models.Consultorio;
import com.gestionCM.demo.models.Turno;
import com.gestionCM.demo.models.Profesional;
import com.gestionCM.demo.repository.RepoConsultorio;
import com.gestionCM.demo.repository.RepoTurno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class TurnoService {

    private final RepoTurno repoTurno;
    private final ProfesionalService profesionalService;
    private final RepoConsultorio repoConsultorio;

    @Autowired
    public TurnoService(RepoTurno repoTurno, ProfesionalService profesionalService,RepoConsultorio repoConsultorio) {
        this.profesionalService = profesionalService;
        this.repoTurno = repoTurno;
        this.repoConsultorio = repoConsultorio;
    }

    public List<Turno> getTurnos() {
        return repoTurno.findAll();
    }

    public void agregarTurno(Turno nuevoTurno) {
        if (validarTurno(nuevoTurno)) {
            repoTurno.save(nuevoTurno);
            System.out.println("El turno se creó correctamente");
        } else {
            System.out.println("No se pudo agregar el turno");
        }
    }

    public void borrarTurno(Long idTurno) {
        Optional<Turno> optionalTurno = repoTurno.findById(idTurno);

        if (optionalTurno.isPresent() && puedeModificarOCancelarTurno(repoTurno.getReferenceById(idTurno))) {
            Turno turno = optionalTurno.get();
            repoTurno.delete(turno);
            System.out.println("El turno se cancelo correctamente");
        } else {
            System.out.println("No se puede borrar el turno");
        }
    }

    public void actualizarTurno(Long id, Turno nuevoTurno) {
        Optional<Turno> optionalTurno = repoTurno.findById(id);

        if (optionalTurno.isPresent()) {
            Turno turnoExistente = optionalTurno.get();

            if (validarTurno(nuevoTurno) && puedeModificarOCancelarTurno(turnoExistente)) {
                actualizarCamposTurnoExistente(turnoExistente, nuevoTurno);
                repoTurno.save(turnoExistente);
            } else {
                System.out.println("No se puede modificar o cancelar el turno ya que excede la hora límite, está fuera del horario de atención o ya fue asignado.");
            }
        } else {
            System.out.println("No se encontró el turno");
        }
    }

    private boolean existeTurnoExactoEnBaseDeDatos(Turno nuevoTurno) {
        return repoTurno.existsByProfesionalAndFechayHoraTurno(nuevoTurno.getProfesional(), nuevoTurno.getFechayHoraTurno());
    }

    private boolean pacienteTieneTurnoEnHoraYDia(Turno nuevoTurno) {
        return repoTurno.existsByPacienteAndFechayHoraTurno(nuevoTurno.getPaciente(), nuevoTurno.getFechayHoraTurno());
    }
    private boolean profesionalTieneTurnoEnHoraYDia(Turno nuevoTurno) {
        return repoTurno.existsByProfesionalAndFechayHoraTurno(nuevoTurno.getProfesional(), nuevoTurno.getFechayHoraTurno());
    }

    public boolean puedeModificarOCancelarTurno(Turno turno) {
        LocalDateTime horaLimite = turno.getFechayHoraTurno().minusHours(1); // Una hora antes del turno
        return LocalDateTime.now().isBefore(horaLimite);
    }

    private void actualizarCamposTurnoExistente(Turno turnoExistente, Turno nuevoTurno) {
        turnoExistente.setFechayHoraTurno(nuevoTurno.getFechayHoraTurno());
        turnoExistente.setProfesional(nuevoTurno.getProfesional());
        turnoExistente.setEspecialidad(nuevoTurno.getEspecialidad());

        asignarConsultorioAleatorio(turnoExistente);
    }

    private void asignarConsultorioAleatorio(Turno turno) {
        List<Consultorio> consultorios = repoConsultorio.findAll();
        var i = 0;

        if (!consultorios.isEmpty()) {
            while (consultorioOcupadoEnFechaHora(consultorios.get(i),turno.getFechayHoraTurno())){
                i++;
            }
            Consultorio consultorioAsignado = consultorios.get(i);
            turno.setConsultorio(consultorioAsignado);
        } else {
            System.out.println("No hay consultorios disponibles");
        }
    }

    private boolean consultorioOcupadoEnFechaHora(Consultorio consultorio, LocalDateTime fechaHoraTurno) {
        List<Turno> turnosEnFechaYHora = repoTurno.findByFechayHoraTurno(fechaHoraTurno);
        return turnosEnFechaYHora.stream().anyMatch(turno -> Objects.equals(turno.getConsultorio().getId(), consultorio.getId()));
    }

    private boolean validarProfesionalExistente(Turno nuevoTurno) {
        String matriculaProfesional = nuevoTurno.getProfesional().getMatricula();
        Profesional profesional = profesionalService.obtenerProfesionalPorMatricula(matriculaProfesional);
        if (profesional != null && profesional.getId() != null) {
            Profesional profesionalEnBD = profesionalService.obtenerProfesionalPorId(profesional.getId());
            return profesionalEnBD != null;
        }
        return true;
    }

    //Verfifica
    // que el profesional no tenga un turno asignado a esa hora y dia
    // que el paciente no tenga un turno asignado a esa hora y dia
    private boolean validarTurno(Turno turno) {
        return validarProfesionalExistente(turno) &&
                !existeTurnoExactoEnBaseDeDatos(turno) &&
                !pacienteTieneTurnoEnHoraYDia(turno) &&
                !profesionalTieneTurnoEnHoraYDia(turno) &&
                LocalDateTime.now().isBefore(turno.getFechayHoraTurno());
    }

}

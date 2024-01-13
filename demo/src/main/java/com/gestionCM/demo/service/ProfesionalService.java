package com.gestionCM.demo.service;


import com.gestionCM.demo.models.Profesional;
import com.gestionCM.demo.repository.RepoProfesional;
import com.gestionCM.demo.repository.RepoTurno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class ProfesionalService {

    private final RepoProfesional repoProfesional;
    private final RepoTurno repoTurno;
    private final EspecialidadService especialidadService;

    @Autowired
    public ProfesionalService(RepoProfesional repoProfesional, EspecialidadService especialidadService,RepoTurno repoTurno) {
        this.repoProfesional = repoProfesional;
        this.especialidadService = especialidadService;
        this.repoTurno = repoTurno;
    }

    public List<Profesional> getProfesionales() {
        return repoProfesional.findAll();
    }

    public void agregarProfesional(Profesional nuevoProfesional) {
        if (!existeMatricula(nuevoProfesional.getMatricula())) {
            Set<com.gestionCM.demo.models.Especialidad> especialidades = nuevoProfesional.getEspecialidades();
            if (especialidades != null && !especialidades.isEmpty()) {
                Set<com.gestionCM.demo.models.Especialidad> especialidadesPersistidas = especialidadService.guardarEspecialidades(especialidades);
                nuevoProfesional.setEspecialidades(especialidadesPersistidas);
            }

            repoProfesional.save(nuevoProfesional);
            System.out.println("El profesional fue agregado correctamente");
        } else {
            System.out.println("La matrícula ya existe. No se puede agregar el profesional");
        }
    }

    public void borrarProfesional(Long idProfesional) {
        Optional<Profesional> optionalProfesional = repoProfesional.findById(idProfesional);

        if (optionalProfesional.isPresent()) {
            repoProfesional.delete(optionalProfesional.get());
            System.out.println("El profesional fue borrado correctamente");
        } else {
            System.out.println("No se encontró el profesional que desea borrar");
        }
    }

    public void actualizarProfesional(Long id, Profesional nuevoProfesional) {
        Optional<Profesional> optionalProfesional = repoProfesional.findById(id);

        if (optionalProfesional.isPresent()) {
            Profesional profesionalExistente = optionalProfesional.get();
            actualizarCampos(profesionalExistente, nuevoProfesional);
            repoProfesional.save(profesionalExistente);
            System.out.println("Los datos del profesional fueron actualizados correctamente");
        } else {
            System.out.println("No se encontró el profesional que desea actualizar");
        }
    }

    private void actualizarCampos(Profesional profesionalExistente, Profesional nuevoProfesional) {
        profesionalExistente.setNombreProfesional(nuevoProfesional.getNombreProfesional());
        profesionalExistente.setApellidoProfesional(nuevoProfesional.getApellidoProfesional());
        profesionalExistente.setMatricula(nuevoProfesional.getMatricula());
        profesionalExistente.setEspecialidades(nuevoProfesional.getEspecialidades());
        profesionalExistente.setHorarioEntrada(nuevoProfesional.getHorarioEntrada());
        profesionalExistente.setHorarioSalida(nuevoProfesional.getHorarioSalida());
    }

    private boolean existeMatricula(String matricula) {
        return repoProfesional.existsByMatricula(matricula);
    }

    public Profesional obtenerProfesionalPorId(Long id) {
        Optional<Profesional> optionalProfesional = repoProfesional.findById(id);
        if (optionalProfesional.isPresent()) {
            return optionalProfesional.get();
        } else {
            throw new RuntimeException("No se encontró el Profesional");
        }
    }

    public Profesional obtenerProfesionalPorMatricula(String matricula) {
        Optional<Profesional> optionalProfesional = repoProfesional.findByMatricula(matricula);

        if (optionalProfesional.isPresent()) {
            return optionalProfesional.get();
        } else {
            throw new RuntimeException("No se encontró el Profesional con matrícula: " + matricula);
        }
    }

    public List<LocalTime> obtenerHorariosDelProfesional(Profesional profesional) {
        List<LocalTime> horarios = new ArrayList<>();
        LocalTime horaInicio = LocalTime.of(profesional.getHorarioEntrada(), 0);
        LocalTime horaFin = LocalTime.of(profesional.getHorarioSalida(), 0);
        horaFin = horaFin.minusMinutes(60);

        while (horaInicio.isBefore(horaFin)) {
            horarios.add(horaInicio);
            horaInicio = horaInicio.plusMinutes(60);
        }

        return horarios;
    }

    public List<LocalDateTime> obtenerHorariosDelProfesionalEnELDia(Profesional profesional, LocalDate dia){
        List<LocalDateTime> horariosConDia = new ArrayList<>();
        List<LocalTime> horariosDelDia = obtenerHorariosDelProfesional(profesional);
        horariosDelDia.forEach(horario -> horariosConDia.add(dia.atTime(horario)));
        return horariosConDia;
    }

    public boolean profesionalTieneOcupadoElHorario(Profesional profesional, LocalDateTime fechaYHora){
        return repoTurno.existsByProfesionalAndFechayHoraTurno(profesional, fechaYHora);
    }

}

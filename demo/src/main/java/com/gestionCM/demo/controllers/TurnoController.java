package com.gestionCM.demo.controllers;
import com.gestionCM.demo.models.Turno;
import com.gestionCM.demo.models.TurnoRequest;
import com.gestionCM.demo.repository.RepoEspecialidad;
import com.gestionCM.demo.repository.RepoPaciente;
import com.gestionCM.demo.repository.RepoProfesional;
import com.gestionCM.demo.repository.RepoConsultorio;
import com.gestionCM.demo.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private final TurnoService turnoService;
    private final RepoPaciente repoPaciente;
    private final RepoProfesional repoProfesional;
    private final RepoConsultorio repoConsultorio;
    private final RepoEspecialidad repoEspecialidad;


    @Autowired
    public TurnoController(TurnoService turnoService, RepoEspecialidad repoEspecialidad, RepoProfesional repoProfesional,RepoPaciente repoPaciente,
                           RepoConsultorio repoConsultorio) {
        this.repoEspecialidad = repoEspecialidad;
        this.repoProfesional = repoProfesional;
        this.repoPaciente = repoPaciente;
        this.repoConsultorio = repoConsultorio;
        this.turnoService = turnoService;
    }

    @GetMapping
    public List<Turno> getTurnos() {
        return turnoService.getTurnos();
    }

    @PostMapping
    public void postTurno(@RequestBody TurnoRequest turnoRequest) {
        Turno nuevoTurno = new Turno();
        nuevoTurno.setPaciente(repoPaciente.getReferenceById(turnoRequest.getPacienteId()));
        nuevoTurno.setProfesional(repoProfesional.getReferenceById(turnoRequest.getProfesionalId()));
        nuevoTurno.setConsultorio(repoConsultorio.getReferenceById(turnoRequest.getConsultorioId()));
        nuevoTurno.setEspecialidad(repoEspecialidad.getReferenceById(turnoRequest.getEspecialidadId()));
        nuevoTurno.setFechayHoraTurno(turnoRequest.getFechayHoraTurno());
        turnoService.agregarTurno(nuevoTurno);
    }

    @DeleteMapping("/{id}")
    public void deleteTurno(@PathVariable Long id) {turnoService.borrarTurno(id);
    }

    @PutMapping("/{id}")
    public void putTurno(@PathVariable Long id, @RequestBody Turno nuevoTurno) {
        turnoService.actualizarTurno(id, nuevoTurno);
    }


}

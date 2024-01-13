package com.gestionCM.demo.controllers;
import com.gestionCM.demo.models.Especialidad;
import com.gestionCM.demo.models.Profesional;
import com.gestionCM.demo.models.Turno;
import com.gestionCM.demo.repository.RepoEspecialidad;
import com.gestionCM.demo.repository.RepoPaciente;
import com.gestionCM.demo.repository.RepoProfesional;
import com.gestionCM.demo.repository.RepoTurno;
import com.gestionCM.demo.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Controller
public class PaginaController {
    private final RepoEspecialidad repoEspecialidad;
    private final RepoProfesional repoProfesional;
    private final RepoPaciente repoPaciente;
    private final RepoTurno repoTurno;
    private final TurnoService turnoService;

    @Autowired
    public PaginaController(RepoEspecialidad repoEspecialidad, RepoProfesional repoProfesional, RepoPaciente repoPaciente, RepoTurno repoTurno, TurnoService turnoService) {
        this.repoEspecialidad = repoEspecialidad;
        this.repoProfesional = repoProfesional;
        this.repoPaciente = repoPaciente;
        this.repoTurno = repoTurno;
        this.turnoService = turnoService;
    }

    @GetMapping("/inicio")
    public String mostrarPlantillaBase() {
        return "PlantillaBase";
    }

    @GetMapping("/mostrarEspecialidades")
    public String mostrarEspecialidades(Model model) {
        List<Especialidad> especialidades = repoEspecialidad.findAll();
        model.addAttribute("ListaEspecialidades", especialidades);
        return "Especialidades";
    }


    @GetMapping("/mostrarProfesionales")
    public String mostrarProfesionales(Model model) {
        List<Profesional> profesionales = repoProfesional.findAll();
        model.addAttribute("ListaProfesionales", profesionales);
        return "Profesionales";
    }

    @GetMapping("/ConsultarTurnosPorPaciente")
    public String consultarTurnosPorPacienteForm(Model model) {
        model.addAttribute("consultarTurnosPorPacienteSelected", true);
        return "ConsultarTurnosPorPaciente1";
    }

    @PostMapping("/ConsultarTurnosPorPaciente")
    public String procesarFormularioPaciente(@RequestParam String texto, Model model) {
        Long idTemporal =Long.parseLong(texto);
        List<Turno> turnos = repoTurno.findByPacienteId(idTemporal);
        model.addAttribute("turnos", turnos);
        return "ConsultarTurnosPorPaciente2";
    }

    @GetMapping("/MostrarTurnos")
    public String mostrarTurnos(Model model) {
        model.addAttribute("TurnosSelected", true);
        return "MostrarTurnos";
    }

    @GetMapping("/MostrarTurnosProfesional")
    public String mostrarTurnosProfesional(Model model) {
        List<Profesional> profesionales = repoProfesional.findAll();
        model.addAttribute("ListaProfesionales", profesionales);
        return "MostrarTurnosProfesional1";
    }

    @PostMapping("/MostrarTurnosProfesional")
    public String procesarFormularioProfesional(@RequestParam Long idSeleccionado, Model model) {
        List<Turno> turnos = repoTurno.findByProfesionalId(idSeleccionado);
        model.addAttribute("turnos", turnos);
        List<Profesional> profesionales = repoProfesional.findAll();
        model.addAttribute("ListaProfesionales", profesionales);
        return "MostrarTurnosProfesional2";
    }


    @GetMapping("/MostrarTurnosEspecialidad")
    public String mostrarTurnosEspecialidad(Model model) {
        List<Especialidad> especialdad = repoEspecialidad.findAll();
        model.addAttribute("ListaEspecialidades", especialdad);
        return "MostrarTurnosEspecialidad1";
    }

    @PostMapping("/MostrarTurnosEspecialidad")
    public String procesarFormularioEspecialidad(@RequestParam Long idSeleccionado, Model model) {
        List<Turno> turnos = repoTurno.findByEspecialidadId(idSeleccionado);
        model.addAttribute("turnos", turnos);
        List<Especialidad> especialdad = repoEspecialidad.findAll();
        model.addAttribute("ListaEspecialidades", especialdad);
        return "MostrarTurnosEspecialidad2";
    }


    @GetMapping("/MostrarTurnosTodos")
    public String mostrarTurnosTodos(Model model) {
        List<Turno> turnos = repoTurno.findAll();
        model.addAttribute("ListaDeTodosLosTurnos", turnos);
        return "MostrarTurnosTodos";
    }

    @PostMapping("/MostrarTurnosTodos")
    public String eliminarTurno(@RequestParam Long idSeleccionado) {
        turnoService.borrarTurno(idSeleccionado);
        return "redirect:/MostrarTurnosTodos";
    }

    @GetMapping("/MostrarTurnosTodosModificar")
    public String mostrarTurnosTodosModificar(@RequestParam Long idSeleccionado, Model model) {

        Turno turno = repoTurno.getReferenceById(idSeleccionado);
        model.addAttribute("TurnoSeleccionado", turno);

        List<Especialidad> listaEspecialidades = repoEspecialidad.findAll();
        model.addAttribute("ListaEspecialidades", listaEspecialidades);

        List<Profesional> listaProfesionales = repoProfesional.findAll();
        model.addAttribute("ListaProfesionales", listaProfesionales);

        return "MostrarTurnosTodosModificar";
    }

    @PostMapping("/MostrarTurnosTodosModificar")
    public String procesarFormularioEdicion(@ModelAttribute("TurnoSeleccionado") Turno turnoSeleccionado, Model model) {

        Turno nuevoTurno = new Turno();
        nuevoTurno.setPaciente(repoPaciente.getReferenceById(turnoSeleccionado.getPaciente().getId()));
        nuevoTurno.setProfesional(repoProfesional.getReferenceById(turnoSeleccionado.getProfesional().getId()));
        nuevoTurno.setEspecialidad(repoEspecialidad.getReferenceById(turnoSeleccionado.getEspecialidad().getId()));
        nuevoTurno.setFechayHoraTurno(turnoSeleccionado.getFechayHoraTurno());

        turnoService.actualizarTurno(turnoSeleccionado.getId(), nuevoTurno);

        return "redirect:/MostrarTurnosTodos";
    }




}

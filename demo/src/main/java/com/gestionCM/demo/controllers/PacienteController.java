package com.gestionCM.demo.controllers;
import com.gestionCM.demo.models.Paciente;
import com.gestionCM.demo.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public List<Paciente> getPacientes() {
        return pacienteService.getPacientes();
    }

    @PostMapping
    public void postPaciente(@RequestBody Paciente nuevoPaciente) {
        pacienteService.agregarPaciente(nuevoPaciente);
    }

    @DeleteMapping("/{id}")
    public void deletePaciente(@PathVariable Long id) {
        pacienteService.borrarPaciente(id);
    }

    @PutMapping("/{id}")
    public void putPaciente(@PathVariable Long id, @RequestBody Paciente nuevoPaciente) {
        pacienteService.actualizarPaciente(id, nuevoPaciente);
    }
}


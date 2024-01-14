package com.gestionCM.demo.controllers;
import com.gestionCM.demo.models.Especialidad;
import com.gestionCM.demo.models.HorarioDisponibleDTO;
import com.gestionCM.demo.models.Profesional;
import com.gestionCM.demo.repository.RepoProfesional;
import com.gestionCM.demo.service.ProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/profesionales")
public class ProfesionalController {
    private final ProfesionalService profesionalService;
    private final RepoProfesional repoProfesional;

    @Autowired
    public ProfesionalController(ProfesionalService profesionalService,RepoProfesional repoProfesional) {
        this.profesionalService = profesionalService;
        this.repoProfesional = repoProfesional;
    }

    @GetMapping
    public List<Profesional> getProfesionales() {
        return profesionalService.getProfesionales();
    }

    @PostMapping
    public void postProfesional(@RequestBody Profesional nuevoProfesional) {
        profesionalService.agregarProfesional(nuevoProfesional);
    }

    @DeleteMapping("/{id}")
    public void deleteProfesional(@PathVariable Long id) {
        profesionalService.borrarProfesional(id);
    }

    @PutMapping("/{id}")
    public void putProfesional(@PathVariable Long id, @RequestBody Profesional nuevoProfesional) {
        profesionalService.actualizarProfesional(id, nuevoProfesional);
    }

    @GetMapping("/obtenerEspecialidadesPorProfesional")
    public Set<Especialidad> obtenerEspecialidadesPorProfesional(@RequestParam Long idProfesional) {
        Profesional profesional = repoProfesional.findById(idProfesional).orElse(null);
        if (profesional != null) {
            return profesional.getEspecialidades();
        } else {
            return Collections.emptySet();
        }
    }

    @GetMapping("/obtenerHorariosPorProfesional")
    public List<HorarioDisponibleDTO> obtenerHorariosPorProfesional(@RequestParam Long idProfesional,LocalDate dia) {
        Profesional profesional = repoProfesional.findById(idProfesional).orElse(null);

        if (profesional != null) {
            List<HorarioDisponibleDTO> horariosDisponibles = new ArrayList<>();

            List<LocalDateTime> horariosDelDia = profesionalService.obtenerHorariosDelProfesionalEnELDia( profesional,  dia);

            horariosDelDia.forEach(horario -> horariosDisponibles.add(new HorarioDisponibleDTO(horario, !profesionalService.profesionalTieneOcupadoElHorario(profesional, horario))));

            return horariosDisponibles;
        }

        return Collections.emptyList();
    }

}


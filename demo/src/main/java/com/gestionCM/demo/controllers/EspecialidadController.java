package com.gestionCM.demo.controllers;
import com.gestionCM.demo.models.Especialidad;
import com.gestionCM.demo.service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/especialidades")
@RestController
public class EspecialidadController {
    private final EspecialidadService especialidadService;

    @Autowired
    public EspecialidadController(EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @GetMapping
    public List<Especialidad> getEspecialidades(){
        return especialidadService.getEspecialidades();
    }

    @PostMapping
    public void postEspecialidad(@RequestBody Especialidad nuevaEspecialidad) {
        especialidadService.agregarEspecialidad(nuevaEspecialidad);
    }

    @DeleteMapping("/{id}")
    public void deleteEspecialidad(@PathVariable Long id) {
        especialidadService.borrarEspecialidad(id);
    }

    @PutMapping("/{id}")
    public void putEspecialidad(@PathVariable Long id, @RequestBody Especialidad nuevaEspecialidad) {
        especialidadService.actualizarEspecialidad(id, nuevaEspecialidad);
    }

}

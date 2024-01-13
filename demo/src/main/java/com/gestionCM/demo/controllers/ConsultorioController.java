package com.gestionCM.demo.controllers;
import com.gestionCM.demo.models.Consultorio;
import com.gestionCM.demo.service.ConsultorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/consultorios")
public class ConsultorioController {
    private final ConsultorioService consultorioService;

    @Autowired
    public ConsultorioController(ConsultorioService consultorioService) {
        this.consultorioService = consultorioService;
    }

    @GetMapping
    public List<Consultorio> getConsultorios() {
        return consultorioService.getConsultorios();
    }

    @PostMapping
    public void postConsultorio(@RequestBody Consultorio nuevoConsultorio) {
        consultorioService.agregarConsultorio(nuevoConsultorio);
    }

    @DeleteMapping("/{id}")
    public void deleteConsultorio(@PathVariable Long id) {
        consultorioService.borrarConsultorio(id);
    }

    @PutMapping("/{id}")
    public void putConsultorio(@PathVariable Long id, @RequestBody Consultorio nuevoConsultorio) {
        consultorioService.actualizarConsultorio(id, nuevoConsultorio);
    }
}



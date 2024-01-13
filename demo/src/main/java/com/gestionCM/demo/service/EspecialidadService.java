package com.gestionCM.demo.service;

import com.gestionCM.demo.models.Especialidad;
import com.gestionCM.demo.repository.RepoEspecialidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EspecialidadService {

    private final RepoEspecialidad repoEspecialidad;

    @Autowired
    public EspecialidadService(RepoEspecialidad repoEspecialidad) {
        this.repoEspecialidad = repoEspecialidad;
    }

    public List<Especialidad> getEspecialidades() {
        return repoEspecialidad.findAll();
    }

    public void agregarEspecialidad(Especialidad nuevaEspecialidad) {
        if (nombreEspecialidadEsUnico(nuevaEspecialidad.getNombreEspecialidad())) {
            repoEspecialidad.save(nuevaEspecialidad);
            System.out.println("La especialidad fue agregada correctamente");
        } else {
            System.out.println("El nombre de la especialidad ya existe. No se pudo agregar.");
        }
    }

    public void borrarEspecialidad(Long idEspecialidad) {
        Optional<Especialidad> optionalEspecialidad = repoEspecialidad.findById(idEspecialidad);

        if (optionalEspecialidad.isPresent()) {
            Especialidad especialidad = optionalEspecialidad.get();
            repoEspecialidad.delete(especialidad);
            System.out.println("La especialidad fue borrada correctamente");
        } else {
            System.out.println("No se encontró la especialidad que desea borrar");
        }
    }

    public void actualizarEspecialidad(Long id, Especialidad nuevaEspecialidad) {
        Optional<Especialidad> optionalEspecialidad = repoEspecialidad.findById(id);

        if (optionalEspecialidad.isPresent()) {
            Especialidad especialidadExistente = optionalEspecialidad.get();

            if (nombreEspecialidadEsUnico(nuevaEspecialidad.getNombreEspecialidad())) {
                especialidadExistente.setNombreEspecialidad(nuevaEspecialidad.getNombreEspecialidad());

                repoEspecialidad.save(especialidadExistente);
                System.out.println("La especialidad fue actualizada correctamente");
            } else {
                System.out.println("El nombre de la especialidad ya existe. No se pudo actualizar.");
            }
        } else {
            System.out.println("No se encontró la especialidad que desea actualizar");
        }
    }

    private boolean nombreEspecialidadEsUnico(String nombreEspecialidad) {
        return repoEspecialidad.findByNombreEspecialidad(nombreEspecialidad) == null;
    }

    public Set<Especialidad> guardarEspecialidades(Set<Especialidad> especialidades) {
        Set<Especialidad> especialidadesPersistidas = new HashSet<>();

        for (Especialidad especialidad : especialidades) {
            if (especialidad.getId() == null) {
                agregarEspecialidad(especialidad);
                Especialidad especialidadPersistida = repoEspecialidad.findByNombreEspecialidad(especialidad.getNombreEspecialidad());
                especialidadesPersistidas.add(especialidadPersistida);
            } else {
                especialidadesPersistidas.add(especialidad);
            }
        }
        return especialidadesPersistidas;
    }
}

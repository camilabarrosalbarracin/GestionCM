package com.gestionCM.demo.service;

import com.gestionCM.demo.models.Consultorio;
import com.gestionCM.demo.repository.RepoConsultorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultorioService {

    private final RepoConsultorio repoConsultorio;

    @Autowired
    public ConsultorioService(RepoConsultorio repoConsultorio) {
        this.repoConsultorio = repoConsultorio;
    }

    public List<Consultorio> getConsultorios() {
        return repoConsultorio.findAll();
    }

    public void agregarConsultorio(Consultorio nuevoConsultorio) {
        if (numeroConsultorioEsUnico(nuevoConsultorio.getNumeroConsultorio())) {
            repoConsultorio.save(nuevoConsultorio);
            System.out.println("El consultorio se agrego correctamente");
        } else {
            System.out.println("El número de consultorio ya existe. No se pudo agregar");
        }
    }

    public void borrarConsultorio(Long idConsultorio) {
        Optional<Consultorio> optionalConsultorio = repoConsultorio.findById(idConsultorio);

        if (optionalConsultorio.isPresent()) {
            Consultorio consultorio = optionalConsultorio.get();
            repoConsultorio.delete(consultorio);
            System.out.println("El consultorio fue borrado correctamente");
        } else {
            System.out.println("No se encontró el consultorio que desea borrar");
        }
    }

    public void actualizarConsultorio(Long id, Consultorio nuevoConsultorio) {
        Optional<Consultorio> optionalConsultorio = repoConsultorio.findById(id);

        if (optionalConsultorio.isPresent()) {
            Consultorio consultorioExistente = optionalConsultorio.get();

            if (numeroConsultorioEsUnico(nuevoConsultorio.getNumeroConsultorio())) {
                actualizarCamposConsultorio(consultorioExistente, nuevoConsultorio);
                repoConsultorio.save(consultorioExistente);
                System.out.println("El consultorio fue acrualizado correctamente");
            } else {
                System.out.println("El número de consultorio ya existe. No se pudo actualizar.");
            }
        } else {
            System.out.println("No se pudo actualizar el consultorio.");
        }
    }

    private boolean numeroConsultorioEsUnico(int numeroConsultorio) {
        return repoConsultorio.findByNumeroConsultorio(numeroConsultorio) == null;
    }

    private void actualizarCamposConsultorio(Consultorio consultorioExistente, Consultorio nuevoConsultorio) {
        consultorioExistente.setNumeroConsultorio(nuevoConsultorio.getNumeroConsultorio());
        consultorioExistente.setDireccionConsultorio(nuevoConsultorio.getDireccionConsultorio());
        consultorioExistente.setDiasAbiertos(nuevoConsultorio.getDiasAbiertos());
    }
}


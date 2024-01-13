package com.gestionCM.demo.service;

import com.gestionCM.demo.models.Paciente;
import com.gestionCM.demo.repository.RepoPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final RepoPaciente repoPaciente;

    @Autowired
    public PacienteService(RepoPaciente repoPaciente) {
        this.repoPaciente = repoPaciente;
    }

    public List<Paciente> getPacientes() {
        return repoPaciente.findAll();
    }

    public void agregarPaciente(Paciente nuevoPaciente) {
        if (dniPacienteEsUnico(nuevoPaciente.getDniPaciente())) {
            repoPaciente.save(nuevoPaciente);
            System.out.println("El paciente fue agregado correctamente");
        } else {
            System.out.println("El DNI del paciente ya existe. No se pudo agregar");
        }
    }

    public void borrarPaciente(Long idPaciente) {
        Optional<Paciente> optionalPaciente = repoPaciente.findById(idPaciente);

        if (optionalPaciente.isPresent()) {
            Paciente paciente = optionalPaciente.get();
            repoPaciente.delete(paciente);
            System.out.println("El paciente fue borrado correctamente");
        } else {
            System.out.println("No se encontró el paciente que desea borrar");
        }
    }

    public void actualizarPaciente(Long id, Paciente nuevoPaciente) {
        Optional<Paciente> optionalPaciente = repoPaciente.findById(id);

        if (optionalPaciente.isPresent()) {
            Paciente pacienteExistente = optionalPaciente.get();
            actualizarCamposPaciente(pacienteExistente, nuevoPaciente);
            repoPaciente.save(pacienteExistente);
            System.out.println("Los datos del paciente fueron correctamente actualizados");
        } else {
            System.out.println("No se encontró el paciente que desea actualizar");
        }
    }

    private boolean dniPacienteEsUnico(String dniPaciente) {
        return repoPaciente.findByDniPaciente(dniPaciente) == null;
    }

    private void actualizarCamposPaciente(Paciente pacienteExistente, Paciente nuevoPaciente) {
        pacienteExistente.setNombrePaciente(nuevoPaciente.getDniPaciente());
        pacienteExistente.setNombrePaciente(nuevoPaciente.getNombrePaciente());
        pacienteExistente.setApellidoPaciente(nuevoPaciente.getApellidoPaciente());
    }
}

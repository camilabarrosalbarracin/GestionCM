package com.gestionCM.demo.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class Paciente {
    private String nombrePaciente;
    private String apellidoPaciente;
    private String dniPaciente;
     //historial de turnos en BDD
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
}

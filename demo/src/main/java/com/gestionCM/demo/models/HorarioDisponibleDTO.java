package com.gestionCM.demo.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HorarioDisponibleDTO {
    private LocalDateTime fechaYHora;
    private boolean disponible;

    public HorarioDisponibleDTO(LocalDateTime fechaYHora, boolean disponible) {
        this.fechaYHora = fechaYHora;
        this.disponible = disponible;
    }
}
package com.gestionCM.demo.models;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TurnoModificarDTO {
    private Turno turno;
    private boolean disponible;

    public TurnoModificarDTO(Turno turno, boolean disponible) {
        this.turno = turno;
        this.disponible = disponible;
    }
}

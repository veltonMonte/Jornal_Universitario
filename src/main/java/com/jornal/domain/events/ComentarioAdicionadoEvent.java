package com.jornal.domain.events;

import com.jornal.domain.models.Comentario;
import lombok.Getter;

@Getter
public class ComentarioAdicionadoEvent extends JornalEvent {

    private final Comentario comentario;

    public ComentarioAdicionadoEvent(Object source, Comentario comentario) {
        super(source);
        this.comentario = comentario;
    }
}
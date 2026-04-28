package com.jornal.domain.events;

import com.jornal.domain.entities.Artigo;

import java.time.LocalDateTime;

public class ArtigoPublicadoEvent {

    private final Artigo artigo;
    private final LocalDateTime ocorridoEm;

    public ArtigoPublicadoEvent(Artigo artigo) {
        this.artigo = artigo;
        this.ocorridoEm = LocalDateTime.now();
    }

    public Artigo getArtigo() {
        return artigo;
    }

    public LocalDateTime getOcorridoEm() {
        return ocorridoEm;
    }
}
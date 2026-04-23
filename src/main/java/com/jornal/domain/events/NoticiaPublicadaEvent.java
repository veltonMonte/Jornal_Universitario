package com.jornal.domain.events;

import com.jornal.domain.models.Noticia;
import lombok.Getter;

@Getter
public class NoticiaPublicadaEvent extends JornalEvent{

    private final Noticia noticia;

    public NoticiaPublicadaEvent(Object source, Noticia noticia) {
        super(source);
        this.noticia = noticia;
    }
}

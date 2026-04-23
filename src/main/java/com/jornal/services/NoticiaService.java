package com.jornal.services;

import com.jornal.domain.models.Noticia;
import com.jornal.domain.models.Usuario;
import com.jornal.repositories.ComentarioRepository;
import com.jornal.repositories.NoticiaRepository;
import com.jornal.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;

import java.beans.Transient;
import java.util.UUID;

public class NoticiaService {
    private final NoticiaRepository noticiaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ComentarioRepository comentarioRepository;
    private final ApplicationEventPublisher eventPublisher;

    public NoticiaService(NoticiaRepository noticiaRepository, UsuarioRepository usuarioRepository, ComentarioRepository comentarioRepository, ApplicationEventPublisher eventPublisher) {
        this.noticiaRepository = noticiaRepository;
        this.usuarioRepository = usuarioRepository;
        this.comentarioRepository = comentarioRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public Noticia criarNoticia(UUID autorId, String titulo,
                                String conteudo, String resumo,
                                Noticia.Categoria categoria) {
        Usuario autor = usuarioRepository.findById(autorId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Noticia noticia = Noticia.builder()
                .titulo(titulo)
                .conteudo(conteudo)
                .resumo(resumo)
                .categoria(categoria)
                .autor(autor)
                .build();

        return noticiaRepository.save(noticia);
    }
}

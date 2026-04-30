package com.jornal.application.services;

import com.jornal.application.Dtos.ArtigoRequest;
import com.jornal.application.Dtos.ArtigoResponse;
import com.jornal.domain.entities.Artigo;
import com.jornal.domain.entities.Categoria;
import com.jornal.domain.entities.Usuario;
import com.jornal.domain.interfaces.IArtigoRepository;
import com.jornal.domain.interfaces.ICategoriaRepository;
import com.jornal.domain.valueobjects.StatusArtigo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ArtigoService {

    private final IArtigoRepository artigoRepository;
    private final UsuarioService usuarioService;
    private final ICategoriaRepository categoriaRepository;

    public ArtigoService(IArtigoRepository artigoRepository,
                         UsuarioService usuarioService,
                         ICategoriaRepository categoriaRepository) {
        this.artigoRepository = artigoRepository;
        this.usuarioService = usuarioService;
        this.categoriaRepository = categoriaRepository;
    }

    public ArtigoResponse criar(ArtigoRequest req) {
        Usuario usuario = usuarioService.encontrar(req.getUsuarioId());
        Categoria categoria = categoriaRepository.findById(req.getCategoriaId())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada: " + req.getCategoriaId()));
        Artigo artigo = new Artigo(req.getTitulo(), req.getConteudo(), usuario, categoria);
        artigo.setSubtitulo(req.getSubtitulo());
        artigo.setTags(req.getTags());
        artigo.setImagemCapa(req.getImagemCapa());
        return ArtigoResponse.de(artigoRepository.save(artigo));
    }

    public ArtigoResponse buscarPorId(UUID id) {
        return ArtigoResponse.de(encontrar(id));
    }

    public List<ArtigoResponse> listarPublicados() {
        return artigoRepository.findByStatus(StatusArtigo.PUBLICADO).stream()
                .map(ArtigoResponse::de)
                .collect(Collectors.toList());
    }

    public List<ArtigoResponse> listarPorUniversidade(UUID universidadeId) {
        return artigoRepository.findByUsuarioUniversidadeId(universidadeId).stream()
                .map(ArtigoResponse::de)
                .collect(Collectors.toList());
    }

    public ArtigoResponse publicar(UUID id) {
        Artigo artigo = encontrar(id);
        artigo.publicar();
        return ArtigoResponse.de(artigoRepository.save(artigo));
    }

    public ArtigoResponse atualizar(UUID id, ArtigoRequest req) {
        Artigo artigo = encontrar(id);
        artigo.setTitulo(req.getTitulo());
        artigo.setSubtitulo(req.getSubtitulo());
        artigo.setConteudo(req.getConteudo());
        artigo.setTags(req.getTags());
        return ArtigoResponse.de(artigoRepository.save(artigo));
    }

    public void remover(UUID id) {
        artigoRepository.deleteById(id);
    }

    public Artigo encontrar(UUID id) {
        return artigoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Artigo não encontrado: " + id));
    }
}
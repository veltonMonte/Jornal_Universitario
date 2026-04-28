package com.jornal.infrastructure.storage;

import java.io.InputStream;
import java.util.UUID;

public class ImagemStorage {

    private final String bucketNome;
    private final String urlBase;

    public ImagemStorage(String bucketNome, String urlBase) {
        this.bucketNome = bucketNome;
        this.urlBase = urlBase;
    }

    public String upload(InputStream imagem, String extensao) {
        String chave = UUID.randomUUID() + "." + extensao;
        // TODO: implementar upload real (AWS S3, Cloudinary, disco local, etc.)
        System.out.printf("[STORAGE] Upload da imagem: %s/%s%n", bucketNome, chave);
        return urlBase + "/" + chave;
    }

    public void remover(String urlImagem) {
        String chave = urlImagem.replace(urlBase + "/", "");
        // TODO: implementar remoção real no serviço de storage
        System.out.printf("[STORAGE] Removendo imagem: %s/%s%n", bucketNome, chave);
    }

    public String gerarUrlPublica(String chave) {
        return urlBase + "/" + chave;
    }
}

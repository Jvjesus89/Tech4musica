package br.tech4music.artista.service;

import java.util.List;
import java.util.Optional;


import br.tech4music.artista.compartilhado.ArtistaDto;

public interface ArtistaService {
    ArtistaDto criarArtista(ArtistaDto artista);
    List<ArtistaDto> obterTodos();
    Optional<ArtistaDto> obterPorId(String id);
    void removerArtista(String id);
    ArtistaDto atualizarArtista(String id, ArtistaDto artista);
}

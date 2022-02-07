package br.tech4musica.musica.service;

import java.util.List;
import java.util.Optional;

import br.tech4musica.musica.compartilhado.MusicaDto;


public interface MusicaService {
    MusicaDto criarMusica(MusicaDto musica);
    List<MusicaDto> obterTodos();
    Optional<MusicaDto> obterPorId(String id);
    List<MusicaDto> obterPorArtista(String Artistamusica);
    void removerMusica(String id);
    MusicaDto atualizarMusica(String id, MusicaDto musica);
}

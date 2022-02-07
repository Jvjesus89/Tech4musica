package br.tech4music.artista.clienteHTTP;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.tech4music.artista.compartilhado.Musica;




@FeignClient(name= "musicas-ms", fallback = MusicasFeignClientFallback.class)
public interface ArtistasFeignClient {
    @GetMapping(path = "/api/musica/{artistamusica}/lista")
    List<Musica> obterMusicaspArtistas(@PathVariable String artistamusica);
}

@Component
class MusicasFeignClientFallback implements ArtistasFeignClient {

    @Override
    public List<Musica> obterMusicaspArtistas (String artistamusica) {
        return new ArrayList<>();
    }

}

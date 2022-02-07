package br.tech4musica.musica.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.tech4musica.musica.model.Musica;


@Repository
public interface MusicaRepositorio extends MongoRepository<Musica, String> {

	List<Musica> findByArtistaMusica(String artistamusica);
    
}

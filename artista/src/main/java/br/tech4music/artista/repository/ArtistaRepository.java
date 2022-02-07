package br.tech4music.artista.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.tech4music.artista.model.Artista;



@Repository
public interface ArtistaRepository extends MongoRepository<Artista, String> {
    
}

package br.tech4music.artista.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.tech4music.artista.clienteHTTP.ArtistasFeignClient;
import br.tech4music.artista.compartilhado.ArtistaDto;
import br.tech4music.artista.model.Artista;
import br.tech4music.artista.repository.ArtistaRepository;


@Service
public class ArtistaServiceImpl implements ArtistaService {
    @Autowired
    private ArtistaRepository repo;

    @Autowired
    private ArtistasFeignClient artistaMsClient;

    @Override
    public ArtistaDto criarArtista(ArtistaDto artista) {
        return salvarArtista(artista);
    }

    @Override
    public List<ArtistaDto> obterTodos() {
        List<Artista> artista = repo.findAll();

        return artista.stream()
            .map(artistas -> new ModelMapper().map(artistas, ArtistaDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public Optional<ArtistaDto> obterPorId(String id) {
       Optional<Artista> artista = repo.findById(id);

        
       if(artista.isPresent()) {
           ArtistaDto dto = new ModelMapper().map(artista.get(), ArtistaDto.class);
           dto.setMusicas(artistaMsClient.obterMusicaspArtistas(id));
           return Optional.of(dto);
       }

       return Optional.empty();
    }

    @Override
    public void removerArtista(String id) {
        repo.deleteById(id);
    }

    @Override
    public ArtistaDto atualizarArtista(String id, ArtistaDto artista) {
        artista.setId(id);
        return salvarArtista(artista);
    }

    private ArtistaDto salvarArtista(ArtistaDto artista) {
        ModelMapper mapper = new ModelMapper();
        Artista artistaEntidade = mapper.map(artista, Artista.class);
        artistaEntidade = repo.save(artistaEntidade);

        return mapper.map(artistaEntidade, ArtistaDto.class);
    }

}

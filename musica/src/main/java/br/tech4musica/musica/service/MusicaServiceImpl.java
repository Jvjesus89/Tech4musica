package br.tech4musica.musica.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import br.tech4musica.musica.compartilhado.MusicaDto;
import br.tech4musica.musica.model.Musica;
import br.tech4musica.musica.repository.MusicaRepositorio;





@Service
public class MusicaServiceImpl implements MusicaService {
    
    @Autowired
    private MusicaRepositorio repo;

    @Override
    public MusicaDto criarMusica(MusicaDto musica) {
        return salvarMusica(musica);
    }

    @Override
    public List<MusicaDto> obterTodos() {
        List<Musica> musicas = repo.findAll();

        return musicas.stream()
            .map(musica -> new ModelMapper().map(musica, MusicaDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public Optional<MusicaDto> obterPorId(String id) {
        Optional<Musica> musica = repo.findById(id);

       if(musica.isPresent()) {
           return Optional.of(new ModelMapper().map(musica.get(), MusicaDto.class));
       }

       return Optional.empty();
    }

    @Override
    public List<MusicaDto> obterPorArtista(String artistamusica) {
        List<Musica> musicas = repo.findByArtistaMusica(artistamusica);

        return musicas.stream()
            .map(musica -> new ModelMapper().map(musica, MusicaDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public void removerMusica(String id) {
        repo.deleteById(id);
    }

    @Override
    public MusicaDto atualizarMusica(String id, MusicaDto musica) {
        musica.setId(id);
        return salvarMusica(musica);
    }

    private MusicaDto salvarMusica(MusicaDto musica) {
        ModelMapper mapper = new ModelMapper();
        Musica musicaNova = mapper.map(musica, Musica.class);
        musicaNova = repo.save(musicaNova);

        return mapper.map(musicaNova, MusicaDto.class);
    }
    
}

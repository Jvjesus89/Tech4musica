package br.tech4music.artista.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.tech4music.artista.compartilhado.ArtistaDto;
import br.tech4music.artista.model.Artista;
import br.tech4music.artista.service.ArtistaService;
import br.tech4music.artista.view.model.ArtistaModeloRequest;
import br.tech4music.artista.view.model.ArtistaModeloResponse;
import br.tech4music.artista.view.model.ArtistaModeloResponseDetalhes;




@RestController
@RequestMapping("/api/artistas")
public class ArtistaController {
    @Autowired
    private ArtistaService service;

    @GetMapping(value="/status")
    public String statusServico(@Value("${local.server.port}") String porta) {
        return String.format("Serviço ativo e executando na porta %s", porta);
    }
    

    @PostMapping
    public ResponseEntity<ArtistaModeloResponse> criarArtista(@RequestBody @Valid ArtistaModeloRequest artista) {
        ModelMapper mapper = new ModelMapper();
        ArtistaDto dto = mapper.map(artista, ArtistaDto.class);
        dto = service.criarArtista(dto);
        return new ResponseEntity<>(mapper.map(dto, ArtistaModeloResponse.class), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<ArtistaModeloResponse>> obterTodos() {
        List<ArtistaDto> dtos = service.obterTodos();

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<ArtistaModeloResponse> resp = dtos.stream()
                    .map(dto -> mapper.map(dto, ArtistaModeloResponse.class))
                    .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    
    @GetMapping(value="/{id}/musicas")
    public ResponseEntity<ArtistaModeloResponseDetalhes> obterPorId(@PathVariable String id) {
        Optional<ArtistaDto> artista = service.obterPorId(id);

        if(artista.isPresent()) {
            return new ResponseEntity<>(
                new ModelMapper().map(artista.get(), ArtistaModeloResponseDetalhes.class), 
                HttpStatus.OK
            );
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<ArtistaModeloResponse> atualizarArtista(@PathVariable String id,
        @Valid @RequestBody Artista artista) {
        ModelMapper mapper = new ModelMapper();
        ArtistaDto dto = mapper.map(artista, ArtistaDto.class);
        dto = service.atualizarArtista(id, dto);

        return new ResponseEntity<>(mapper.map(dto, ArtistaModeloResponse.class), HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> removerArtista(@PathVariable String id) {
        service.removerArtista(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 
}

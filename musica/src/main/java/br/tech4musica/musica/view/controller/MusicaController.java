package br.tech4musica.musica.view.controller;

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


import br.tech4musica.musica.compartilhado.MusicaDto;
import br.tech4musica.musica.service.MusicaService;
import br.tech4musica.musica.view.model.MusicaModeloAlteracao;
import br.tech4musica.musica.view.model.MusicaModeloInclusao;
import br.tech4musica.musica.view.model.MusicaModeloResponse;

@RestController
@RequestMapping("/api/musica")
public class MusicaController {
    @Autowired
    private MusicaService service;

    @GetMapping(value="/status")
    public String statusServico(@Value("${local.server.port}") String porta) {
        return String.format("Serviço ativo e executando na porta %s", porta);
    }    

    @PostMapping
    public ResponseEntity<MusicaModeloResponse> criarMusica(@RequestBody @Valid MusicaModeloInclusao musica) {
        ModelMapper mapper = new ModelMapper();
        MusicaDto dto = mapper.map(musica, MusicaDto.class);
        dto = service.criarMusica(dto);
        return new ResponseEntity<>(mapper.map(dto, MusicaModeloResponse.class), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<MusicaModeloResponse>> obterTodos() {
        List<MusicaDto> dtos = service.obterTodos();

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<MusicaModeloResponse> resp = dtos.stream()
                    .map(dto -> mapper.map(dto, MusicaModeloResponse.class))
                    .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping(value="/{dono}/musicas")
    public ResponseEntity<List<MusicaModeloResponse>> obterPorArtista(@PathVariable String artistamusica) {
        List<MusicaDto> dtos = service.obterPorArtista(artistamusica);

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<MusicaModeloResponse> resp = dtos.stream()
                    .map(dto -> mapper.map(dto, MusicaModeloResponse.class))
                    .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<MusicaModeloResponse> obterPorId(@PathVariable String id) {
        Optional<MusicaDto> Musica = service.obterPorId(id);
        

        if(Musica.isPresent()) {
            return new ResponseEntity<>(
                new ModelMapper().map(Musica.get(), MusicaModeloResponse.class), 
                HttpStatus.OK
            );
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<MusicaModeloResponse> atualizarMusica(@PathVariable String id,
        @Valid @RequestBody MusicaModeloAlteracao Musica) {
        ModelMapper mapper = new ModelMapper();
        MusicaDto dto = mapper.map(Musica, MusicaDto.class);
        dto = service.atualizarMusica(id, dto);

        return new ResponseEntity<>(mapper.map(dto, MusicaModeloResponse.class), HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> removerMusica(@PathVariable String id) {
        service.removerMusica(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package br.tech4music.artista.view.model;


import java.util.List;

import org.springframework.data.annotation.Id;

import br.tech4music.artista.compartilhado.Musica;


public class ArtistaModeloResponseDetalhes {
    @Id
    private String id;
    private String nome;
    private String sobrenome;
    private List<Musica> musicas;


   
    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

}

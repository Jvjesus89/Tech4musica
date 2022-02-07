package br.tech4music.artista.view.model;

import org.springframework.data.annotation.Id;

public class ArtistaModeloResponse {
    @Id
    private String id;
    private String nome;
    private String sobrenome;


   
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

package br.tech4music.artista.compartilhado;

import org.springframework.data.annotation.Id;

public class Musica {
    @Id
    private String id;
    private String nome;
    private String album;
    private Integer anolançamento;
    private String artistamusica;

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
    public String getAlbum() {
        return album;
    }
    public void setAlbum(String album) {
        this.album = album;
    }
    public Integer getAnolançamento() {
        return anolançamento;
    }
    public void setAnolançamento(Integer anolançamento) {
        this.anolançamento = anolançamento;
    }
    public String getArtistamusica() {
        return artistamusica;
    }
    public void setAntiga(boolean b) {
    }
}
    
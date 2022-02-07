package br.tech4musica.musica.view.model;


public class MusicaModeloInclusao {
    
    private String id;
    private String nome;
    private String album;
    private Integer anolançamento;
    private String Artistamusica;

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
        return Artistamusica;
    }
    public void setArtistamusica(String artistamusica) {
        Artistamusica = artistamusica;
    }
}
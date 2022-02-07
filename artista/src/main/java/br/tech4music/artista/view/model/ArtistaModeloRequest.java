package br.tech4music.artista.view.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

public class ArtistaModeloRequest {
    

    @Id
    private String id;
    @NotBlank(message = "O nome deve possuir alguma letra")
    @NotEmpty(message = "O nome deverá ser preenchido")
    @Size(min = 3, message = "O nome deverá ter pelo menos 3 caracteres")
    private String nome;
    @NotBlank(message = "O sobrenome deve possuir alguma letra")
    @NotEmpty(message = "O sobrenome deverá ser preenchido")
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

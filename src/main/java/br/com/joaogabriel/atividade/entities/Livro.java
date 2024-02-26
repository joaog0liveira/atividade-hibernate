package br.com.joaogabriel.atividade.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private int anoPub;
    private String isbn;

    public Livro() {
    }

    public Livro(Integer id, String titulo, int anoPub, String isbn) {
        this.id = id;
        this.titulo = titulo;
        this.anoPub = anoPub;
        this.isbn = isbn;
    }


    public int getId() {
        return id != null ? id.intValue() : 0;
    }

   /* public void setId(Integer id) {
        this.id = id;
    }*/

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoPub() {
        return anoPub;
    }

    public void setAnoPub(int anoPub) {
        this.anoPub = anoPub;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return id == livro.id && anoPub == livro.anoPub && Objects.equals(titulo, livro.titulo) && Objects.equals(isbn, livro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, anoPub, isbn);
    }
}

package br.com.joaogabriel.atividade.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Autor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @OneToMany(mappedBy = "autor")
    private List<Livro> livros;

    @ManyToMany
    @JoinTable(
            name = "autor_editora",
            joinColumns = @JoinColumn(name = "autor_id"),
            inverseJoinColumns = @JoinColumn(name = "editora_id")
    )
    private List<Editora> editoras;

    public Autor() {
    }

    public Autor(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

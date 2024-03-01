package br.com.joaogabriel.atividade;

import br.com.joaogabriel.atividade.model.Autor;
import br.com.joaogabriel.atividade.model.Editora;
import br.com.joaogabriel.atividade.model.Livro;
import br.com.joaogabriel.atividade.repository.AutorRepository;
import br.com.joaogabriel.atividade.repository.EditoraRepository;
import br.com.joaogabriel.atividade.repository.LivroRepository;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        LivroRepository livroRepository = new LivroRepository(sessionFactory);
        EditoraRepository editoraRepository = new EditoraRepository(sessionFactory);
        AutorRepository autorRepository = new AutorRepository(sessionFactory);

        testeCrudLivro(livroRepository, editoraRepository, autorRepository);
        testeCrudEditora(editoraRepository);
        testeCrudAutor(autorRepository);

        int idTeste = 2; // Substitua pelo ID do livro desejado

        // autores por livro
        List<String> autoresDoLivro = livroRepository.buscarAutoresPorLivro(idTeste);
        System.out.println(autoresDoLivro);

        // livros por autor
        List<Livro> livrosDoAutor = livroRepository.quaisLivrosPorAutor(idTeste);
        System.out.println(livrosDoAutor);

        // editoras por livro
        List<String> editorasDoLivro = livroRepository.quaisEditorasPorLivro(idTeste);
        System.out.println(editorasDoLivro);

        // livros por editora
        List<Livro> livrosDaEditora = livroRepository.quaisLivrosPorEditora(idTeste);
        System.out.println(livrosDaEditora);

        // autores da editora
        List<String> autoresDaEditora = editoraRepository.quaisAutoresPorEditora(idTeste);
        System.out.println(autoresDaEditora);

        // editoras do autor
        List<String> editorasDoAutor = autorRepository.quaisEditorasPorAutor(idTeste);
        System.out.println(editorasDoAutor);

        sessionFactory.close();
    }

    private static void testeCrudLivro(LivroRepository livroRepository,
                                       EditoraRepository editoraRepository,
                                       AutorRepository autorRepository) {
    }

    private static void testeCrudEditora(EditoraRepository editoraRepository) {


    }

    private static void testeCrudAutor(AutorRepository autorRepository) {

    }
}

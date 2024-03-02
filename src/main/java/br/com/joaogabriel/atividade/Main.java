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

        /*
        a partir daqui são algumas buscas especificas
        caso não queria fazer elas, basta comentar
        */
        int idTeste = 1; // substitua pelo ID desejado, esse id irá fazer as seguintes buscas abaixo

        List<String> autoresDoLivro = livroRepository.buscarAutoresPorLivro(idTeste);
        System.out.println(autoresDoLivro);

        List<Livro> livrosDoAutor = livroRepository.quaisLivrosPorAutor(idTeste);
        System.out.println(livrosDoAutor);

        List<String> editorasDoLivro = livroRepository.quaisEditorasPorLivro(idTeste);
        System.out.println(editorasDoLivro);

        List<Livro> livrosDaEditora = livroRepository.quaisLivrosPorEditora(idTeste);
        System.out.println(livrosDaEditora);

        List<String> autoresDaEditora = editoraRepository.quaisAutoresPorEditora(idTeste);
        System.out.println(autoresDaEditora);

        List<String> editorasDoAutor = autorRepository.quaisEditorasPorAutor(idTeste);
        System.out.println(editorasDoAutor);

        sessionFactory.close();
    }

    private static void testeCrudLivro(LivroRepository livroRepository,
                                       EditoraRepository editoraRepository,
                                       AutorRepository autorRepository) {
        Livro livro = new Livro();
        livro.setTitulo("O Senhor dos Anéis");
        livro.setAnoPub(1954);
        livro.setIsbn("11111");

        Editora editora = new Editora();
        editora.setNome("HarperCollins");
        editoraRepository.salvarEditora(editora);

        Autor autor = new Autor();
        autor.setNome("J.R.R. Tolkien");
        autorRepository.salvarAutor(autor);

        livro.setEditora(editora);
        livro.setAutor(autor);

        System.out.println();

        livroRepository.salvarLivro(livro);
        System.out.println("Livro criado: " + livro.getTitulo());

        Livro livroSalvo = livroRepository.buscarLivro(livro.getId());
        System.out.println("Livro encontrado: " + livroSalvo.getTitulo());

        livroSalvo.setTitulo("O Senhor dos Anéis: A Sociedade do Anel");
        livroRepository.atualizarLivro(livroSalvo);
        System.out.println("Livro atualizado: " + livroSalvo.getTitulo());

        livroRepository.excluirLivro(livroSalvo.getId());
        System.out.println("Livro excluído.");

    }

    private static void testeCrudEditora(EditoraRepository editoraRepository) {
        Editora editora = new Editora();
        editora.setNome("Pearson");

        System.out.println();

        editoraRepository.salvarEditora(editora);
        System.out.println("Editora criada: " + editora.getNome());

        Editora editoraSalva = editoraRepository.buscarEditora(editora.getId());
        System.out.println("Editora encontrada: " + editoraSalva.getNome());

        editoraSalva.setNome("Nova Editora XYZ");
        editoraRepository.atualizarEditora(editoraSalva);
        System.out.println("Editora atualizada: " + editoraSalva.getNome());

        editoraRepository.excluirEditora(editoraSalva.getId());
        System.out.println("Editora excluída.");

    }

    private static void testeCrudAutor(AutorRepository autorRepository) {
        Autor autor = new Autor();
        autor.setNome("Taylor Jenkins Reid");

        System.out.println();

        autorRepository.salvarAutor(autor);
        System.out.println("Autor(a) criado: " + autor.getNome());

        Autor autorSalvo = autorRepository.buscarAutor(autor.getId());
        System.out.println("Autor(a) encontrado: " + autorSalvo.getNome());


        autorSalvo.setNome("Rainbow Rowell");
        autorRepository.atualizarAutor(autorSalvo);
        System.out.println("Autor(a) atualizado: " + autorSalvo.getNome());

        autorRepository.excluirAutor(autorSalvo.getId());
        System.out.println("Autor(a) excluído.");

    }
}

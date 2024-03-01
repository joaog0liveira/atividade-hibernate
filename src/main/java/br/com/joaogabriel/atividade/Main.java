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

        // Teste do CRUD para Livro
        testeCrudLivro(livroRepository, editoraRepository, autorRepository);

        // Teste do CRUD para Editora
        testeCrudEditora(editoraRepository);

        // Teste do CRUD para Autor
        testeCrudAutor(autorRepository);

        int idTeste = 2; // Substitua pelo ID do livro desejado

        //autores por livro
        List<String> autoresDoLivro = livroRepository.buscarAutoresPorLivro(idTeste);
        System.out.println(autoresDoLivro);

        // quaisLivrosPorAutor
        List<Livro> livrosDoAutor = livroRepository.quaisLivrosPorAutor(idTeste);
        System.out.println(livrosDoAutor);

        // quaisEditorasPorLivro
        List<String> editorasDoLivro = livroRepository.quaisEditorasPorLivro(idTeste);
        System.out.println(editorasDoLivro);

        // quaisLivrosPorEditora
        List<Livro> livrosDaEditora = livroRepository.quaisLivrosPorEditora(idTeste);
        System.out.println(livrosDaEditora);

        // quaisAutoresPorEditora
        List<String> autoresDaEditora = editoraRepository.quaisAutoresPorEditora(idTeste);
        System.out.println(autoresDaEditora);

        // quaisEditorasPorAutor
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
        livro.setIsbn("978-0-618-51750-2");

        Editora editora = new Editora();
        editora.setNome("HarperCollins");
        editoraRepository.salvarEditora(editora);

        Autor autor = new Autor();
        autor.setNome("J.R.R. Tolkien");
        autorRepository.salvarAutor(autor);

        livro.setEditora(editora);
        livro.setAutor(autor);

        System.out.println("\n=== Teste CRUD para Livro ===");

        // Create (Salvar Livro)
        livroRepository.salvarLivro(livro);
        System.out.println("Livro criado: " + livro.getTitulo());

        // Read (Buscar Livro)
        Livro livroSalvo = livroRepository.buscarLivro(livro.getId());
        System.out.println("Livro encontrado: " + livroSalvo.getTitulo());

        // Update (Atualizar Livro)
        livroSalvo.setTitulo("O Senhor dos Anéis: A Sociedade do Anel");
        livroRepository.salvarLivro(livroSalvo);
        System.out.println("Livro atualizado: " + livroSalvo.getTitulo());

        // Delete (Excluir Livro)
        livroRepository.excluirLivro(livroSalvo.getId());
        System.out.println("Livro excluído.");

    }

    private static void testeCrudEditora(EditoraRepository editoraRepository) {
        Editora editora = new Editora();
        editora.setNome("Editora XYZ");

        System.out.println("\n=== Teste CRUD para Editora ===");

        // Create (Salvar Editora)
        editoraRepository.salvarEditora(editora);
        System.out.println("Editora criada: " + editora.getNome());

        // Read (Buscar Editora)
        Editora editoraSalva = editoraRepository.buscarEditora(editora.getId());
        System.out.println("Editora encontrada: " + editoraSalva.getNome());

        // Update (Atualizar Editora)
        editoraSalva.setNome("Nova Editora XYZ");
        editoraRepository.salvarEditora(editoraSalva);
        System.out.println("Editora atualizada: " + editoraSalva.getNome());

        // Delete (Excluir Editora)
        editoraRepository.excluirEditora(editoraSalva.getId());
        System.out.println("Editora excluída.");

    }

    private static void testeCrudAutor(AutorRepository autorRepository) {
        Autor autor = new Autor();
        autor.setNome("George Orwell");

        System.out.println("\n=== Teste CRUD para Autor ===");

        // Create (Salvar Autor)
        autorRepository.salvarAutor(autor);
        System.out.println("Autor criado: " + autor.getNome());

        // Read (Buscar Autor)
        Autor autorSalvo = autorRepository.buscarAutor(autor.getId());
        System.out.println("Autor encontrado: " + autorSalvo.getNome());

        // Update (Atualizar Autor)
        autorSalvo.setNome("George Orwell Jr.");
        autorRepository.salvarAutor(autorSalvo);
        System.out.println("Autor atualizado: " + autorSalvo.getNome());

        // Delete (Excluir Autor)
        autorRepository.excluirAutor(autorSalvo.getId());
        System.out.println("Autor excluído.");

    }
}

package br.com.joaogabriel.atividade.repository;

import br.com.joaogabriel.atividade.model.Livro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class LivroRepository {
    private final SessionFactory sessionFactory;

    public LivroRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void salvarLivro(Livro livro) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            if (livro.getId() != 0) {
                session.update(livro);
            } else {
                // Se a entidade não tem uma chave primária, use session.save() para inserir
                session.save(livro);
            }

            transaction.commit();
        }
    }

    public Livro buscarLivro(int livroId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Livro.class, livroId);
        }
    }

    public List<Livro> listarLivros() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Livro", Livro.class).list();
        }
    }

    public void atualizarLivro(Livro livro) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(livro);
            transaction.commit();
        }
    }

    public void excluirLivro(int livroId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Livro livro = session.get(Livro.class, livroId);
            if (livro != null) {
                session.delete(livro);
                transaction.commit();
            }
        }
    }



    public List<String> buscarAutoresPorLivro(int livroId) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT DISTINCT a.nome FROM Livro l JOIN l.autor a WHERE l.id = :livroId";
            return session.createQuery(jpql, String.class)
                    .setParameter("livroId", livroId)
                    .getResultList();
        }
    }


    public List<Livro> quaisLivrosPorAutor(int autorId) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT DISTINCT a.nome FROM Livro l JOIN l.autor a WHERE a.id = :autorId";
            return session.createQuery(jpql, Livro.class)
                    .setParameter("autorId", autorId)
                    .getResultList();
        }
    }


    public List<String> quaisEditorasPorLivro(int livroId) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT DISTINCT e.nome FROM Livro l JOIN l.editora e WHERE l.id = :livroId";
            return session.createQuery(jpql, String.class)
                    .setParameter("livroId", livroId)
                    .getResultList();
        }
    }

    public List<Livro> quaisLivrosPorEditora(int editoraId) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT DISTINCT l.titulo FROM Livro l WHERE l.editora.id = :editoraId";
            return session.createQuery(jpql, Livro.class)
                    .setParameter("editoraId", editoraId)
                    .getResultList();
        }
    }

}

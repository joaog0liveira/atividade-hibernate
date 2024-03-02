package br.com.joaogabriel.atividade.repository;

import br.com.joaogabriel.atividade.model.Autor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class AutorRepository {
    private final SessionFactory sessionFactory;

    public AutorRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void salvarAutor(Autor autor) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(autor);
            transaction.commit();
        }
    }

    public Autor buscarAutor(int autorId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Autor.class, autorId);
        }
    }

    public List<Autor> listarAutores() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Autor", Autor.class).list();
        }
    }

    public void atualizarAutor(Autor autor) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(autor);
            transaction.commit();
        }
    }

    public void excluirAutor(int autorId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Autor autor = session.get(Autor.class, autorId);
            if (autor != null) {
                session.delete(autor);
                transaction.commit();
            }
        }
    }

    public List<String> quaisEditorasPorAutor(int autorId) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT DISTINCT e.nome FROM Editora e JOIN e.livros l WHERE l.autor.id = :autorId";
            return session.createQuery(jpql, String.class)
                    .setParameter("autorId", autorId)
                    .getResultList();
        }
    }
}

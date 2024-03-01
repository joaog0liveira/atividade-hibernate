package br.com.joaogabriel.atividade.repository;
import br.com.joaogabriel.atividade.model.Autor;
import br.com.joaogabriel.atividade.model.Editora;
import br.com.joaogabriel.atividade.model.Livro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class EditoraRepository {
    private final SessionFactory sessionFactory;

    public EditoraRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void salvarEditora(Editora editora) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(editora);
            transaction.commit();
        }
    }

    public Editora buscarEditora(int editoraId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Editora.class, editoraId);
        }
    }

    public List<Editora> listarEditoras() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Editora", Editora.class).list();
        }
    }

    public void atualizarEditora(Editora editora) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(editora);
            transaction.commit();
        }
    }

    public void excluirEditora(int editoraId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Editora editora = session.get(Editora.class, editoraId);
            if (editora != null) {
                session.delete(editora);
                transaction.commit();
            }
        }
    }

    public List<String> quaisAutoresPorEditora(int editoraId) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT DISTINCT a.nome FROM Autor a JOIN a.livros l WHERE l.editora.id = :editoraId";
            return session.createQuery(jpql, String.class)
                    .setParameter("editoraId", editoraId)
                    .getResultList();
        }
    }


}
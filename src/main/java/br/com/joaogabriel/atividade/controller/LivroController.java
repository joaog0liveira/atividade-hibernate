package br.com.joaogabriel.atividade.controller;

import br.com.joaogabriel.atividade.entities.Livro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;

public class LivroController implements Serializable {

    private SessionFactory sessionFactory;

    public LivroController() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public void create(Livro livro) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.save(livro);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void update(Livro livro) {
        Session session = null;
        Transaction transaction = null;

        try {
            if (livro == null) {
                // Tratar o caso em que o livro passado é nulo
                throw new IllegalArgumentException("Livro não pode ser nulo.");
            }

            // Verificar a existência do livro
            Livro existente = buscar(livro.getId());

            if (existente == null) {
                throw new EntityNotFoundException("Livro não encontrado!");
            }

            // Iniciar a transação
            session = getSession();
            transaction = session.beginTransaction();

            // Atualizar as propriedades do livro existente com as do livro passado como parâmetro
            existente.setTitulo(livro.getTitulo());
            existente.setAnoPub(livro.getAnoPub());
            existente.setIsbn(livro.getIsbn());

            // Realizar a atualização
            session.update(existente);

            // Commit da transação
            transaction.commit();
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            // Tratar exceções específicas
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Ou logar a exceção, dependendo da sua preferência
        } catch (Exception e) {
            // Tratar exceções genéricas
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Ou logar a exceção, dependendo da sua preferência
        } finally {
            // Fechar a sessão
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void delete(Integer id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();

            Livro titulo = buscar(id);

            if ("Livro não encontrado!".equals(titulo)) {
                throw new EntityNotFoundException("Livro não encontrado!");
            }

            Livro livro = session.get(Livro.class, id);
            if (livro != null) {
                transaction = session.beginTransaction();
                session.remove(livro);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public Livro buscar(Integer id) {
        Session session = null;
        try {
            session = getSession();
            Livro livro = session.get(Livro.class, id);

            if (livro != null) {
                return new Livro(livro.getId(), livro.getTitulo(), livro.getAnoPub(), livro.getIsbn());
            } else {
                return null;
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}


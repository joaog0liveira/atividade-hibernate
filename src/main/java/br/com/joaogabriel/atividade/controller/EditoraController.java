package br.com.joaogabriel.atividade.controller;

import br.com.joaogabriel.atividade.entities.Editora;
import br.com.joaogabriel.atividade.entities.Livro;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;

public class EditoraController implements Serializable {
    private SessionFactory sessionFactory;

    public EditoraController() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public void create(Editora editora) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.save(editora);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void update(Editora editora) {
        Session session = null;
        Transaction transaction = null;

        try {
            if (editora == null) {
                throw new IllegalArgumentException("Erro!");
            }

            // Verificar a existência do livro
            Editora existente = buscar(editora.getId());

            if (existente == null) {
                throw new EntityNotFoundException("Editora não encontrada!");
            }

            session = getSession();
            transaction = session.beginTransaction();

            existente.setNome(editora.getNome());

            session.update(existente);

            transaction.commit();
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
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

    public void delete(Integer id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();

            Editora nome = buscar(id);

            if ("Editora não encontrada!".equals(nome)) {
                throw new EntityNotFoundException("Editora não encontrada!");
            }
 
            Editora editora = session.get(Editora.class, id);
            if (editora != null) {
                transaction = session.beginTransaction();
                session.remove(editora);
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


    public Editora buscar(Integer id) {
        Session session = null;
        try {
            session = getSession();
            Editora editora = session.get(Editora.class, id);

            if (editora != null) {
                return new Editora(editora.getId(), editora.getNome());
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

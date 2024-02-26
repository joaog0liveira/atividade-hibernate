package br.com.joaogabriel.atividade;

import br.com.joaogabriel.atividade.controller.LivroController;
import br.com.joaogabriel.atividade.entities.Livro;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LivroController livroController = new LivroController();

        // Supondo que você tenha um livro existente no banco de dados com o ID 1
        int livroId = 1;

        // Buscar o livro existente pelo ID
        Livro livroExistente = livroController.buscar(livroId);

        if (livroExistente != null) {
            // Exibir detalhes do livro antes da atualização
            System.out.println("Detalhes do Livro antes da Atualização:");
            exibirDetalhesLivro(livroExistente);

            // Modificar algumas propriedades do livro
            livroExistente.setTitulo("Novo Título");
            livroExistente.setAnoPub(2023);
            livroExistente.setIsbn("987654321");

            // Atualizar o livro no banco de dados
            livroController.update(livroExistente);

            // Buscar o livro novamente para verificar as mudanças
            Livro livroAtualizado = livroController.buscar(livroId);

            // Exibir detalhes do livro após a atualização
            System.out.println("\nDetalhes do Livro após a Atualização:");
            exibirDetalhesLivro(livroAtualizado);
        } else {
            System.out.println("Livro não encontrado!");
        }
    }

    private static void exibirDetalhesLivro(Livro livro) {
        System.out.println("ID: " + livro.getId());
        System.out.println("Título: " + livro.getTitulo());
        System.out.println("Ano de Publicação: " + livro.getAnoPub());
        System.out.println("ISBN: " + livro.getIsbn());
        // Adicione mais propriedades conforme necessário
        System.out.println("------------------------");
    }
}

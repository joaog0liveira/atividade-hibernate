package br.com.joaogabriel.atividade;

import br.com.joaogabriel.atividade.controller.EditoraController;
import br.com.joaogabriel.atividade.controller.LivroController;
import br.com.joaogabriel.atividade.entities.Editora;
import br.com.joaogabriel.atividade.entities.Livro;


public class Main {
    public static void main(String[] args) {
        LivroController livroController = new LivroController();
        EditoraController editoraController = new EditoraController();


        Editora editora = new Editora();
        editora.setNome("Editora1");

        editoraController.create(editora);


       /* int livroId = 1;

        Livro livroExistente = livroController.buscar(livroId);

        if (livroExistente != null) {
            System.out.println("Detalhes do Livro antes da Atualização:");
            exibirDetalhesLivro(livroExistente);

            livroExistente.setTitulo("Novo Título");
            livroExistente.setAnoPub(2023);
            livroExistente.setIsbn("987654321");

            livroController.update(livroExistente);

            Livro livroAtualizado = livroController.buscar(livroId);

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
        System.out.println("------------------------");*/
    }
}

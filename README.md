# Sistema de gerenciamento de Livros

Sistema de gerenciamento de livros desenvolvida como parta de uma atividade utilizando Hibernate. 
Desenvolvido em Java 17, o projeto permite realizar operações básicas de CRUD (Create, Read, Update, Delete) para livros, 
autores e editoras, utilizando uma API simples. Também foram implementados métodos para buscar informações específicas, 
como autores por livro e livros por autor.

## Tecnologias Utilizadas

- Java 17
- Hibernate
- MySQL

## Recursos e Funcionalidades

- **Gestão de Livros:** Adicione, atualize, consulte e exclua livros, além de associar autores e editoras a cada livro.

- **Gestão de Autores:** Registre novos autores, atualize informações e busque livros relacionados a um autor.

- **Gestão de Editoras:** Adicione novas editoras, atualize informações e busque autores relacionados a uma editora.

## Configuração do Ambiente

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/joaog0liveira/gerenciamento-livros
   ```

2. **Configuração do Banco de Dados:**

- Crie um banco de dados no MySQL chamado hibernate (ou modifique a configuração no hibernate.cfg.xml para refletir seu banco de dados).

3. **Compilação e Execução:**


## Testes
Os testes podem ser realizados diretamente através do console, já que o projeto não possui uma API REST implementada. 
O método main da classe Main executa exemplos de CRUD e buscas específicas.

## Métodos Exemplo

### 1. Livro
#### 1.1 Criar Livro
Método: POST (exemplo de uso no código)
Descrição: Cria um novo livro, associando-o a um autor e uma editora.

#### 1.2 Buscar Livro
Método: GET (exemplo de uso no código)
Descrição: Busca um livro pelo ID.

#### 1.3 Atualizar Livro
Método: PUT (exemplo de uso no código)
Descrição: Atualiza os dados de um livro.

#### 1.4 Deletar Livro
Método: DELETE (exemplo de uso no código)
Descrição: Deleta um livro pelo ID.

### 2. Autor
#### 2.1 Criar Autor
Método: POST (exemplo de uso no código)
Descrição: Cria um novo autor.

#### 2.2 Buscar Autor
Método: GET (exemplo de uso no código)
Descrição: Busca um autor pelo ID.

#### 2.3 Atualizar Autor
Método: PUT (exemplo de uso no código)
Descrição: Atualiza os dados de um autor.

#### 2.4 Deletar Autor
Método: DELETE (exemplo de uso no código)
Descrição: Deleta um autor pelo ID.

### 3. Editora
#### 3.1 Criar Editora
Método: POST (exemplo de uso no código)
Descrição: Cria uma nova editora.

#### 3.2 Buscar Editora
Método: GET (exemplo de uso no código)
Descrição: Busca uma editora pelo ID.

#### 3.3 Atualizar Editora
Método: PUT (exemplo de uso no código)
Descrição: Atualiza os dados de uma editora.

#### 3.4 Deletar Editora
Método: DELETE (exemplo de uso no código)
Descrição: Deleta uma editora pelo ID.

## Contato
Se você tiver alguma dúvida ou sugestão, sinta-se à vontade para entrar em contato:

Nome: João Gabriel de Oliveira Meireles
Email: joaog.meireles@outlook.com
LinkedIn: João Gabriel

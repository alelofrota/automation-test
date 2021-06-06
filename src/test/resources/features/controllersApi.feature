# encoding: utf-8
# language: pt

  @ControllerAPI
  Funcionalidade: Validar a funcionalidade da API do controle de livros de estudantes.

    @Cadastrar
    Cenario: Cadastrar o estudante e seus livros
      Dado que eu tenha os dados para cadastrar o estudante
      |nome  | email                  | ano        | cpf         | autor | nome do livro| titulo |
      |Renato| renatograso95@gmail.com| 44         |  34503755803| Autor1| NomeLivro1   | Titulo1|
      Quando enviar a requisicao "POST" para a API
      Então validar retorno 201


      @ListAll
      Cenario: Listar todos estudantes cadastrados por ordem alfabetica, validando o único cadastro e livros não duplicados.
        Dado que eu envie a requisicao GET para a API
        Entao validar retorno 200
        E valido estudantes com unico CPF
        E valido lista alfabetica
        E valido livros sem serem duplicados

        @Change
        Cenario: Deve alterar o cadastro do estudante
          Dado que eu tenha os dados para efetuar a alteracao do estudante
          |cpf          | nome          |email                     |
          | 09705066618 | User Alterado | email.alterado@gmail.com |
          Quando enviar a requisicao "PATCH" para a API da biblioteca
          Entao validar retorno 200
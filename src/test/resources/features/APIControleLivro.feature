# language: pt

@APIControleLivro
Funcionalidade: Validar a funcionalidade da API para Cadastrar estudantes
				Validar a funcionalidade da API para listar todos os estudantes cadastrados em ordem alfabética e livros não duplicados
				Validar a funcionalidade da API para alterar cadastro do estudante

	@Cadastrar
	Cenário: cadastrar um estudante e um livro
		Dado prencher dados para cadastrar o estudante
			| name    | email                  | years | cpf         | author  | name book  | title   |
			| Mohamad | Zaker.18@hotmail.com   | 27    | 23939220899 | Autor1  | NomeLivro1 | Titulo1 |
		Quando enviar requisicao "POST" para api
		Então validar retorno 201

	@ListarAll
	Cenário: listar todos os estudantes com ordem alfabética,cadastro único e livros não duplicados
		Dado  enviar requisicao GET para api
		Então validar retorno 200
		E validar se os estudantes com cadastro unico com cpf
		E validar se a lista em ordem alfabetica
		E validar se os livros não são duplicados


	@Alterar
	Cenário: alterar cadastro do estudante
		Dado prencher dados para alterar cadastro de um estudante
			| cpf         | name          | email                     |
			| 23939220899 | novo Nome     | email.novoEmail@gmail.com |
		Quando enviar requisicao "PATCH" para api
		Então validar retorno 200
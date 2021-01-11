# language: pt

@APIController
Funcionalidade: Validar e manipular as funcionalidades da API da biblioteca que controla a lista dos livros dos estudantes 

@Cadastrar
Cenário: Deve cadastrar estudante e seus livros
	Dado que eu tenha os dados para cadastrar o estudante
	| name  | email                  | years | cpf         | author  | name book  | title   |
	| Lucas | lucas.bonani@gmail.com | 33    | 12367890123 | Autor1  | NomeLivro1 | Titulo1 |
	Quando enviar requisicao "POST" para api
  Então validar retorno 201

@ListarAll
Cenário: Deve listar todos os estudantes cadastrados em ordem alfabética, validar cadastro único e livros não duplicados
	Dado que eu envie requisicao GET para api
	Então validar retorno 200
	E validar estudantes com cadastrado unico com cpf
	E validar lista em ordem alfabetica
	E validar livros nao duplicados
	
@Alterar
Cenário: Deve alterar cadastro do estudante
	Dado que eu tenha os dados para alterar o estudante
	| cpf         | name          | email                    |
	| 09705066618 | User Alterado | email.alterado@gmail.com |
  Quando enviar requisicao "PATCH" para api
  Então validar retorno 200
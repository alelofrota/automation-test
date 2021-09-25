#language:pt

@API @StudentControllerTest
Funcionalidade: Manter dados do estudante
  Eu como usuario
  Gostaria de realizar as requisições de GET, POST e PATCH
  Para manipular os dados do estudantes através da API

  @Cadastrar
  Esquema do Cenario: Cadastrar estudante com sucesso
    Dado que eu esteja na rota de estudantes da API
    Quando cadastrar um estudante com "<nome>","<email>","<cpf>",<idade>, "<livro>","<titulo>","<autor>"
    Então a API retorna o status 201
    E deve retornar os dados cadastrados "<nome>","<email>","<cpf>",<idade>, "<livro>","<titulo>","<autor>"
    Exemplos: 
      | nome | email                | cpf         | idade | livro   | titulo           | autor       |
      | Alex | testerman@tester.com | 46364356214 |    31 | O Livro | O primeiro livro | QA Engineer |
      
@ListarTodos
  Cenário: Listar todos os estudantes sem duplicação de dados
    Dado que eu esteja na rota de estudantes da API
    Quando enviar a requisição para listar todos os estudantes
    Então a API retorna o status 200
    E devem estar em ordem alfabética

  @Atualizar
  Esquema do Cenario: Atualizar estudante com sucesso
    Dado que eu esteja na rota de estudantes da API
    Quando atualizar um estudante com "<nome>","<email>","<cpf>"
    Então a API retorna o status 200
    E deve retornar os dados atualizados "<nome>","<email>"
    Exemplos: 
      | nome          | email                   | cpf         |
      | Roque Updated | roqueupdated@tester.com | 09705066619 |
      
@CPFExistente
  Esquema do Cenario: Cadastrar estudante com CPF já existente
    Dado que eu esteja na rota de estudantes da API
    Quando cadastrar um estudante com "<nome>","<email>","<cpf>",<idade>, "<livro>","<titulo>","<autor>"
    Então a API retorna o status 400
    E a mensagem de erro "Cpf already registered!"
    Exemplos: 
      | nome  | email           | cpf         | idade | livro  | titulo           | autor       |
      | Roque | roque@gmail.com | 09705066619 |    31 | Teste1 | O primeiro livro | QA Engineer |

 @LivroDuplicado
  Esquema do Cenario: Cadastrar estudante com livro duplicado
    Dado que eu esteja na rota de estudantes da API
    Quando cadastrar um estudante com livro duplicado com "<nome>","<email>","<cpf>",<idade>, "<livro>","<titulo>","<autor>","<livro1>","<titulo1>","<autor1>"
    Então a API retorna o status 400
    E a mensagem de erro "The books are duplicated!"
    Exemplos: 
      | nome  | email           | cpf         | idade | livro   | titulo             | autor | livro1  | titulo1            | autor1 |
      | Roque | roque@gmail.com | 99999999999 |    25 | Teste99 | Testando api rest2 | Alelo | Teste99 | Testando api rest2 | Alelo  |

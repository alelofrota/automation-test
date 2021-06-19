# language: pt
Funcionalidade: Manter dados de estudantes através da API
  Como um usuario do sistema
  eu quero realizar as requisicões na api
  afim de manipular as informações do cadastro de estudantes

  @Cadastrar
   Cenario: realizar o cadastro de um estudante
     Dado o endereco da API para manter o cadastro de estudantes
     Quando realizar uma requisicao para cadastrar um novo estudante informando todos os dados
      #| nome           | email           | anos | cpf         | nome-do-livro | titulo  | autor  |
       | teste cadastro | teste@email.com | 27   | 07847204010 | nome livro1   | titulo1 | autor1 |
     Então a API ira retornar os dados de cadastro do estudante respondendo o codigo 201

  @Listar
   Cenario: listar todos os estudantes cadastrados
     Dado o endereco da API para manter o cadastro de estudantes
     Quando realizar uma requisicao para consultar a lista de estudantes cadastrados
     Então a API ira retornar todos os dados existentes respondendo o codigo 200
     E e os dados deverao ser exibidos em ordem alfabetica
     E nao devera ter estudantes com o CPF duplicado
     E não devera ter livros duplicados por estudante

  @Alterar
  Cenario: alterar o cadastro do estudante
    Dado o endereco da API para manter o cadastro de estudantes
    Quando realizar uma requisicao para alterar um cadastro de estudante informando um CPF existente e os dados que devem ser alterados
     #| cpfUpdate   | nomeUpdate   | emailUpdate           |
      | 07847204010 | teste update | testeUpdate@email.com |
    Então a API ira retornar os dados de cadastro do estudante respondendo o codigo 200

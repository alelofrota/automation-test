#language: pt

  Funcionalidade: Validar cadastro de livros e estudantes, afim de controlar a quantidade de livros que cada estudante cadastrado tem.

    @cadastar_estudante
    Cenario: Cadastrar estudante e livro
      Dado que tenha um estudante com livros
      Quando chamar o serviço de cadastro
      Entao devo receber o codigo de retorno 201

    @estudante_cadastrado
    Cenario: Realizar cadastro de um estudante já cadastrado
      Dado que tenha um estudante ja cadastrado
      Quando chamar o serviço de cadastro
      Entao devo receber o codigo de retorno 400
      E a mensagem "Cpf already registered!"

     @consultar_estudantes
    Cenario: Consultar estudantes cadastrados
      Quando chamar o serviço de consulta
      Entao devo receber o codigo de retorno 200
      E a lista de todos os estudantes cadastrados

     @alterar_cadasro_estudante
    Cenario: Realizar alteracao nos dados de um estudante cadastrado
      Dado que tenha um estudante ja cadastrado
      E tenha os dados para alteracao
      Quando chamar o serviço de alterar
      Entao devo receber o codigo de retorno 200

    @alterar_estudante_sem_cadastro
    Cenario: Realizar alteracao nos dados de um estudante não cadastrado
      Dado que tenha um estudante nao cadastrado
      E tenha os dados para alteracao
      Quando chamar o serviço de alterar
      Entao devo receber o codigo de retorno 404
      E a mensagem "Cpf not found!"

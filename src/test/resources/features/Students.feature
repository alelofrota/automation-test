Feature: estudante

  Scenario: Efetuar a criação de um estudante utilizando o metodo POST
    Given serviço de estudantes esteja operacional
    When devo criar um estudante via post passando nome e cpf "Paulo Coelho" , "87024679093"
    Then validar qualquer campo no body da consulta e status code 201

  Scenario: Efetuar o uptdate de um estudante utilizando o metodo PATH
    Given serviço de estudantes esteja operacional
    When devo atualizar um estudante via path passando nome cpf e novo email , "Paulos Coelhos", "87024679093" , "podwesley@gmail.com"
    Then validar o novo nome atualizado e status code 200 , "Paulos Coelhos"

  Scenario: Efetuar a criação de um estudante utilizando o metodo POST cujo o nome tenha mais de 20 caracteres
    Given serviço de estudantes esteja operacional
    When devo criar um estudante via post passando nome e cpf "Paulo Coelho ZZZZZZZZZZZZZZZZZZZZZZZZZZZ" , "07257755051"
    Then validar a mensagem de erro e status code 400, "Bad Request"

  Scenario: Efetuar a criação de um estudante utilizando o metodo POST sem endereço de email
    Given serviço de estudantes esteja operacional
    When devo criar um estudante via post passando nome e cpf e email vazio "Anderson Teste" , "84246866083", ""
    Then validar a mensagem de erro e status code 400, "Bad Request"

  Scenario: Efetuar uma consulta para verificar se existem estudantes duplicados no corpo da resposta
    Given serviço de estudantes esteja operacional
    When devo efetuar uma consulta no serviço que lista todos os estudantes
    Then validar se na resposta contem estudantes duplicados

# --- Serviço não implementado por parte do desenvolvedor na API.
#  Scenario: Efetuar a consulta de um estudante utilizando o metodo GET
#    Given serviço de estudantes esteja operacional
#    When devo consultar um estudante via get passando cpf , "07257755051"
#    Then validar algum atributo no corpo e status code 200


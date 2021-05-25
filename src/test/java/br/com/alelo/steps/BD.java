package br.com.alelo.steps;

public class BD {

    public String getStudent() {
        return student;
    }

    public BD setNomeCPF(String nome, String cpf) {
        return new BD(nome, cpf);
    }

    public BD setNomeCPFEmail(String nome, String cpf, String email) {
        return new BD(nome, cpf, email);
    }

    private String student;

    public BD() {

        student = "{\n" +
                "  \"books\": [\n" +
                "    {\n" +
                "      \"author\": \"Anderson Soares\",\n" +
                "      \"name\": \"Auto Ajuda\",\n" +
                "      \"title\": \"O Cavaleiro Preso na armadura\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"cpf\": \"" + "Paulo Coelho" + "\",\n" +
                "  \"email\": \"anderson@hotmail.com\",\n" +
                "  \"name\": \"" + "87024679093" + "\",\n" +
                "  \"years\": 36\n" +
                "}";

    }

    // 1 NOME - 2 - CPF
    BD(String... dados) {

        student = "{\n" +
                "  \"books\": [\n" +
                "    {\n" +
                "      \"author\": \"Anderson Soares\",\n" +
                "      \"name\": \"Auto Ajuda\",\n" +
                "      \"title\": \"O Cavaleiro Preso na armadura\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"cpf\": \"" + dados[1] + "\",\n" +
                "  \"email\": \"anderson@hotmail.com\",\n" +
                "  \"name\": \"" + dados[0] + "\",\n" +
                "  \"years\": 36\n" +
                "}";
    }

    // 1 NOME - 2 - CPF 3 - EMAIL
    BD(String nome, String cpf, String email) {

        student = "{\n" +
                "  \"books\": [\n" +
                "    {\n" +
                "      \"author\": \"Anderson Soares\",\n" +
                "      \"name\": \"Auto Ajuda\",\n" +
                "      \"title\": \"O Cavaleiro Preso na armadura\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"cpf\": \"" + cpf + "\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"name\": \"" + nome + "\",\n" +
                "  \"years\": 36\n" +
                "}";
    }

}

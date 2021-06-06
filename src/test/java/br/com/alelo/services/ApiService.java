package br.com.alelo.services;

public interface ApiService {

    public void get(String url);

    public void post(Object obj, String url);

    public void patch(String cpf, Object obj, String url);
}
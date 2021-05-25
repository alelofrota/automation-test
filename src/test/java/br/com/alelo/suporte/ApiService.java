package br.com.alelo.suporte;

public interface ApiService {

	public void get(String url);

//	public void getByCpf(String url, String cpf);

	public void post(Object obj, String url);

	public void patch(String cpf, Object obj, String url);

}

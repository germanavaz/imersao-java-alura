package stickers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
	
	public static void main(String[] args) throws Exception {
		
		// fazer uma conexão HTTP - ok
		String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
		// desafio - colocar a chave em um arquivo de configuração ou uma variavel de ambiente
		URI endereco = URI.create(url);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request =  HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();
		
		
		// extrair os dados que interessam (título, poster e classificação) - "parsear os dados"
		// poderia usar a lib jackson para parsear 
		JsonParser parser = new JsonParser();
		List<Map<String, String>> listaDeFilmes = parser.parse(body);
		
		// exibir e manipular os dados 
		for (Map<String, String> filme : listaDeFilmes) {
			System.out.println(filme.get("title"));
			System.out.println(filme.get("image"));
			System.out.println(filme.get("imDbRating"));
			System.out.println();
		}
		
	}

}

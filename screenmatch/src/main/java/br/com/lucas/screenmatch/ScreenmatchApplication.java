package br.com.lucas.screenmatch;

import br.com.lucas.screenmatch.model.DadosEpisodio;
import br.com.lucas.screenmatch.model.DadosSerie;
import br.com.lucas.screenmatch.model.DadosTemporada;
import br.com.lucas.screenmatch.service.ConsumoApi;
import br.com.lucas.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.constant.Constable;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.ibterDados("https://www.omdbapi.com/?t=Dexter&Season=1&apikey=f7a39a7e");

		System.out.println(json);
		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);
		json = consumoApi.ibterDados("https://www.omdbapi.com/?t=Dexter&Season=1&episode=2&apikey=f7a39a7e");
		DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
		System.out.println(dadosEpisodio);

		List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i<=dados.totalTemporadas(); i++) {
			json = consumoApi.ibterDados("https://www.omdbapi.com/?t=Dexter&Season=" + i + "&apikey=f7a39a7e");
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);
	}

}

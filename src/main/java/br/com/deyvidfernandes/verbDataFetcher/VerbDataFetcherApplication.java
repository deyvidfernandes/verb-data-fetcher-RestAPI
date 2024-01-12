package br.com.deyvidfernandes.verbDataFetcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class VerbDataFetcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(VerbDataFetcherApplication.class, args);
	}

}

package br.com.deyvidfernandes.verbDataFetcher.database.queries;

public record Query(String MYSQL, String POSTGRESQL, String MARIADB) implements IQuery {

}

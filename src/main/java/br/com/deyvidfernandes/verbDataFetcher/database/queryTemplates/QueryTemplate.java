package br.com.deyvidfernandes.verbDataFetcher.database.queryTemplates;

public record QueryTemplate(String MYSQL, String POSTGRESQL, String MARIADB) implements IQueryTemplate { }

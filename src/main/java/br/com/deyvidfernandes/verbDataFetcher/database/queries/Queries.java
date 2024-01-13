package br.com.deyvidfernandes.verbDataFetcher.database.queries;

import br.com.deyvidfernandes.verbDataFetcher.database.DatabaseType;

public class Queries {
    static public String getQueryDialect(DatabaseType dialect, IQuery query) {
        return switch (dialect) {
            case MYSQL -> query.MYSQL();
            case MARIADB -> query.MARIADB().equals("MYSQL") ? query.MYSQL() : query.MARIADB();
            case POSTGRESQL -> query.POSTGRESQL();
        };
    }
}

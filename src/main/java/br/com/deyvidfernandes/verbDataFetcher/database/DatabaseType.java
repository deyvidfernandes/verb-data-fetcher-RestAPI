package br.com.deyvidfernandes.verbDataFetcher.database;

public enum DatabaseType {
    MYSQL("MySQL"),
    MARIADB("MariaDB"),
    POSTGRESQL("PostgreSQL"),
    ;

    private final String value;

    DatabaseType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
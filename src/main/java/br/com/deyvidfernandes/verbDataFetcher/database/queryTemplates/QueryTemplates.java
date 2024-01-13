package br.com.deyvidfernandes.verbDataFetcher.database.queryTemplates;

import br.com.deyvidfernandes.verbDataFetcher.database.DatabaseType;

public final class QueryTemplates {

    private QueryTemplates() {}

    public static final IQueryTemplate CREATE_TABLE = new QueryTemplate(
            """
                CREATE TABLE {0} (
                	infinitiveForm VARCHAR(20) NOT NULL,
                	simplePastForm VARCHAR(20) NOT NULL,
                	participleForm VARCHAR(20) NOT NULL,
                	britishParticipleForm VARCHAR(20) NOT NULL,
                	britishSimplePastForm VARCHAR(20) NOT NULL,
                	dictionaryDefinition VARCHAR(400) NOT NULL,
                    usageIndex DECIMAL(6,6) NOT NULL,
                    audioUrl VARCHAR(30) NOT NULL,
                    phonetic VARCHAR(30) NOT NULL,
                    ID int AUTO_INCREMENT,
                    
                    constraint verb_pk
                		primary key (ID)
                );
            """,
            """
                CREATE TABLE {0} (
                    infinitiveForm VARCHAR(20) NOT NULL,
                    simplePastForm VARCHAR(20) NOT NULL,
                    participleForm VARCHAR(20) NOT NULL,
                    britishParticipleForm VARCHAR(20) NOT NULL,
                    britishSimplePastForm VARCHAR(20) NOT NULL,
                    dictionaryDefinition VARCHAR(400) NOT NULL,
                    usageIndex DECIMAL(6,6) NOT NULL,
                    audioUrl VARCHAR(30) NOT NULL,
                    phonetic VARCHAR(30) NOT NULL,
                    ID SERIAL,
                
                    constraint verb_pk
                       primary key (ID)
               );
            """,
            "MYSQL"
    );

    public static final IQueryTemplate INSERT_VERB = new QueryTemplate(
            """
                INSERT INTO {0}
                    (
                        infinitiveForm,
                        simplePastForm,
                        participleForm,
                        britishParticipleForm,
                        britishSimplePastForm,
                        dictionaryDefinition,
                        usageIndex,
                        audioUrl,
                        phonetic
                    )
                VALUES
                    (
                        "{1}",
                        "{2}",
                        "{3}",
                        "{4}",
                        "{5}",
                        "{6}",
                        {7},
                        "{8}",
                        "{9}"
                    )
            """,
            """
                INSERT INTO {0}
                    (
                        infinitiveForm,
                        simplePastForm,
                        participleForm,
                        britishParticipleForm,
                        britishSimplePastForm,
                        dictionaryDefinition,
                        usageIndex,
                        audioUrl,
                        phonetic
                    )
                VALUES
                    (
                        ''{1}'',
                        ''{2}'',
                        ''{3}'',
                        ''{4}'',
                        ''{5}'',
                        ''{6}'',
                        {7},
                        ''{8}'',
                        ''{9}''
                    )
            """,
            "MYSQL"
    );

    static public String getQueryDialectTemplate(DatabaseType dialect, IQueryTemplate query) {
        return switch (dialect) {
            case MYSQL -> query.MYSQL();
            case MARIADB -> query.MARIADB().equals("MYSQL") ? query.MYSQL() : query.MARIADB();
            case POSTGRESQL -> query.POSTGRESQL();
        };
    }

}
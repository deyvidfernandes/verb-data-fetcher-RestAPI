package br.com.deyvidfernandes.verbDataFetcher.database.queryTemplates;

import br.com.deyvidfernandes.verbDataFetcher.database.DatabaseType;

public final class QueryTemplates {

    private QueryTemplates() {}

    public static final IQueryTemplate CREATE_TABLE = new QueryTemplate(
            """
                        CREATE TABLE {0} (
                            definitions TEXT,
                            phonetics VARCHAR(100) NOT NULL,
                            usageIndex DECIMAL(12,12) NOT NULL,
                            infinitive VARCHAR(50) NOT NULL,
                            infinitive_audio VARCHAR(100),
                                                                  
                            simple_past VARCHAR(50) NOT NULL,
                            simple_past_audio VARCHAR(100),
                            simple_past_uk VARCHAR(50),
                            simple_past_uk_audio VARCHAR(100),
                         
                            participle VARCHAR(50) NOT NULL,
                            participle_audio VARCHAR(100),
                            participle_uk VARCHAR(50),
                            participle_uk_audio VARCHAR(100),
                            id int AUTO_INCREMENT,
                            
                            constraint verb_pk
                        	    primary key (id)
                        );
                    """,
            """
                        CREATE TABLE {0} (
                            definitions TEXT,
                            phonetics VARCHAR(100) NOT NULL,
                            usageIndex DECIMAL(12,12) NOT NULL,
                            infinitive VARCHAR(50) NOT NULL,
                            infinitive_audio VARCHAR(100),
                                                                          
                            simple_past VARCHAR(50) NOT NULL,
                            simple_past_audio VARCHAR(100),
                            simple_past_uk VARCHAR(50),
                            simple_past_uk_audio VARCHAR(100),
                                 
                            participle VARCHAR(50) NOT NULL,
                            participle_audio VARCHAR(100),
                            participle_uk VARCHAR(50),
                            participle_uk_audio VARCHAR(100),
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
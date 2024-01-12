package br.com.deyvidfernandes.verbDataFetcher.verb;

import br.com.deyvidfernandes.verbDataFetcher.database.DatabaseConnector;
import br.com.deyvidfernandes.verbDataFetcher.database.queries.Queries;
import br.com.deyvidfernandes.verbDataFetcher.utils.Numbers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.text.MessageFormat;

@RestController
@RequestMapping("/verbData")
public class VerbDataController {

    @PostMapping("/")
    public ResponseEntity persistVerb(@RequestBody VerbModel[] verbData) throws FailPersistingVerbsException {
        try {
            DatabaseConnector.openConnection();
            int index = 0;

            var dbType = DatabaseConnector.getDatabaseType();
            String insertVerbQueryTemplate = Queries.getQuery(dbType, Queries.INSERT_VERB);
            for (VerbModel verb : verbData) {
                String query = MessageFormat.format(
                        insertVerbQueryTemplate,
                        DatabaseConnector.getSchema(),
                        verb.infinitiveForm,
                        verb.simplePastForm,
                        verb.participleForm,
                        verb.britishSimplePastForm,
                        verb.britishParticipleForm,
                        verb.definition,
                        Numbers.correctlyDisplayDouble(verb.usageIndex),
                        verb.audioUrl,
                        verb.phonetic,
                        (index++)
                );
                System.out.println(query);

                DatabaseConnector.update(query);
            }
            DatabaseConnector.closeConnection();
        } catch (SQLException e) {
            throw new FailPersistingVerbsException(e.getMessage());
        }

        return  ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}

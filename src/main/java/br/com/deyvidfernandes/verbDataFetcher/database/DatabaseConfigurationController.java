package br.com.deyvidfernandes.verbDataFetcher.database;

import br.com.deyvidfernandes.verbDataFetcher.database.queryTemplates.QueryTemplates;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Arrays;


import static br.com.deyvidfernandes.verbDataFetcher.database.queryTemplates.QueryTemplates.getQueryDialectTemplate;

@RestController
@RequestMapping("/database")
public class DatabaseConfigurationController {

    @PostMapping("/setup")
    public ResponseEntity<Object> setup(@RequestBody DatabaseConfigurationModel conf) throws SQLException {

        DatabaseConnector.setup(conf.type, conf.table, conf.url, conf.username, conf.password, null);

        DatabaseConnector.openConnection();
        if (!DatabaseConnector.tableExists(conf.table)) {
            var dbType = DatabaseConnector.getDatabaseType();
            String createTableQueryTemplate = getQueryDialectTemplate(dbType, QueryTemplates.CREATE_TABLE);
            DatabaseConnector.update(MessageFormat.format(createTableQueryTemplate, conf.table));
        }
        DatabaseConnector.closeConnection();

        return  ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/available-types")
    public ResponseEntity<Object> getAvailableTypes() {
        var availableDBTypes = Arrays.stream(DatabaseType.values()).map(DatabaseType::getValue);
        return  ResponseEntity.status(HttpStatus.OK).body(availableDBTypes);
    }
}

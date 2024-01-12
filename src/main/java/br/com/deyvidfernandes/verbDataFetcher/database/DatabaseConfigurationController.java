package br.com.deyvidfernandes.verbDataFetcher.database;

import br.com.deyvidfernandes.verbDataFetcher.database.queries.Queries;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.text.MessageFormat;

@RestController
@RequestMapping("/database")
public class DatabaseConfigurationController {

    @PostMapping("/")
    public ResponseEntity setup(@RequestBody DatabaseConfigurationModel conf) throws SQLException {

        DatabaseConnector.setup(conf.type, conf.table, conf.url, conf.username, conf.password, null);

        DatabaseConnector.openConnection();
        if (!DatabaseConnector.tableExists(conf.table)) {
            var dbType = DatabaseConnector.getDatabaseType();
            String createTableQueryTemplate = Queries.getQuery(dbType, Queries.CREATE_TABLE);
            DatabaseConnector.update(MessageFormat.format(createTableQueryTemplate, conf.table));
        }
        DatabaseConnector.closeConnection();

        return  ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}

package br.com.deyvidfernandes.verbDataFetcher.database;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/database")
public class DatabaseConfigurationController {

    @PostMapping("/")
    public ResponseEntity create(@RequestBody DatabaseConfigurationModel conf) throws Exception {
        var JdbcProtocol = switch (conf.type) {
            case MYSQL -> "jdbc:mysql://";
            case MARIADB -> "jdbc:mariadb://";
            case POSTGRESQL -> "jdbc:postgresql://";
        };
        var JdbcUrl = JdbcProtocol + conf.url;

        RuntimeDBConnector.setup(JdbcUrl, conf.username, conf.password);
        return  ResponseEntity.status(HttpStatus.CREATED).body("Sucesso!");
    }
}

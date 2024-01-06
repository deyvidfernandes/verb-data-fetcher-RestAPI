package br.com.deyvidfernandes.verbDataFetcher;

import br.com.deyvidfernandes.verbDataFetcher.database.RuntimeDBConnector;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
@RequestMapping("/get")
public class GetController {
    @GetMapping("/")
    public ResponseEntity create() throws Exception {
        try {
            RuntimeDBConnector.openConnection();
            ResultSet rs = RuntimeDBConnector.query("select * from apartamento");
            rs.next();
            System.out.println(rs.getObject(2).toString());
            System.out.println(rs.getObject(3).toString());
            System.out.println(rs.getObject(4).toString());
            System.out.println(rs.getObject(5).toString());
            RuntimeDBConnector.closeConnection();
            return ResponseEntity.status(HttpStatus.CREATED).body(rs.toString());
        } catch (SQLException e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.CREATED).body("deu ruim");
        }
    }
}

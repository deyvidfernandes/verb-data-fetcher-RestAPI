package br.com.deyvidfernandes.verbDataFetcher.database;

import org.junit.jupiter.api.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
 @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 public class RuntimeDBConnectorIntegrationTest {
        @BeforeAll
    static void setUp() {
        RuntimeDBConnector.setup(
                "jdbc:mysql://localhost:3306/test",
                "testUser",
                "1234"
        );
        try {
            RuntimeDBConnector.openConnection();
        } catch (SQLException e) {
            fail(e.toString());
        }
    }
    @Test
    @Order(1)
    @DisplayName("Connect to database and execute a query")
    void query() {
        String result = "";
        try {
            ResultSet rs = RuntimeDBConnector.query("select * from testTable");
            rs.next();
            result += rs.getString(1);
            result += rs.getString(2);
            result += rs.getString(3);
            result += rs.getInt(4);

            assertEquals(result, "abc1");
        } catch (SQLException e) {
            fail(e.toString());
        }
    }
    @Test
    @Order(2)
    @DisplayName("Cannot close already closed connection")
    void closeConnection() {
        try {
            RuntimeDBConnector.closeConnection();
        } catch (SQLException e) {
            fail(e.toString());
        }
        assertThrows(UnsupportedOperationException.class, () -> {
            RuntimeDBConnector.closeConnection();
        });
    }
}

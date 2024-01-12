package br.com.deyvidfernandes.verbDataFetcher.database;

import org.junit.jupiter.api.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
 @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 public class DatabaseConnectorIntegrationTests {
        @BeforeAll
    static void setUp() {
        try {
            DatabaseConnector.setup(
                    DatabaseType.MYSQL,
                    "test",
                    "localhost:3306/test",
                    "testUser",
                    "1234",
                    null
            );

            DatabaseConnector.openConnection();
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    @Order(1)
    @DisplayName("Connect to database and execute a query")
    void query() {
        String result = "";
        try {
            ResultSet rs = DatabaseConnector.query("select * from testTable");
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
    @DisplayName("Check if table do not exists")
    void tableDoNotExists() {
        try {
            assertFalse(DatabaseConnector.tableExists("foobar"));
        } catch (SQLException e) {
            fail(e.toString());
        }
    }
    @Test
    @Order(3)
    @DisplayName("Check if table exists")
    void tableExists() {
        try {
            assertTrue(DatabaseConnector.tableExists("testTable"));
        } catch (SQLException e) {
            fail(e.toString());
        }
    }

    @Test
    @Order(4)
    @DisplayName("Cannot close already closed connection")
    void closeConnection() {
        try {
            DatabaseConnector.closeConnection();
        } catch (SQLException e) {
            fail(e.toString());
        }
        assertThrows(UnsupportedOperationException.class, () -> {
            DatabaseConnector.closeConnection();
        });
    }
}

package br.com.deyvidfernandes.verbDataFetcher.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.Map;

public class DatabaseConnector {
    static private HikariDataSource dataSource;
    static private Connection currentConnection;
    static private String schema;
    static private DatabaseType databaseType;

    static public void setup(DatabaseType databaseType, String schema, String url, String username, String password, Map<String, Object> properties) {

        String jdbcProtocol = switch (databaseType) {
            case MYSQL -> "jdbc:mysql://";
            case MARIADB -> "jdbc:mariadb://";
            case POSTGRESQL -> "jdbc:postgresql://";
        };

        var JdbcUrl = jdbcProtocol + url;

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(JdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        if (properties != null) {
            properties.forEach(config::addDataSourceProperty);
        }


        dataSource = new HikariDataSource(config);


        DatabaseConnector.schema = schema;
        DatabaseConnector.databaseType = databaseType;
    }

    static public void openConnection() throws SQLException {
        currentConnection = dataSource.getConnection();
    }

    static public void closeConnection() throws SQLException {
        if (currentConnection.isClosed()) {
            throw new UnsupportedOperationException("The SQL connection was already closed");
        }
        currentConnection.close();
    }

    static public ResultSet query(String sql) throws SQLException {
        PreparedStatement pst = currentConnection.prepareStatement(sql);
        return pst.executeQuery();
    }

    static public int update(String sql) throws SQLException {
        PreparedStatement pst = currentConnection.prepareStatement(sql);
        return pst.executeUpdate();
    }

    static public boolean tableExists(String name) throws SQLException {
        DatabaseMetaData dmd = currentConnection.getMetaData();
        ResultSet table = dmd.getTables(null, null, name, null);
        return table.next();
    }

    public static String getSchema() {
        return schema;
    }
    public static DatabaseType getDatabaseType() {
        return databaseType;
    }

}

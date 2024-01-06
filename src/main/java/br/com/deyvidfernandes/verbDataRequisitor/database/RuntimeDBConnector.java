package br.com.deyvidfernandes.verbDataRequisitor.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RuntimeDBConnector {
    static private HikariDataSource dataSource;
    static private Connection currentConnection;

    static public void setup(String JdbcUrl, String username, String password, Map<String, Object> properties) {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(JdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        properties.forEach(config::addDataSourceProperty);
        dataSource = new HikariDataSource(config);
    }

    static public void setup(String JdbcUrl, String username, String password) {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(JdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);
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

}

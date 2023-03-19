package com.lunas.todoapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    private String sgbd;
    private String domain;
    private String port;
    private String database;
    private String user;
    private String password;
    private String driver;
    private String url;

    public ConnectionManager(String sgbd, String domain, String port, String database, String user, String password) {
        this.setSgbd(sgbd);
        this.setDomain(domain);
        this.setPort(port);
        this.setDatabase(database);
        this.setUser(user);
        this.setPassword(password);
        
        this.setDriver();
        this.setUrl();
    }

    public Connection getConnection() {    
        try {
            return DriverManager.getConnection(this.getUrl(), this.getUser(), this.getPassword());
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + ex.getMessage());
        }
    }

    public void closeConnection(Connection connection) {    
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados: " + ex.getMessage());
        }
    }
    
    public String getSgbd() {
        return sgbd;
    }

    public void setSgbd(String sgbd) {
        this.sgbd = sgbd;
        this.setDriver();
        this.setUrl();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
        this.setUrl();
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
        this.setUrl();
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
        this.setUrl();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    private void setDriver() {
        this.driver = "com." + this.getSgbd() + ".jbdc.Driver";
    }

    public String getUrl() {
        return url;
    }

    private void setUrl() {
        this.url = "jdbc:" + this.getSgbd() + "://" + this.getDomain() + ":" + this.getPort() + "/" + this.getDatabase();
    }
}

package com.lunas.todoapp.controller;

import java.util.List;
import com.lunas.todoapp.model.Task;
import com.lunas.todoapp.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskController {
    
    private final String sbgd = "mysql";
    private final String domain = "localhost";
    private final String port = "3306";
    private final String database = "todoapp";
    private final String user = "root";
    private final String password = "";
    
    private final ConnectionManager connectionManager = new ConnectionManager(sbgd, domain, port, database, user, password);
    private Connection connection;
    private PreparedStatement statement;

    public void create(Task task) throws SQLException {
        String query = "INSERT INTO tasks("
                + "project_id,"
                + "name,"
                + "description,"
                + "completed,"
                + "notes,"
                + "deadline,"
                + "created_at,"
                + "updated_at)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, task.getProjectId());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, task.getDeadline());
            statement.setDate(7, task.getCreatedAt());
            statement.setDate(8, task.getUpdatedAt());
            statement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao criar tarefa: " + e.getMessage());
        } finally {
            connectionManager.closeConnection(connection);
        }
    }
    
    public void read(Task task) throws SQLException {
        String query = "SELECT FROM tasks WHERE id = ?";

        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, task.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao ler a tarefa: " + e.getMessage());
        } finally {
            connectionManager.closeConnection(connection);
        }
    }
    
    public void update(Task task) throws SQLException {
        
    }
    
    public void delete(Task task) throws SQLException {
        String query = "DELETE FROM tasks WHERE id = ?";

        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, task.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar a tarefa: " + e.getMessage());
        } finally {
            connectionManager.closeConnection(connection);
        }
    }
    
    public List<Task> getAll(Task task) throws SQLException {
        String query = "SELECT ALL FROM tasks WHERE project_id = ?";
        
        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, task.getProjectId());
            statement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao exibir tarefas: " + e.getMessage());
        } finally {
            connectionManager.closeConnection(connection);
        }
        return null;
    }
}

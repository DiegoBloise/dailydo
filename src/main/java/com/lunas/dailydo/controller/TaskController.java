package com.lunas.dailydo.controller;

import java.util.ArrayList;
import java.util.List;
import com.lunas.dailydo.model.Project;
import com.lunas.dailydo.model.Task;
import com.lunas.dailydo.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskController {

    private final String sbgd = "mysql";
    private final String domain = "localhost";
    private final String port = "3306";
    private final String database = "dailydo";
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
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";

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
            throw new SQLException("Erro ao criar a tarefa: " + e.getMessage());
        } finally {
            connectionManager.closeConnection(connection, statement);
        }
    }

    public Task readById(int taskId) throws SQLException {
        String query = "SELECT * FROM tasks WHERE id = ?;";

        Task task;
        ResultSet resultSet;

        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(query);

            statement.setInt(1, taskId);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                task = new Task(
                    resultSet.getInt("id"),
                    resultSet.getInt("project_id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getBoolean("completed"),
                    resultSet.getString("notes"),
                    resultSet.getDate("deadline"),
                    resultSet.getDate("created_at"),
                    resultSet.getDate("updated_at"));
            } else {
                throw new SQLException("Nenhuma tarefa encontrada com o ID " + taskId);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao carregar a tarefa: " + e.getMessage());
        } finally {
            connectionManager.closeConnection(connection, statement);
        }
        return task;
    }

    public void update(Task task) throws SQLException {
        String query = "UPDATE tasks SET "
                + "project_id = ?, "
                + "name = ?, "
                + "description = ?, "
                + "completed = ?, "
                + "notes = ?, "
                + "deadline = ?, "
                + "created_at = ?, "
                + "updated_at = ? "
                + "WHERE id = ?;";

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
            statement.setInt(9, task.getId());

            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar tarefa: " + e.getMessage());
        } finally {
            connectionManager.closeConnection(connection);
        }
    }

    public void deleteById(int taskId) throws SQLException {
        String query = "DELETE FROM tasks WHERE id = ?;";

        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(query);

            statement.setInt(1, taskId);

            statement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar a tarefa: " + e.getMessage());
        } finally {
            connectionManager.closeConnection(connection, statement);
        }
    }

    public List<Task> getAll(Project project) throws SQLException {
        String query = "SELECT * FROM tasks WHERE project_id = ?;";

        List<Task> tasks = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(query);

            statement.setInt(1, project.getId());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Task task = new Task(
                    resultSet.getInt("id"),
                    resultSet.getInt("project_id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getBoolean("completed"),
                    resultSet.getString("notes"),
                    resultSet.getDate("deadline"),
                    resultSet.getDate("created_at"),
                    resultSet.getDate("updated_at"));

                tasks.add(task);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao exibir tarefas: " + e.getMessage());
        } finally {
            connectionManager.closeConnection(connection, statement);

            if (resultSet != null) {
                resultSet.close();
            }
        }
        return tasks;
    }
}

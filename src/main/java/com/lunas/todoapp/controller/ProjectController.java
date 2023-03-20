package com.lunas.todoapp.controller;

import com.lunas.todoapp.model.Project;
import com.lunas.todoapp.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectController {

    private final String sbgd = "mysql";
    private final String domain = "localhost";
    private final String port = "3306";
    private final String database = "todoapp";
    private final String user = "root";
    private final String password = "";

    private final ConnectionManager connectionManager = new ConnectionManager(sbgd, domain, port, database, user, password);
    private Connection connection;
    private PreparedStatement statement;

    public void create(Project project) {
        String query = "INSERT INTO projects VALUES ("
            + "default, "
            + "?, "
            + "?, "
            + "?, "
            + "?);";

        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, project.getCreatedAt());
            statement.setDate(4, project.getUpdatedAt());
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar o projeto: " + e.getMessage());
        }
    }

    public List<Project> getAll() throws SQLException {
        String query = "SELECT * from projects";

        List<Project> projects = new ArrayList<Project>();
        ResultSet resultSet = null;

        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Project project = new Project(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getDate("created_at"),
                    resultSet.getDate("updated_at"));

                projects.add(project);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao exibir projetos: " + e);
        } finally {
            connectionManager.closeConnection(connection, statement);
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return projects;
    }

    public void update(Project project) {
        String query = "UPDATE projects SET"
            + "name = ?, "
            + "description = ?, "
            + "created_at = ?, "
            + "updated_at = ? "
            + "WHERE id = ?";

        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, project.getCreatedAt());
            statement.setDate(4, project.getUpdatedAt());
            statement.setInt(5, project.getId());
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o projeto: " + e);
        }
    }

    public void delete(Project project) {
        String query = "DELETE FROM projects WHERE id = ?";

        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, project.getId());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover o projeto: " + e);
        }
    }
}

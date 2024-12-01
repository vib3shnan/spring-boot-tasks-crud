package com.vibi.vibiapp.dao;

import com.vibi.vibiapp.model.Task;
import com.vibi.vibiapp.util.DBUtil;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class TaskDao {

    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        ResultSet resultSet = null;
        Connection conn = null;
        try {
            conn = DBUtil.openConnection();
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM task");

            while (resultSet.next()) {
                Task task = new Task();
                task.setTaskId(resultSet.getInt("taskId"));
                task.setTaskName(resultSet.getString("taskName"));
                task.setTaskDescription(resultSet.getString("taskDescription"));
                task.setTaskStatus(resultSet.getString("taskStatus"));
                tasks.add(task);
            }
            return tasks;
        } catch (Exception e) {
            System.err.println("Error fetching tasks: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try{
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing conn: " + e.getMessage());
            }
        }
        return null;
    }

    public Task getTaskById(int Id){
        ResultSet resultSet = null;
        Task task = new Task();
        Connection conn = null;
        try {
            conn = DBUtil.openConnection();
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery("select * from task where taskId = " + Id);

            while (resultSet.next()) {
                task.setTaskId(resultSet.getInt("taskId"));
                task.setTaskName(resultSet.getString("taskName"));
                task.setTaskDescription(resultSet.getString("taskDescription"));
                task.setTaskStatus(resultSet.getString("taskStatus"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing conn: " + e.getMessage());
            }
        }
        return task;
    }

    public String addTask(Task task) {
        String query = "Insert into task (taskId, taskName, taskDescription, taskStatus) values (?,?,?,?)";
        Connection conn = null;
        try {
            conn = DBUtil.openConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, task.getTaskId());
            preparedStatement.setString(2, task.getTaskName());
            preparedStatement.setString(3, task.getTaskDescription());
            preparedStatement.setString(4, task.getTaskStatus());
            preparedStatement.execute();
            return "row added successfully";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error while addition " + e.getMessage());
        } finally {
            try{
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing conn: " + e.getMessage());
            }
        }
        return null;
    }

    public String updateTask(int Id, Task task){

        Connection conn = null;
        String query = "update task set taskName = ?, taskDescription = ?, taskStatus = ? where taskId = ?";

        try {
            conn = DBUtil.openConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, task.getTaskName());
            preparedStatement.setString(2, task.getTaskDescription());
            preparedStatement.setString(3, task.getTaskStatus());
            preparedStatement.setInt(4, Id);
            preparedStatement.execute();
            return "update performed successfully";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error while updation " + e.getMessage());
        }
        finally {
            try{
                conn.close();
            }
            catch(SQLException e){
                System.err.println("Error closing conn: " + e.getMessage());
            }
        }
        return null;
    }

    public String deleteTask(int Id){
        Connection conn = null;
        String query = "delete from task where taskId = ?";
        try {
            conn = DBUtil.openConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, Id);
            preparedStatement.execute();
            return "row deleted successfully";

        } catch (SQLException e) {
            System.out.println("error while deleting " + e.getMessage());
        }
        finally {
            try{
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing conn: " + e.getMessage());
            }
        }
        return null;
    }
}

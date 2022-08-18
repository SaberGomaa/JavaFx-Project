/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.course;
import models.register;

/**
 *
 * @author hP
 */
public class CoursesController implements Initializable {

    private static com.mysql.jdbc.Connection con;
    private static String url = " ";
    private static String dbName = "";
    //Table
    @FXML
    private TableView<course> tableviewcousrse;

    //Columns
    @FXML
    private TableColumn<course, Integer> id;

    @FXML
    private TableColumn<course, String> name;
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    models.course course = null;
    ObservableList<models.course> couresList = FXCollections.observableArrayList();

    private void loadData() {
        setConnection();
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        getdata();
    }

    private void getdata() {
        try {
            connection = database.dbconnection.getConnect();
            couresList.clear();
            query = "select *from courses";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                couresList.add(new course(Integer.parseInt(resultSet.getString("courseID")),
                        resultSet.getString("courseName")));
                tableviewcousrse.setItems(couresList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoursesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    //get connction with database
    private void setConnection() {
        try {
            setURL();
            con = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, "root", "");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //set  database link
    private void setURL() {
        url = "jdbc:mysql://localhost:3306/courserigistartiosystem"
                + "?useUnicode=true&characterEncoding=UTF-8";
    }

    //excute database query
    private boolean runNonQuery(String sqlStatement) {
        setConnection();
        Statement stmt;
        try {
            stmt = con.createStatement();
            stmt.execute(sqlStatement);
            con.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @FXML
    private void Exit(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

}

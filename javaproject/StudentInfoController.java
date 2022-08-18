/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.studentInfo;

/**
 *
 * @author hP
 */
public class StudentInfoController implements Initializable {

    //Table
    @FXML
    private TableView<models.studentInfo> tableview;

    //Columns
    @FXML
    private TableColumn<models.studentInfo, String> name;

    @FXML
    private TableColumn<models.studentInfo, String> username;

    @FXML
    private TableColumn<models.studentInfo, String> phone;
    @FXML
    private TableColumn<models.studentInfo, Double> Gpa;
    @FXML
    private TableColumn<models.studentInfo, Integer> hours;
    @FXML
    private TableColumn<models.studentInfo, Integer> level;

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    models.studentInfo studentinfo = null;
    ObservableList<models.studentInfo> stulist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        laodData();
        getRefresh();
    }

    @FXML
    private void Exit(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    @FXML
    public void getRefresh() {
        try {
            connection = database.dbconnection.getConnect();
            stulist.clear();
            query = "select stdName,stdUsername,stdPhone,stdGpa"
                    + ",stdLevel,stdNumofHourse from registartion";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                System.out.println(resultSet.getString("stdNumofHourse"));
                stulist.add(new studentInfo(resultSet.getString("stdName"),
                        resultSet.getString("stdUsername"),
                        resultSet.getString("stdPhone"),
                        Double.parseDouble(resultSet.getString("stdGpa")),
                        Integer.parseInt(resultSet.getString("stdLevel")),
                        Integer.parseInt(resultSet.getString("stdNumofHourse"))));
                tableview.setItems(stulist);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void laodData() {
//        connection = database.dbconnection.getConnect();
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        Gpa.setCellValueFactory(new PropertyValueFactory<>("Gpa"));
        level.setCellValueFactory(new PropertyValueFactory<>("level"));
        hours.setCellValueFactory(new PropertyValueFactory<>("numberOfHours"));

    }

}

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
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.course;
import models.register;

/**
 * FXML Controller class
 *
 * @author Ali Tahoon
 */
public class courseTableviewControllerSearch implements Initializable {

    @FXML
    TextField txtKeySearch;
    @FXML
    private TableView<models.course> tblviewstudent;
    @FXML
    private TableColumn<models.course, Integer> id;
    @FXML
    private TableColumn<models.course, String> name;
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    models.course course = null;
    ObservableList<models.course> couresList = FXCollections.observableArrayList();

    @FXML
    private void getSearch(javafx.scene.input.MouseEvent event) {
        query = "select *from registerd where courseID = " + txtKeySearch.getText();
        System.out.println(query);
        getRecord(query);
//        loadData();
//        getRefresh();
    }

    @FXML
    public void getRefresh() {
        try {
            couresList.clear();
            query = "select *from registerd";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                couresList.add(new course(Integer.parseInt(resultSet.getString("courseID")),
                        resultSet.getString("courseName")));
                tblviewstudent.setItems(couresList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(courseTableviewControllerSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        getRefresh();
    }

    public void getRecord(String stat) {
        try {
            couresList.clear();
            preparedStatement = connection.prepareStatement(stat);
            resultSet = preparedStatement.executeQuery();
            System.out.println("qkwjdoiqjwod");
            while (resultSet.next()) {
                couresList.add(new course(Integer.parseInt(resultSet.getString("courseID")),
                resultSet.getString("courseName")));
                tblviewstudent.setItems(couresList);
            }

        } catch (SQLException ex) {
            Logger.getLogger(courseTableviewControllerSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Exit(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    private void loadData() {
        connection = database.dbconnection.getConnect();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

    }

}

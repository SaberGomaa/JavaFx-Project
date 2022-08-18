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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.course;

/**
 * FXML Controller class
 *
 * @author Ali Tahoon
 */
public class courseTableviewControlleradd implements Initializable {

    @FXML
    private TableView<models.course> tblviewcourse;
    @FXML
    private TableColumn<models.course, Integer> id;
    @FXML
    private TableColumn<models.course, String> name;
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    models.course course = null;
    ObservableList<models.course> courList = FXCollections.observableArrayList();

    @FXML
    private void getAddView(javafx.scene.input.MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("courseinput.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(courseTableviewControlleradd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Exit(javafx.scene.input.MouseEvent event) {
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();

    }

//    @FXML
//    private void getDeleteView(javafx.scene.input.MouseEvent event) {
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("courseTabelview_delete.fxml"));
//            Stage stage = new Stage();
//            Scene scene = new Scene(root);
//
//            stage.setScene(scene);
//            stage.initStyle(StageStyle.UNDECORATED);
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(courseTableviewControlleradd.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @FXML
    public void getRefresh() {
        try {
            loadData();
            courList.clear();
            query = "select *from registerd";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courList.add(new course(Integer.parseInt(resultSet.getString("courseID")),
                        resultSet.getString("courseName")));
                tblviewcourse.setItems(courList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(courseTableviewControlleradd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        getRefresh();
    }

    private void loadData() {
        connection = database.dbconnection.getConnect();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

}

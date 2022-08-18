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
public class courseTableviewControllerdelete implements Initializable {

    @FXML
    private TableView<models.course> tblviews;
    @FXML
    private TableColumn<models.course, Integer> id;
    @FXML
    private TableColumn<models.course, String> name;
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    models.course courses = null;
    ObservableList<models.course> stulist = FXCollections.observableArrayList();

  

    @FXML
    private void getdelete(javafx.scene.input.MouseEvent event) {
        try {
            courses = tblviews.getSelectionModel().getSelectedItem();
            query = "delete from  registerd where courseID = " + courses.getId();
            connection = database.dbconnection.getConnect();
            preparedStatement = connection.prepareCall(query);
            preparedStatement.execute();
            getRefresh();
        } catch (SQLException ex) {
            Logger.getLogger(courseTableviewControllerdelete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Exit(javafx.scene.input.MouseEvent event) {
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();

    }

    @FXML
    public void getRefresh() {
        try {
            loadData();
            stulist.clear();
            query = "select *from registerd";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                stulist.add(new course(Integer.parseInt(resultSet.getString("courseID")),
                        resultSet.getString("coursename")));
                tblviews.setItems(stulist);
            }
        } catch (SQLException ex) {
            Logger.getLogger(courseTableviewControllerdelete.class.getName()).log(Level.SEVERE, null, ex);
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

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.course;

/**
 *
 * @author NewTech
 */
public class HomeController implements Initializable {

    @FXML
    Button btnstudent;
    @FXML
    Button btncourses;
    @FXML
    Button btnStudentInfo;
    @FXML
    Button btnCoursesLeft;
    @FXML
    Button btnStudentLeft; 
    @FXML
    private VBox hbox;
    @FXML
    private Button btnHome;
    @FXML
    AnchorPane mainPage;
    @FXML
    AnchorPane ParentmainPage;
    @FXML
    Label username;
    @FXML
    private void handlebtnStudentInfo(javafx.scene.input.MouseEvent event) throws IOException {
        try {
            AnchorPane main=FXMLLoader.load(getClass().getResource("StudentInfo.fxml"));
            mainPage.getChildren().setAll(main);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handleCoursesButton(javafx.scene.input.MouseEvent event) throws IOException {
        try {
            AnchorPane main=FXMLLoader.load(getClass().getResource("Courses.fxml"));
            mainPage.getChildren().setAll(main);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    private void handleHomeButton(javafx.scene.input.MouseEvent event) throws IOException {
        try {
            AnchorPane main=FXMLLoader.load(getClass().getResource("Home.fxml"));
            ParentmainPage.getChildren().setAll(main);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    private void handleRegisterButton(javafx.scene.input.MouseEvent event) throws IOException {
        try {
            AnchorPane main=FXMLLoader.load(getClass().getResource("register_course.fxml"));
            mainPage.getChildren().setAll(main);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     @FXML
    private void Exit(javafx.scene.input.MouseEvent event) {
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();

    }

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    static int num_hours;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       username.setText(LoginPage.userName); 
       
//       try {
//            connection = database.dbconnection.getConnect();
//            query = "select stdNumofHourse from registartion where stdUsername = " + LoginPage.userName;
//            preparedStatement = connection.prepareStatement(query);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//              num_hours= Integer.parseInt(resultSet.getString("stdNumofHourse"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        System.out.println(num_hours  + "nnnnnnnnnnnnnnnnnnnnn");
        
    }

}

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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.register;

/**
 * FXML Controller class
 *
 * @author Ali Tahoon
 */
public class Registercontroller implements Initializable {

    private static com.mysql.jdbc.Connection con;
    private static String url = " ";
    private static String dbName = "";

    @FXML
    TextField txtName;
    @FXML
    TextField txtUsername;
    @FXML
    PasswordField txtPassword;
    @FXML
    TextField txtGpa;
    @FXML
    TextField txtNumberofhours;
    @FXML
    TextField txtPhone;
    @FXML
    TextField txtLevel;
    @FXML
    Button btnLogin;
    @FXML
    Button btnRegister;
    @FXML
    ImageView btnExit;
    @FXML
    AnchorPane mainPane;
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;

    @FXML
    private void getExit(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void getRegister(javafx.scene.input.MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void handleBtnLogin(javafx.scene.input.MouseEvent event) {
        try {
            AnchorPane main = FXMLLoader.load(getClass().getResource("Loginpa.fxml"));
            mainPane.getChildren().setAll(main);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Handle available Courses
    private void insertRegisterd() {
        double gpa = Double.parseDouble(txtGpa.getText());
        int level = Integer.parseInt(txtLevel.getText());
        int hours = Integer.parseInt(txtNumberofhours.getText());
        System.out.println("javaproject.Registercontroller.insertRegisterd()");
        if (gpa >= 2 && level == 2 && hours >= 36) {
            query = "INSERT INTO `courses` VALUES ('M',10)";
            runNonQuery(query);
//            query = "INSERT INTO `courses` VALUES ('M',10)";
//            runNonQuery(query);

//            query = "INSERT INTO `registerd`(`coursename`, `courseID`) VALUES ('U','U')";
//            query = "INSERT INTO `registerd`(`coursename`, `courseID`) VALUES ('I','I')";
//            query = "INSERT INTO `registerd`(`coursename`, `courseID`) VALUES ('T','T')";
//            query = "INSERT INTO `registerd`(`coursename`, `courseID`) VALUES ('Q','Q')";
//            query = "INSERT INTO `registerd`(`coursename`, `courseID`) VALUES ('A','A')";
//            System.out.println(query);
            //setConnection();
//        } else if (gpa > 2 && level == 3 && hourse > 70) {
//            query = "INSERT INTO `registerd`(`coursename`, `courseID`) VALUES ('S','S')";
//            query = "INSERT INTO `registerd`(`coursename`, `courseID`) VALUES ('R','R')";
//            query = "INSERT INTO `registerd`(`coursename`, `courseID`) VALUES ('D','D')";
//            query = "INSERT INTO `registerd`(`coursename`, `courseID`) VALUES ('B','B')";
//            query = "INSERT INTO `registerd`(`coursename`, `courseID`) VALUES ('O','O')";
//            query = "INSERT INTO `registerd`(`coursename`, `courseID`) VALUES ('P','P')";
//            System.out.println(query);
//            setConnection();
//            runNonQuery(query);
//        } else if (gpa > 2 && level == 4 && hourse > 100) {
//            query = "INSERT INTO `registerd`(`coursename`, `courseID`) VALUES ('L','L')";
//
//            System.out.println(query);
//            setConnection();
//            runNonQuery(query);
//        } else {
//
        }
    }

    @FXML
    private void getAdd(javafx.scene.input.MouseEvent event) {
        register r = new register(txtName.getText(), txtUsername.getText(), txtPassword.getText(),
                Double.parseDouble(txtGpa.getText()),
                Integer.parseInt(txtNumberofhours.getText()), txtPhone.getText(),
                Integer.parseInt(txtLevel.getText()));
        if(Integer.parseInt(txtNumberofhours.getText())>=9 && Integer.parseInt(txtNumberofhours.getText())<=18){
            query = "INSERT INTO `registartion`( `stdName`, `stdUsername`,`stdPassword`, `stdNumofHourse`, `stdLevel`, `stdGpa`,`stdPhone`)  VALUES ('"
                + r.getName() + "','" + r.getUsername() + "','" + r.getPassword() + "','" + r.getNumberOfHours() + "','"
                + r.getLevel() + "','" + r.getGpa() + "','" + r.getPhone() + "')";
            System.out.println(query);
            setConnection();
            runNonQuery(query);
            insertRegisterd();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a number of hours bettwen 9 and 18 ");
            alert.showAndWait();
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
}

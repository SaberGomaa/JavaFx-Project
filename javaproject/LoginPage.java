/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ali Tahoon
 */
public class LoginPage implements Initializable {

    private static com.mysql.jdbc.Connection con;
    private static String url = " ";
    private static String dbName = "courserigistartiosystem";
    @FXML
    Label developers;
    @FXML
    Button btnLogin;
    @FXML
    Button btnExit;
    @FXML
    PasswordField txtpassword;
    @FXML
    TextField txtusername;
    @FXML
    Button btnRegister;
    @FXML
    AnchorPane mainPane;
    
    static String userName;
    @FXML
    private void handleLoginButton(javafx.scene.input.MouseEvent event) throws IOException {
        userName = txtusername.getText();
                        System.out.println("javaproject.LoginPage.handleLoginButton()");
        String passWord = txtpassword.getText();

        boolean isLogged = checkUserName_Password(userName, passWord);
        System.out.println(isLogged);
        if (isLogged) {
            //gooo home Page
            System.out.println("javaproject.LoginPage.handleLoginButton()");
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        }
    }

    @FXML
    private void handleBtnRegister(javafx.scene.input.MouseEvent event) {
        try {
            AnchorPane main = FXMLLoader.load(getClass().getResource("Register.fxml"));
            mainPane.getChildren().setAll(main);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void getExit(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    @FXML
    private void handleCancelButton(javafx.scene.input.MouseEvent event) throws Exception {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
    //database Handel login

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

    public boolean checkUserName_Password(String username, String password) {
        try {
            setConnection();
            Statement stmt = con.createStatement();
            System.out.println("javaproject.LoginPage.checkUserName_Password()");
            String strcheck = "select * from registartion where "
                    + "stdUsername='" + username + "'" + " and " + "stdPassword='"
                    + password + "'";
            stmt.executeQuery(strcheck);
            while (stmt.getResultSet().next()) {
                con.close();
                return true;
            }
            con.close();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        return false;
    }

    //database End Handeled
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TranslateTransition tt = new TranslateTransition(javafx.util.Duration.seconds(8), developers);
       tt.setToX(2000);
        tt.setCycleCount(TranslateTransition.INDEFINITE);
        tt.play();
        
        

    }

}
